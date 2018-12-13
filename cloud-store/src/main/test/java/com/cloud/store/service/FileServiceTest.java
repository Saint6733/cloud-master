package com.cloud.store.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Date;
import java.util.List;

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
     * 查询全部文件
     */
    @Test
    public void listFile() {
        List<GridFSFile> list = fileService.listFile();
        list.forEach(gridFSFile -> {
            BsonValue id = gridFSFile.getId();
            ObjectId objectId = gridFSFile.getObjectId();
            String filename = gridFSFile.getFilename();
            Date date = gridFSFile.getUploadDate();
            Document document = gridFSFile.getMetadata();
            Integer chunkSize = gridFSFile.getChunkSize();
            Long length = gridFSFile.getLength();
            System.out.println("----------------");
            System.out.println("id:" + id);
            System.out.println("objectId:" + objectId);
            System.out.println("filename:" + filename);
            System.out.println("date:" + date);
            System.out.println("document:" + document);
            System.out.println("chunkSize:" + chunkSize);
            System.out.println("length:" + length);
        });
    }

    /**
     * 根据内容类型存储文件
     */
    @Test
    public void storeByContentType() throws Exception {
        String filename = "Java_manual.pdf";
        String contentType = "pdf";
        String filePath = "D:" + File.separator + "picture";
        File file = new File(filePath + File.separator + filename);
        InputStream content = new FileInputStream(file);
        ObjectId objectId = fileService.storeByContentType(content, filename, contentType);
        System.out.println("--------------");
        System.out.println(objectId);
    }

    /**
     * 存储文件
     */
    @Test
    public void storeByFilenameOrDocument() throws IOException {
        String filePath = "D:" + File.separator + "picture";
        String filename = "spring cloud.png";
        String contentType = "png";
        Document metadata = new Document();
        metadata.put("filename", filename);
        metadata.put("contentType", contentType);
        File file = new File(filePath + File.separator + filename);
        InputStream content = new FileInputStream(file);
        ObjectId objectId = fileService.store(content, filename, metadata);
        System.out.println("--------------------");
        System.out.println(objectId);
    }

    /**
     * 存储文件，根据文件名、文件类型、Document
     */
    @Test
    public void storeByFilenameAndContentTypeOrDocument() throws IOException {
        String filePath = "D:" + File.separator + "picture";
        String filename = "spring.png";
        String contentType = "png";
        File file = new File(filePath + File.separator + filename);
        InputStream content = new FileInputStream(file);
        Document metadata = new Document();
        metadata.put("filename", filename);
        metadata.put("contentType", contentType);
        ObjectId objectId = fileService.store(content, filename, contentType,metadata);
        System.out.println("--------------------");
        System.out.println(objectId);
    }

    /**
     * 存储文件
     */
    @Test
    public void storeByFilename() throws Exception {
        String filePath = "D:" + File.separator + "picture";
        String filename = "java1.jpg";
        File file = new File(filePath + File.separator + filename);
        ObjectId objectId = fileService.store(file, filename);
        System.out.println(objectId);
    }

    /**
     * 读取文件
     */
    @Test
    public void getFile() throws IOException {
        String filePath = "D:" + File.separator + "mongoDownload";
        String filename = "Java_manual.pdf";
        ObjectId objectId = new ObjectId("5c12267ad56d518854b0ab79");
        InputStream inputStream = fileService.getFile(filename, objectId);
        File file = new File(filePath + File.separator + filename);
        try (OutputStream outputStream = new FileOutputStream(file)){
            byte[] buf = new byte[1024];
            while (inputStream.read(buf) != -1) {
                outputStream.write(buf);
            }
        }
    }

    /**
     * 根据ObjectId或文件名获取文件
     */
    @Test
    public void getFileByObjectIdOrFilename() throws IOException{
        String filePath = "D:" + File.separator + "mongoDownload";
        String filename = "Java_manual.pdf";
        String contentType = "pdf";
        ObjectId objectId = new ObjectId("5c12267ad56d518854b0ab79");
//        ObjectId objectId = null;
        InputStream inputStream = fileService.getFileByObjectIdOrFilename(objectId, filename, contentType);
        File file = new File(filePath + File.separator + filename);
        try (OutputStream outputStream = new FileOutputStream(file)){
            byte[] buf = new byte[1024];
            while (inputStream.read(buf) != -1) {
                outputStream.write(buf);
            }
        }
    }

    /**
     * 根据文件名文件
     */
    @Test
    public void getResourcesByFilename() throws IOException{
        String filePath = "D:" + File.separator + "mongoDownload";
        String filename = "spring cloud.png";
        GridFsResource gridFsResource = fileService.getResourcesByFilename(filename);
        if (gridFsResource != null) {
            String dbFilename = gridFsResource.getFilename();
            String id = ((BsonObjectId)gridFsResource.getId()).getValue().toString();
            InputStream inputStream = gridFsResource.getInputStream();
            File file = new File(filePath + File.separator + id + "_" + dbFilename);
            try (OutputStream outputStream = new FileOutputStream(file)) {
                byte[] buf = new byte[1024];
                while (inputStream.read(buf) != -1) {
                    outputStream.write(buf);
                }
            }
        }
    }

    /**
     * 根据文件名模糊查询所有文件
     */
    @Test
    public void getResourcesLikeFilename() throws IOException{
        String filePath = "D:" + File.separator + "mongoDownload";
        String filename = "spring.png";
        GridFsResource[] gridFsResources = fileService.getResourcesLikeFilename(filename);
        if (gridFsResources.length > 0) {
            for (GridFsResource gridFsResource: gridFsResources) {
                System.out.println("-------------------");
                System.out.println(gridFsResource.getFilename());
                String dbFilename = gridFsResource.getFilename();
                String id = ((BsonObjectId)gridFsResource.getId()).getValue().toString();
                InputStream inputStream = gridFsResource.getInputStream();
                File file = new File(filePath + File.separator + id + "_" + dbFilename);
                try (OutputStream outputStream = new FileOutputStream(file)){
                    byte[] buf = new byte[1024];
                    while (inputStream.read(buf) != -1) {
                        outputStream.write(buf);
                    }
                }
            }
        }
    }

    /**
     * 删除文件
     */
    @Test
    public void deleteFile() {
        String fileName = "mongoLogo.png";
        ObjectId objectId = new ObjectId("5c106d68d56d516ec87e4e6c");
        fileService.deleteFile(fileName, objectId);
    }
}
