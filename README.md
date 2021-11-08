## 平台简介

本项目基于huangpuguang和蘑菇博客扩展而来
## 系统模块

~~~
com.huangpuguang     
├── procon-web              // 前端框架 [80]
├── procon-gateway         // 网关模块 [8080]
├── procon-auth            // 认证中心 [9200]
├── procon-api             // 接口模块
│       └── api-system                          // 系统接口
├── procon-common          // 通用模块
│       └── common-core                         // 核心模块
│       └── common-datascope                    // 权限范围
│       └── common-datasource                   // 多数据源
│       └── common-log                          // 日志记录
│       └── common-redis                        // 缓存服务
│       └── common-security                     // 安全模块
│       └── common-swagger                      // 系统接口
├── procon-services      // 业务模块
│       └── system-service                              // 系统模块 [9201]
│       └── gen-service                                  // 代码生成 [9202]
│       └── job-sevice                                 // 定时任务 [9203]
│       └── file-service                                // 文件服务 [9300]
├──pom.xml                // 公共依赖
~~~

## 架构图

<img src="https://oscimg.oschina.net/oscnet/up-82e9722ecb846786405a904bafcf19f73f3.png"/>
