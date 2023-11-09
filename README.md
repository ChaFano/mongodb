@[TOC](MongoDB副本集特点验证)

## mogodb副本集概述

MongoDB副本集是将数据同步在多个服务器的过程。

复制提供了数据的冗余备份，并在多个服务器上存储数据副本，提高了数据的可用性， 并可以保证数据的安全性。

复制还允许您从硬件故障和服务中断中恢复数据。
`特点归纳为:数据冗余、故障自动转移、读写分离，在本文中主要做了前两个和 mongodb 与 mysql 的性能测试`

## 副本集搭建

副本集环境搭建过程这里不过多赘述，已经有很多博主都写过了，这里放了一个github的wiki 地址，需要的可以查看。
<a href="https://github.com/ChaFano/mongodb/wiki/MongDB%E5%89%AF%E6%9C%AC%E9%9B%86%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA">MongDB副本集环境搭建</a>

## 副本集结构

<div>
<image src="https://img-blog.csdnimg.cn/b0789ea2ae884b18803faf63ff25ce8a.png#pic_center"/>
</div>


## 验证结果

![在这里插入图片描述](https://img-blog.csdnimg.cn/914c3de593d24929816251436796d153.png#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/469e762509774b35811b1a7806d1adb7.png#pic_center)



## 源码地址

>https://github.com/ChaFano/mongodb.git

