package com.ddxq.base.dao.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface BaseFileDao {
	/**
	 * @Title:fileToByte
	 * @Description:file文件转换
	 * @param: file_path
	 * @return: Map
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public byte[] fileToByte(InputStream inStream, long file_lenth) throws IOException;
}
