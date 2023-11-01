# mongodb

该项目旨在验证 MongoDB 中的一些知识点，这里主要是验证副本集，实现高可用的数据验证。


## 副本集搭建

<a href="[MongDB副本集环境搭建 · ChaFano/mongodb Wiki (github.com)](https://github.com/ChaFano/mongodb/wiki/MongDB副本集环境搭建)">MongDB副本集环境搭建</a>


## yml文件配置

这里的yml文件里面配置副本集环境搭建的服务器地址，需要新建并配置，在系统中需要获取副本集的一些配置信息需要在 admin 数据库下操作。

replicaSet=RPS_TEST1 这里是副本集名称，搭建副本集环境的时候定义的。

```javascript

server:
  port: 8081

spring:
  thymeleaf:
    cache: false
    servlet:
      content-type: text/html
    mode: LEGACYHTML5
  mvc:
    static-path-pattern: /static/**
    view:
      prefix: classpath:/templates
      suffix: .html
  data:
    mongodb:
      uri: mongodb://127.0.0.1:28011,127.0.0.1:28012,127.0.0.1:28013,127.0.0.1:28014/admin?replicaSet=RPS_TEST1&slaveOk=true&write=1&readPreference=secondaryPreferred&connectTimeoutMS=300000

connection:
  url: mongodb://127.0.0.1:28011

```



