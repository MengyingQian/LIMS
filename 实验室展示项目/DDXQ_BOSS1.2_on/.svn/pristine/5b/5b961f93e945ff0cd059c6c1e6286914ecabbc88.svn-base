package com.ddxq.base.dao.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Repository;


/**
 * 
 * ClassName:BaseFastdfsDao Description:File基本操作
 * 
 * @author HHC
 * @date 2016年4月20日 下午4:30:48
 *
 */
@Repository("baseFileDao")
public class BaseFileDaoImpl implements BaseFileDao {

	/**
	 * @Title:fileToByte
	 * @Description:file文件转换
	 * @param: file_path
	 * @return: Map
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public byte[] fileToByte(InputStream inStream, long file_lenth) throws IOException{
		
		byte[] buffer = new byte[256 * 1024];  
        byte[] fileBuffer = new byte[(int) file_lenth];
		int count = 0;  
        int length = 0;  
      
        while((length = inStream.read(buffer)) != -1){  
            for (int i = 0; i < length; ++i)  
            {  
           	
                fileBuffer[count + i] = buffer[i];  
            }  
            count += length;
        }
		
		return fileBuffer;
	}
}
