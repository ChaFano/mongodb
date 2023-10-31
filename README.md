# mongodb

## 副本集搭建


### 1、软件解压

```json
tar -zxvf mongodb-linux-x86_64-ubuntu2004-5.0.21.tgz
```

### 2、创建文件夹

`创建主节点、从节点、选举节点文件夹和每个节点的数据存储、日志、配置文件夹`

```json
mkdir -pv /mongo_replica_set/mongod/{master,slave,arbiter}

mkdir -pv /mongo_replica_set/mongod/master/{data,log,conf}

mkdir -pv /mongo_replica_set/mongod/slave/{data,log,conf}

mkdir -pv /mongo_replica_set/mongod/arbiter/{data,log,conf}
```

### 3、复制软件

```json
cp -ra /mongo_replica_set/mongodb  /mongo_replica_set/mongod/master/

cp -ra /mongo_replica_set/mongodb  /mongo_replica_set/mongod/slave/
cp -ra /mongo_replica_set/mongodb  /mongo_replica_set/mongod/slave2/

cp -ra /mongo_replica_set/mongodb  /mongo_replica_set/mongod/arbiter/
```

### 4、创建配置文件

创建master配置文件

```json
vim master/conf/mongodb.conf

dbpath=/mongo_replica_set/mongod/master/data
logpath=/mongo_replica_set/mongod/master/log/mongodb.log
pidfilepath=/mongo_replica_set/mongod/master/master.pid
directoryperdb=true
logappend=true
replSet=RPS_TEST1
bind_ip=0.0.0.0
port=28011
oplogSize=10000
fork=true
noprealloc=true
```

创建slave配置文件

```json
vim slave/conf/mongodb.conf

dbpath=/mongo_replica_set/mongod/slave/data
logpath=/mongo_replica_set/mongod/slave/log/mongodb.log
pidfilepath=/mongo_replica_set/mongod/slave/slave.pid
directoryperdb=true
logappend=true
replSet=RPS_TEST1
bind_ip=0.0.0.0
port=28012
oplogSize=10000
fork=true
noprealloc=true
```

创建arbiter配置文件

```json
vim arbiter/conf/mongodb.conf

dbpath=/mongo_replica_set/mongod/arbiter/data
logpath=/mongo_replica_set/mongod/arbiter/log/mongodb.log
pidfilepath=/mongo_replica_set/mongod/arbiter/arbiter.pid
directoryperdb=true
logappend=true
replSet=RPS_TEST1
bind_ip=0.0.0.0
port=28013
oplogSize=10000
fork=true
noprealloc=true
```

创建slave2配置文件

```json
vim slave2/conf/mongodb.conf

dbpath=/mongo_replica_set/mongod/slave2/data
logpath=/mongo_replica_set/mongod/slave2/log/mongodb.log
pidfilepath=/mongo_replica_set/mongod/slave2/slave2.pid
directoryperdb=true
logappend=true
replSet=RPS_TEST1
bind_ip=0.0.0.0
port=28012
oplogSize=10000
fork=true
noprealloc=true
```

配置文件参数解释

```json
dbpath：数据存放目录
logpath：日志存放路径
pidfilepath：进程文件，方便停止mongodb
directoryperdb：为每一个数据库按照数据库名建立文件夹存放
logappend：以追加的方式记录日志
replSet：replica set的名字
bind_ip：mongodb所绑定的ip地址
port：mongodb进程所使用的端口号，默认为27017
oplogSize：mongodb操作日志文件的最大大小。单位为Mb，默认为硬盘剩余空间的5%
fork：以后台方式运行进程
noprealloc：不预先分配存储
```

### 5、启动mongodb

```json
启动主节点
./master/mongodb/bin/mongod -f master/conf/mongodb.conf 

启动从节点
./slave/mongodb/bin/mongod -f slave/conf/mongodb.conf 

./slave2/mongodb/bin/mongod -f slave2/conf/mongodb.conf 

启动仲裁节点
./arbiter/mongodb/bin/mongod -f arbiter/conf/mongodb.conf 

查看进程
ps -ef | grep mongodb

```

### 6、配置副本集

```json
1、进入主节点
./master/mongodb/bin/mongo --port=28011

2、切换到 admin 数据库
use admin

3、定义初始化脚本（类似于js 的 var a = {}）

fbj={_id:"RPS_TEST1", 
     members:[{_id:0,host:'203.33.207.171:28011',priority:2},
              {_id:1,host:'203.33.207.171:28012',priority:1}, 	
              {_id:2,host:'203.33.207.171:28013',arbiterOnly:true},
              {_id:3,host:'203.33.207.171:28014',priority:1}, 
             ]};

4、使配置生效
rs.initiate(fbj)

5、检查是否生效
rs.status()
```

### 7、测试副本集

```json
主节点 创建数据库
use db01

主节点 插入数据
db.book.insertOne({"name":"菜鸟教程"})
db.book.insertOne({"name":"java教程"})
db.book.insertOne({"name":"js教程"})
db.book.insertOne({"name":"mongodb教程"})

db.book.insertOne({"name":"C++ 教程"})

db.book.find()

从节点查看（默认是不可读的，需要执行以下命令）
rs.secondaryOk();

1、从节点
./slave/mongodb/bin/mongo --port=28012
./slave2/mongodb/bin/mongo --port=28014


2、选举节点
./arbiter/mongodb/bin/mongo --port=28013
选举节点是不保存数据的，只是在服务挂掉的时候作为选举角色来选举新的主节点

在主节点添加数据后再去从节点查看数据是否变化

```

### 8、移出和加入副本集

**移出副本集**

在 MongoDB 中，要从副本集（Replica Set）中移除一个节点，可以执行以下步骤：

1. **连接到主节点：** 使用 `mongo` shell 或者适当的 MongoDB 客户端连接到副本集的主节点。

2. **执行 rs.remove()：** 在连接到主节点之后，执行 `rs.remove()` 命令以移除指定的节点。例如，如果你想要移除节点 `node-3`，你可以执行如下命令：

```json
   rs.remove("node-3:port")  // 替换为要移除的节点的主机名和端口
   
   rs.remove("203.33.207.171:28011")
   rs.remove("203.33.207.171:28012")  // 只能将从节点移出去
```

请确保替换为要移除的节点的确切主机名和端口。

3. **等待重新平衡：** MongoDB 将会开始重新平衡数据，重新分配从节点的数据到剩余的节点。这个过程可能需要一些时间。

在执行这些操作之前，请确保数据备份，并且谨慎执行。节点的移除可能对副本集的可用性产生短期影响。

**加入副本集**

```json
rs.remove("node-3:port")  // 替换为要加入的节点的主机名和端口

rs.add("203.33.207.171:28012")  // 加入节点
```









