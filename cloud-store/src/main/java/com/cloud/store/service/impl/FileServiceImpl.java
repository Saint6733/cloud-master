package com.cloud.store.service.impl;

import com.cloud.store.service.FileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 类描述:FileServiceImpl
 * 类描述:文件服务接口实现类
 * @author shiqianghui
 * @date 2018-12-12
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final GridFsTemplate gridFsTemplate;

    public FileServiceImpl(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    /**
     * 存储文件
     * @param file {@link File} 要存储的文件
     * @param fileName 文件名(包含后缀)
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    @Override
    public ObjectId saveFile(File file, String fileName) throws IOException {
        ObjectId objectId;
        DBObject dbObject = new BasicDBObject();
        ((BasicDBObject) dbObject).put("createTime", new Date());
        try (InputStream inputStream = new FileInputStream(file)) {
            objectId = gridFsTemplate.store(inputStream, fileName, dbObject);
        }
        return objectId;
    }

    /**
     * 读取文件
     * @param fileName 文件名
     * @param objectId  {@link ObjectId} 文件在数据库中的_id
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    @Override
    public InputStream getFile(String fileName, ObjectId objectId) throws IOException {
        Query query = new Query();
        if (!StringUtils.isEmpty(fileName)) {
            query.addCriteria(Criteria.where("filename").is(fileName));
        }
        if (objectId != null) {
            query.addCriteria(Criteria.where("_id").is(objectId));
        }
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        if (gridFSFile != null) {
            GridFsResource gridFsResource = gridFsTemplate.getResource(gridFSFile);
            return gridFsResource.getInputStream();
        } else {
            return null;
        }
    }

    /**
     * 删除文件
     * @param fileName 文件名
     * @param objectId {@link ObjectId} 文件在数据库中的_id
     */
    @Override
    public void deleteFile(String fileName, ObjectId objectId) {
        Query query = new Query();
        if (!StringUtils.isEmpty(fileName)) {
            query.addCriteria(Criteria.where("filename").is(fileName));
        }
        if (objectId != null) {
            query.addCriteria(Criteria.where("_id").is(objectId));
        }
        gridFsTemplate.delete(query);
    }
}
