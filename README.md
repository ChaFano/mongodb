## mogodb副本集概述

MongoDB副本集是将数据同步在多个服务器的过程。

复制提供了数据的冗余备份，并在多个服务器上存储数据副本，提高了数据的可用性， 并可以保证数据的安全性。

复制还允许您从硬件故障和服务中断中恢复数据。
`特点归纳为:数据冗余、故障自动转移、读写分离，在本文中主要做了前两个和 mongodb 与 mysql 的读写性能测试`

## yml 配置文件
这里只给出例子，启动项目的话需要按照自己的需求配置，将本地的ip换成你自己mongodb 服务器的地址。
```yml
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
      uri: mongodb://127.0.0.1:28011,127.0.0.1:28012,127.0.0.1:28013,127.0.0.1:28014,127.0.0.1:28015/admin?replicaSet=RPS_TEST1&slaveOk=true&write=1&readPreference=secondaryPreferred&connectTimeoutMS=300000
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimeZone=Asia/shanghai
    username: root
    password: 3306

connection:
  url1: mongodb://127.0.0.1:28011
  url2: mongodb://127.0.0.1:28012
  url3: mongodb://127.0.0.1:28013
  url4: mongodb://127.0.0.1:28014
  url5: mongodb://127.0.0.1:28015



mybatis-plus:
  mapper-locations: classpath:com/chafan/*/mapper/xml/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 查看日志
    default-enum-type-handler: org.apache.ibatis.type.EnumOrdinalTypeHandler #开启通用枚举支持，默认使用ordinalType

```

## 测试结果解释
下文中图标中的结果是通过postman 请求接口得到的，不是在网页上自动获取取得的，因为网页上测试时间有点长，不易展示出效果来。

## 副本集搭建

副本集环境搭建过程这里不过多赘述，已经有很多博主都写过了，这里放了一个github的wiki 地址，需要的可以查看。
<a href="https://github.com/ChaFano/mongodb/wiki/MongDB%E5%89%AF%E6%9C%AC%E9%9B%86%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA">MongDB副本集环境搭建</a>

## 副本集结构

<div>
<image src="https://img-blog.csdnimg.cn/b0789ea2ae884b18803faf63ff25ce8a.png#pic_center"/>
</div>


## 验证结果

<div>
<image src="https://img-blog.csdnimg.cn/914c3de593d24929816251436796d153.png#pic_center"/>
</div>
  <div>
<image src="https://img-blog.csdnimg.cn/469e762509774b35811b1a7806d1adb7.png#pic_center"/>
</div>

## 源码地址

>https://github.com/ChaFano/mongodb.git

