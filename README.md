 ### flume示例
1. [入门示例-监控端口消息至控制台](primer-example)
2. [单节点文件至控制台](singlefile2console)
3. [单节点文件至hdfs](singlefile2hdfs)
4. [Spooldir Source](spoolingsource)
5. [Taildir Source](taildirsource)
    无法使用Exec source因为Exec无法保证数据不丢失，Spooldir Source能够保证数据不丢失，且能够实现断点续传，存在延迟，不能实时监控；Taildir Source支持断点续传，也可以保证数据不丢失并且低延迟支持实时监控。
6. [复制和多路复用](single-source-mutil-sink)
7. [故障转移](single-source-failover-sink)
8. [负载均衡](single-source-failover-sink)
