package com.cloud.store.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 类描述:FileService
 * 类描述:文件服务接口
 * @author shiqianghui
 * @date 2018-12-12
 */
public interface FileService {

    /**
     * 查询全部文件
     * @return {@link List < GridFSFile >}
     */
    List<GridFSFile> listFile();

    /**
     * 根据内容类型存储文件
     * @param content {@link InputStream}
     * @param filename 文件名
     * @param contentType 内容类型
     * @return {@link ObjectId}
     */
    ObjectId storeByContentType(InputStream content, String filename, String contentType);

    /**
     * 存储文件
     * @param content {@link InputStream}
     * @param filename 文件名(包含后缀)
     * @param metadata 元数据
     * @return {@link ObjectId}
     */
    ObjectId store(InputStream content, String filename, Document metadata);

    /**
     * 存储文件
     * @param content {@link InputStream}
     * @param filename 文件名(包含后缀)
     * @param contentType 文件类型
     * @param metadata {@link Document}
     * @return {@link ObjectId}
     */
    ObjectId store(InputStream content, String filename, String contentType, Document metadata);
    /**
     * 存储文件
     * @param file {@link File} 要存储的文件
     * @param filename 文件名(包含后缀)
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    ObjectId store(File file, String filename) throws IOException;

    /**
     * 读取文件
     * @param filename 文件名
     * @param objectId  {@link ObjectId} 文件在数据库中的_id
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    InputStream getFile(String filename, ObjectId objectId) throws IOException;

    /**
     * 根据ObjectId或文件名获取文件
     * @param objectId {@link ObjectId} 文件在数据库中的_id
     * @param filename 文件名
     * @param contentType 文件类型 metadata._contentType
     * @return {@link InputStream}
     * @throws IOException 抛出异常
     */
    InputStream getFileByObjectIdOrFilename(ObjectId objectId, String filename, String contentType) throws IOException;

    /**
     * 根据文件名文件
     * @param filename 文件名
     * @return {@link GridFsResource}
     */
    GridFsResource getResourcesByFilename(String filename);
    /**
     * 根据文件名模糊查询所有文件
     * @param filename 文件名
     * @return {@link GridFsResource[]}
     */
    GridFsResource[] getResourcesLikeFilename(String filename);
    /**
     * 删除文件
     * @param filename 文件名
     * @param objectId {@link ObjectId} 文件在数据库中的_id
     */
    void deleteFile(String filename, ObjectId objectId);
}
