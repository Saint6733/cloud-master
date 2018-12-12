package com.cloud.store.service;

import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类描述:FileService
 * 类描述:文件服务接口
 * @author shiqianghui
 * @date 2018-12-12
 */
public interface FileService {

    /**
     * 存储文件
     * @param file {@link File} 要存储的文件
     * @param fileName 文件名(包含后缀)
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    ObjectId saveFile(File file, String fileName) throws IOException;

    /**
     * 读取文件
     * @param fileName 文件名
     * @param objectId  {@link ObjectId} 文件在数据库中的_id
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    InputStream getFile(String fileName, ObjectId objectId) throws IOException;

    /**
     * 删除文件
     * @param fileName 文件名
     * @param objectId {@link ObjectId} 文件在数据库中的_id
     */
    void deleteFile(String fileName, ObjectId objectId);
}
