package com.xmz.core.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by SinjinSong on 2017/7/20.
 */
public class IOUtil {
    private static final Logger log = LoggerFactory.getLogger(IOUtil.class);


    public static byte[] getBytesFromFile(String fileName) throws IOException {
        InputStream in = IOUtil.class.getResourceAsStream(fileName);
        if (in == null) {
            log.info("Not Found File:{}",fileName);
            throw new FileNotFoundException();
        }
        log.info("正在读取文件:{}",fileName);
        return getBytesFromStream(in);
    }

    public static byte[] getBytesFromStream(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];     
        int len = -1;     
        while((len = in.read(buffer)) != -1){     
          outStream.write(buffer, 0, len);      
        }       
        outStream.close();      
        in.close();  
        return outStream.toByteArray();     
    }
   
}
