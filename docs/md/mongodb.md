# MongoDB安装步骤
## MongoDB开发环境搭建步骤
> 1.下载MongoDB Community Server对应平台的软件包，参考：https://www.mongodb.com/download-center#community 

> 2.运行本地开发环境，打开命令行，进入到MongoDB的bin目录下，执行：
mongod --dbpath D:\MongoDB\data\db （D:\MongoDB\data\db 是按照时选择的数据目录）

> 4.访问UI控制台：http://localhost:27017 
页面显示：It looks like you are trying to access MongoDB over HTTP on the native driver port.

分页查询：page=0是第1页，mysql中page=1是第1页

新增、更新都使用save()方法，更新时，如果数据已存在，则替换原数据

数据存储格式是BSON，每次最大存储16M，大于则使用GridFS存储,GridFS会将大文件对象分割成多个小的chunk(文件片段),一般为256k/个,每个chunk将作为mongodb的一个文档(document)被存储在chunks集合中。gridfs模块会为每个文件创建chunks和files信息.每个文件的实际内容被存在chunks(二进制数据)中,和文件有关的meta数据(filename,content_type,还有用户自定义的属性)将会被存在files集合中.files集合中的文档就是BSON格式,可以使用mongodb的索引

MongoDB库中保存的上传时间uploadDate字段是ISODate，即GMT（格林尼治标准时）时间，比北京时间晚8小时，读取数据时，MongoDB自动转换成当前时区的时间

问题：
1.gridFsTemplate.store(InputStream content, Document metadata);
Stores the given content into a file with the given name.
提示filename can not be null，在metadata设置filename依然报错，而且接口文档说metadata可以为null
