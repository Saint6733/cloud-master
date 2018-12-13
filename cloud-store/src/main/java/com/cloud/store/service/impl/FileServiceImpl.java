package com.cloud.store.service.impl;

import com.cloud.store.service.FileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类描述:FileServiceImpl
 * 类描述:文件服务接口实现类
 * @author shiqianghui
 * @date 2018-12-12
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private String criteriaFilename = "filename";
    private final GridFsTemplate gridFsTemplate;

    public FileServiceImpl(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    /**
     * 查询全部文件
     * @return {@link List <GridFSFile>}
     */
    @Override
    public List<GridFSFile> listFile() {
        List<GridFSFile> list = new ArrayList<>();
        Query query = new Query();
        GridFSFindIterable gridFSFiles = gridFsTemplate.find(query);
        MongoCursor<GridFSFile> mongoCursor = gridFSFiles.iterator();
        mongoCursor.forEachRemaining(list::add);
        return list;
    }

    /**
     * 根据内容类型存储文件
     * @param content {@link InputStream}
     * @param filename 文件名
     * @param contentType 内容类型
     * @return {@link ObjectId}
     */
    @Override
    public ObjectId storeByContentType(InputStream content, String filename, String contentType) {
        return gridFsTemplate.store(content, filename, contentType);
    }

    /**
     * 存储文件
     * @param content {@link InputStream}
     * @param filename 文件名(包含后缀)
     * @param metadata 元数据
     * @return {@link ObjectId}
     */
    @Override
    public ObjectId store(InputStream content, String filename, Document metadata) {
        return gridFsTemplate.store(content, filename, metadata);
    }

    /**
     * 存储文件
     * @param content {@link InputStream}
     * @param filename 文件名(包含后缀)
     * @param contentType 文件类型
     * @param metadata {@link Document}
     * @return {@link ObjectId}
     */
    @Override
    public ObjectId store(InputStream content, String filename, String contentType, Document metadata) {
        return gridFsTemplate.store(content, filename, contentType, metadata);
    }

    /**
     * 存储文件
     * @param file {@link File} 要存储的文件
     * @param filename 文件名(包含后缀)
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    @Override
    public ObjectId store(File file, String filename) throws IOException {
        ObjectId objectId;
        DBObject dbObject = new BasicDBObject();
        ((BasicDBObject) dbObject).put("createTime", new Date());
        try (InputStream inputStream = new FileInputStream(file)) {
            objectId = gridFsTemplate.store(inputStream, filename, dbObject);
        }
        return objectId;
    }

    /**
     * 读取文件
     * @param filename 文件名
     * @param objectId  {@link ObjectId} 文件在数据库中的_id
     * @return {@link ObjectId} 文件在数据库中的_id
     * @throws IOException 抛出异常
     */
    @Override
    public InputStream getFile(String filename, ObjectId objectId) throws IOException {
        Query query = new Query();
        if (!StringUtils.isEmpty(filename)) {
            query.addCriteria(Criteria.where(criteriaFilename).is(filename));
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
     * 根据ObjectId或文件名获取文件
     * @param objectId {@link ObjectId} 文件在数据库中的_id
     * @param filename 文件名
     * @param contentType 文件类型 metadata._contentType
     * @return {@link InputStream}
     * @throws IOException 抛出异常
     */
    @Override
    public InputStream getFileByObjectIdOrFilename(ObjectId objectId, String filename, String contentType) throws IOException {
        Query query = new Query();
        if (!StringUtils.isEmpty(filename)) {
            query.addCriteria(Criteria.where(criteriaFilename).is(filename));
        }
        if (objectId != null) {
            query.addCriteria(Criteria.where("_id").is(objectId));
        }
        if (!StringUtils.isEmpty(contentType)) {
            query.addCriteria(Criteria.where("metadata._contentType").is(contentType));
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
     * 根据文件名文件
     * @param filename 文件名
     * @return {@link GridFsResource}
     */
    @Override
    public GridFsResource getResourcesByFilename(String filename) {
        return gridFsTemplate.getResource(filename);
    }

    /**
     * 根据文件名模糊查询所有文件
     * @param filename 文件名
     * @return {@link GridFsResource[]}
     */
    @Override
    public GridFsResource[] getResourcesLikeFilename(String filename) {
        return gridFsTemplate.getResources("*" + filename + "*");
    }

    /**
     * 删除文件
     * @param filename 文件名
     * @param objectId {@link ObjectId} 文件在数据库中的_id
     */
    @Override
    public void deleteFile(String filename, ObjectId objectId) {
        Query query = new Query();
        if (!StringUtils.isEmpty(filename)) {
            query.addCriteria(Criteria.where(criteriaFilename).is(filename));
        }
        if (objectId != null) {
            query.addCriteria(Criteria.where("_id").is(objectId));
        }
        gridFsTemplate.delete(query);
    }
}
