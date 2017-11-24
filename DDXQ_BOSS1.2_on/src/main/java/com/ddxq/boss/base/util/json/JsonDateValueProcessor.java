package com.ddxq.boss.base.util.json;

import java.sql.Date;
import java.text.SimpleDateFormat;


import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value);
	}

	public Object process(Object value) {
		if (value instanceof Date) {
			return value.toString();
		}
		return value == null ? "" : value.toString();
	}
}