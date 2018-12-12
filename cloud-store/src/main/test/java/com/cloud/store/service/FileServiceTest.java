package com.cloud.store.service;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * 类名称:FileServiceTest
 * 类描述:文件服务接口测试类
 * @author shiqianghui
 * @date 2018-12-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @Autowired
    FileService fileService;

    /**
     * 存储文件
     */
    @Test
    public void saveFile() throws Exception {
        String filePath = "D:" + File.separator + "picture";
        String fileName = "spring.png";
        File file = new File(filePath + File.separator + fileName);
        ObjectId objectId = fileService.saveFile(file, fileName);
        System.out.println(objectId);
    }

    /**
     * 读取文件
     */
    @Test
    public void getFile() throws IOException {
        String filePath = "D:" + File.separator + "mongoDownload";
        String fileName = "spring.png";
        ObjectId objectId = new ObjectId("5c10c8ebd56d516f2c2d1179");
        InputStream inputStream = fileService.getFile(fileName, objectId);
        File file = new File(filePath + File.separator + fileName);
        try (OutputStream outputStream = new FileOutputStream(file)){
            byte[] buf = new byte[1024];
            while (inputStream.read(buf) != -1) {
                outputStream.write(buf);
            }
        }
    }

    /**
     * 删除文件
     */
    @Test
    public void deleteFile() {
        String fileName = "mongoLogo";
        ObjectId objectId = new ObjectId("5c106d68d56d516ec87e4e6c");
        fileService.deleteFile(fileName, objectId);
    }
}
