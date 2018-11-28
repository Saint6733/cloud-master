# Consul安装步骤
## Consul开发环境搭建步骤
> 1.下载consul对应平台的软件包，参考：https://www.consul.io/downloads.html 

> 2.解压unzip consul.*.zip

> 3.运行本地开发环境，命令：consul agent -dev

> 4.访问UI控制台：http://localhost:8500










##Spring Cloud Consul配置
> debug配置是否调试模式，如果打包发布的话，需要设置为false。  
server.port配置的是 Spring Boot 服务的端口。  
spring.cloud.consul.host/port配置的是本地 Consul 的地址和端口（Server 节点和 Client 节点都可以），Spring Cloud Consul 会调用 Consul HTTP REST 接口，进行服务注册。  
spring.cloud.consul.discovery.true配置启动是否注册服务，  
spring.cloud.consul.discovery.hostname配置 Spring Boot 服务的主机地址，也可以不进行配置，默认本机地址。  
spring.cloud.consul.discovery.serviceName配置 Consul 注册的服务名称，${spring.application.name}变量是我们上面application.properties配置文件中添加的配置。  
spring.cloud.consul.discovery.healthCheckPath配置 Consul 健康检查地址，Actuator 组件帮我们进行了实现，所以我们不需要额外的实现，地址在服务启动的时候，打印信息里面可以看到。  
spring.cloud.consul.discovery.healthCheckInterval配置 Consul 健康检查频率，也就是心跳频率。  
spring.cloud.consul.discovery.tags配置 Consul 注册服务的 Tags，设置为urlprefix-/serviceName的格式，是自动注册到 Fabio 集群中。  
spring.cloud.consul.discovery.instanceId配置 Consul 注册服务 ID。
