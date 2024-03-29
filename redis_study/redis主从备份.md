#    主从复制

###  1. 概念

 主机写,从机读,保证数据的完整性,不会由于主机的奔溃而导致数据的丢失，主机不需要配置,从机需要对其进行配置

### 2. 基本设置:一台主机,多台从机

#### （1）配置文件

 在从服务器的配置文件中加入：slaveof <masterip> <masterport>

#### （2）启动命令

 redis-server启动命令后加入 --slaveof <masterip> <masterport>

#### （3）客户端命令

 Redis服务器启动后，直接通过客户端执行命令：slaveof <masterip> <masterport>，则该Redis实例成为从节点。

 上述3种方式是等效的，下面以客户端命令的方式为例，看一下当执行了slaveof后，Redis主节点和从节点的变化,配置文件只需要配置一次,而其他的方式在启动时便无需再对其进行配置

 ### 3. 断开连接

 \- 可以通过slaveof no one断开。需要注意的是，从节点断开复制后，不会删除已有的数据，只是不再接受主节点新的数据变化
 \- 重新作为其他服务器的备份服务器,都会进行一次全局备份,将从库中原有内容删去,然后主库会将所有的内容发送过来

### 4. 数据保持一致的方式

 当从服务器刚启动时,会使用全量备份,数据会被发送到从库中去

 在之后会进行增量的备份

 这种方式的坏处:在数据备份时,都是使用tcp连接来传输包的方式,这样会增加主机的压力,并且在主机奔溃之后,redis服务便不可用,从库并不会自动切换为主库

 

 ### 5. 从机主机之间的关系

#### （1)一主多从

- 基本概念

    一台主机存在多台与之连接的从机

- 坏处

- 当主机宕机后,从机并不会自动切换为主机,redis服务无法使用
  
  - 多台从机同时连接,备份时会增加主机的压力

####  (2)薪火相传

- 基本概念

一台主机,与主机相连的从机可以作为从机,也可以作为下一台机器的主机

- 好处

- 减轻了主机同步数据的压力
  - 当主机奔溃之后,中间从机执行命令slaveof no one,该从库自动切换为主库

- 坏处
  - 数据的同步具有一定的延迟性
  - 奔溃的主机恢复回来之后,与切换为主库的从库之间并没有任何关系
  - 从库切换为主库需要手动执行命令

#### (3) 哨兵模式

- 基本概念

   同样是配置为一机多从的模式,或者是薪火相传的模式,但是需要在配置文件的位置处添加sentinel.conf文件,配置好要监视的主机的ip和端口号,当主机奔溃之后,可以让哨兵检查到,便能够自动将从库替换成主库,并且原先的从库也会自动转换过来作为新主机的从库

   以前奔溃的redis数据库恢复过来也会自动成为新主机的从库

- 启动哨兵的命令

   redis-sentinel /myredis/sentinel.conf 

- 一组sentinel能够同时监视多组数据库



### 数据备份操作



 

   

 


