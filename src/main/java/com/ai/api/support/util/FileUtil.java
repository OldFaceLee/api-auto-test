package com.ai.api.support.util;

import java.io.File;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/14 下午2:00
 * @description:
 */
public class FileUtil {
    private FileUtil(){}
    private static class Singleton{
        private static final FileUtil instance = new FileUtil();
    }
    public static FileUtil getInstance(){
        return Singleton.instance;
    }

    public void createFolder(String folderPath){
        File file = new File(folderPath);
        if(!file.isDirectory()){
            file.mkdir();
        }else {
            return;
        }
    }


}
