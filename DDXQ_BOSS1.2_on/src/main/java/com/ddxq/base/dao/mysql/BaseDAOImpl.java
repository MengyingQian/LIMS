package com.ddxq.base.dao.mysql;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.LobHandler;

import com.ddxq.base.dao.BaseDAO;
import com.ddxq.common.log.SystemLog;
import com.ddxq.common.page.PageObject;
import com.ddxq.common.cache.CacheUtil;
import com.ddxq.common.constant.GlobalConstant;

/**
 * dao接口mysql实现，承担所有公共DAO的API实现
 * @author zxz
 * @version 1.0.0
 * @since 2015-02-01
 * */
@SuppressWarnings("all")
public class BaseDAOImpl extends NamedParameterJdbcDaoSupport implements
		BaseDAO {

	@Autowired
	private CacheUtil cacheUtil;//缓存工具类

	@Autowired
	private SystemLog systemLog;//日志工具类

	@Override
	public PageObject queryForMPageList(String sql, Object[] args, Map req) {
		int totalCount = 1;
		int start = NumberUtils.toInt(ObjectUtils.toString(req.get("start"),
				"1"));
		int limit = NumberUtils.toInt(ObjectUtils.toString(req.get("limit"),
				GlobalConstant.Global_PageSize + ""));
		int startIndex = limit * (start - 1);
		String sort = ObjectUtils.toString(req.get("sort"));
		String dir = ObjectUtils.toString(req.get("dir"));

		StringBuffer buildsql = new StringBuffer();
		buildsql.append("select * from ( ");
		if (StringUtils.isNotBlank(sort)) {
			String pa = "order by (,|\\.|\\w|\\d| )+( asc| desc)?( Nulls last)?( Nulls first)?\\s?$";
			String re = " order by " + sort + " " + dir;
			Matcher matcher = Pattern.compile(pa, Pattern.CASE_INSENSITIVE)
					.matcher(sql);
			if (matcher.find()) {
				buildsql.append(matcher.replaceFirst(re));
			}
		} else {
			buildsql.append(sql);
		}
		buildsql.append("	) temp LIMIT " + String.valueOf(startIndex) + ","
				+ String.valueOf(limit));

		systemLog.debugLog(getClass(), buildsql.toString() + " : args is "
				+ StringUtils.join(args, ","));
		PageObject page = new PageObject();
		try {
			List list = super.getJdbcTemplate().queryForList(
					buildsql.toString(), args);
			String countsql = "SELECT COUNT(*) FROM (" + sql + ") TEMP";
			totalCount = super.getJdbcTemplate().queryForInt(countsql, args);
			page.setDatasource(list);
			page.setTotalCount(totalCount);
			int _absolutePage = (int) Math.ceil(totalCount * 1.0 / limit);
			page.setAbsolutePage(_absolutePage);
			int _currentPage = start / limit + 1;
			page.setCurrentPage(_currentPage);
		} catch (Exception e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
		}
		return page;
	}

	@Override
	public List queryForList(String sql, Object[] args) {
		systemLog.debugLog(getClass(),
				sql + " : args is " + StringUtils.join(args, ","));
		List list = null;
		try {
			list = super.getJdbcTemplate().queryForList(sql, args);
		} catch (Exception e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
		}
		return list;
	}

	@Override
	public List queryForList(String sql, Map map) {
		systemLog.debugLog(getClass(),
				sql.toString() + " : args is " + map.toString());
		List list = null;
		try {
			list = super.getNamedParameterJdbcTemplate().queryForList(sql,
					map);
		} catch (Exception e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
		}
		return list;
	}

    public void insertIntoTable(){
    }

	
	@Override
	public PageObject queryForPageList(String sql, Object[] args,
			int currentPage, int pageSize) {
		int totalCount = 1;
		int absolutePage = 1;

		int startIndex = pageSize * (currentPage - 1);
		
		StringBuffer buildsql = new StringBuffer();
		if (currentPage == -1) {
			buildsql.append(sql);
		} else {
			buildsql.append("select * from ( ");
			buildsql.append(sql);
			buildsql.append("	) temp LIMIT " + startIndex + "," + pageSize);
		}
		systemLog.debugLog(getClass(), buildsql.toString() + " : args is "
				+ StringUtils.join(args, ","));

		List list = super.getJdbcTemplate().queryForList(buildsql.toString(),
				args);

		String countsql = "select count(*) from (" + sql + ") temp";
		totalCount = super.getJdbcTemplate().queryForInt(countsql, args);

		if (totalCount == 0)
			absolutePage = 1;
		else
			absolutePage = (int) Math.ceil(totalCount / (pageSize * 1.0));

		PageObject page = new PageObject();
		page.setDatasource(list);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		page.setAbsolutePage(absolutePage);
		return page;
	}

	@Override
	public Map queryForMap(String sql, Object[] args) {
		systemLog.debugLog(getClass(),
				sql + " : args is " + StringUtils.join(args, ","));
		List list = super.getJdbcTemplate().queryForList(sql, args);
		if (list != null && list.size() > 0) {
			return (Map) list.get(0);
		}
		return new HashMap();
	}

	@Override
	public Map queryForMap(String sql) {
		systemLog.debugLog(getClass(), sql + " : args is no value");
		List list = super.getJdbcTemplate().queryForList(sql);
		if (list != null && list.size() > 0) {
			return (Map) list.get(0);
		}
		return new HashMap();
	}
	
	@Override
	public List queryForList(String sql) {
		systemLog.debugLog(getClass(), sql + " : args is no value");
		List list = super.getJdbcTemplate().queryForList(sql);
		if (list != null && list.size() > 0) {
			return list;
		}
		return new ArrayList();
	}

	@Override
	public Map queryForMap(String sql, Map map) {
		systemLog.debugLog(getClass(), sql + " : args is " + map.toString());
		List list = super.getNamedParameterJdbcTemplate()
				.queryForList(sql, map);
		if (list != null && list.size() > 0) {
			return (Map) list.get(0);
		}
		return new HashMap();
	}

	@Override
	public PageObject queryForNamedPageList(String sql, Map map, int currentPage) {
		int pageSize = GlobalConstant.Global_PageSize;
		return queryForNamedPageList(sql, map, currentPage, pageSize);
	}

	@Override
	public PageObject queryForPageList(String sql, int currentPage) {
		int pageSize = GlobalConstant.Global_PageSize;
		return queryForPageList(sql, null, currentPage, pageSize);
	}

	@Override
	public PageObject queryForNamedPageList(String sql, Map map,
			int currentPage, int pageSize) {
		int totalCount = 1;
		int absolutePage = 1;
		
		int startIndex = pageSize * (currentPage - 1);

		StringBuffer buildsql = new StringBuffer();
		if (currentPage == -1) {
			buildsql.append(sql);
		} else {
			buildsql.append("select * from ( ");
			buildsql.append(sql);
			buildsql.append("	) temp LIMIT " + startIndex + "," + pageSize);
		}
		systemLog.debugLog(getClass(), sql + " : args is " + map.toString());

		List list = super.getNamedParameterJdbcTemplate().queryForList(
				buildsql.toString(), map);

		String countsql = "select count(*) from (" + sql + ") temp";
		totalCount = super.getNamedParameterJdbcTemplate().queryForInt(
				countsql, map);

		if (totalCount == 0)
			absolutePage = 1;
		else
			absolutePage = (int) Math.ceil(totalCount / (pageSize * 1.0));

		PageObject page = new PageObject();
		page.setDatasource(list);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		page.setAbsolutePage(absolutePage);
		return page;
	}

	@Override
	public PageObject queryForPageList(String sql, Object[] args,
			int currentPage) {
		int pageSize = GlobalConstant.Global_PageSize;
		return queryForPageList(sql, args, currentPage, pageSize);
	}

	@Override
	public List getNamedRecordSet(String sql, Map map)
			throws DataAccessException {
		List returnList = null;
		systemLog.debugLog(getClass(), sql + " : args is " + map.toString());
		try {
			returnList = getNamedParameterJdbcTemplate().queryForList(sql, map);
		} catch (Exception e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
		}
		return returnList;
	}

	@Override
	public Map getNamedFirstRowValue(String sql, Map map)
			throws DataAccessException {
		Map returnMap = null;
		systemLog.debugLog(getClass(), sql + " : args is " + map.toString());
		try {
			List list = getNamedParameterJdbcTemplate().queryForList(sql, map);
			if (list.size() > 0) {
				returnMap = (Map) list.get(0);
			}
		} catch (Exception e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
		}
		return returnMap;
	}

	@Override
	public boolean executeNamedCommand(String sql, Map map)
			throws DataAccessException {
		boolean returnValue = false;
		systemLog.debugLog(getClass(), sql + " : args is " + map.toString());
		try {
			getNamedParameterJdbcTemplate().update(sql, map);
			returnValue = true;
		} catch (DataAccessException e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
			throw e;
		}
		return returnValue;
	}

	@Override
	public long getNamedRecordCount(String sql, Map map)
			throws DataAccessException {
		long returnValue = 0;
		systemLog.debugLog(getClass(), sql + " : args is " + map.toString());
		try {
			List list = getNamedParameterJdbcTemplate().queryForList(sql, map);
			returnValue = list.size();
		} catch (DataAccessException e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
			throw e;
		}
		return returnValue;
	}
	
	@Override
	public long getRecordCount(String sql) throws DataAccessException {
		long returnValue = 0;
		systemLog.debugLog(getClass(), sql + " : args is no value");
		try {
			List list = getJdbcTemplate().queryForList(sql);
			returnValue = list.size();
		} catch (DataAccessException e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
			throw e;
		}
		return returnValue;
	}

	@Override
	public boolean findNamedSQL(String sql, Map map) throws DataAccessException {
		return getNamedRecordCount(sql, map) > 0;
	}

	@Override
	public List getRecordSet(String sql, Object[] args)
			throws DataAccessException {
		List returnList = null;
		systemLog.debugLog(getClass(),
				sql + " : args is " + StringUtils.join(args, ","));
		try {
			returnList = getJdbcTemplate().queryForList(sql, args);
		} catch (DataAccessException e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
			throw e;
		}
		return returnList;
	}

	@Override
	public Map getFirstRowValue(String sql, Object[] args)
			throws DataAccessException {
		Map returnMap = null;
		systemLog.debugLog(getClass(),
				sql + " : args is " + StringUtils.join(args, ","));
		try {
			List list = getJdbcTemplate().queryForList(sql, args);
			if (list.size() > 0) {
				returnMap = (Map) list.get(0);
			}
		} catch (DataAccessException e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
			throw e;
		}
		return returnMap;
	}

	@Override
	public boolean executeCommand(String sql, Object[] args)
			throws DataAccessException {
		boolean returnValue = false;
		systemLog.debugLog(getClass(),
				sql + " : args is " + StringUtils.join(args, ","));
		try {
			getJdbcTemplate().update(sql, args);
			returnValue = true;
		} catch (DataAccessException e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
			throw e;
		}
		return returnValue;
	}

	@Override
	public long getRecordCount(String sql, Object[] args)
			throws DataAccessException {
		long returnValue = 0;
		systemLog.debugLog(getClass(),
				sql + " : args is " + StringUtils.join(args, ","));
		try {
			List list = getJdbcTemplate().queryForList(sql, args);
			returnValue = list.size();
		} catch (DataAccessException e) {
			systemLog.errorLog(getClass(), "--", "--", "--", "--", "--", e);
			throw e;
		}
		return returnValue;
	}

	@Override
	public boolean findSQL(String sql, Object[] args)
			throws DataAccessException {
		return getRecordCount(sql, args) > 0;
	}

}
