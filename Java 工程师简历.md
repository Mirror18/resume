## 个人信息

| 姓名     | 马凯                                   |
| :------- | -------------------------------------- |
| 年龄     | 23                                     |
| 所在城市 | 广州                                   |
| 期待职位 | Java 程序员、Python 程序员、C++ 程序员 |
| 期待薪资 | 8K                                     |
| 技术博客 | https://mirror18.github.io/            |
| Github   | https://github.com/Mirror18            |
| 在线简历 | https://github.com/Mirror18/resume     |



## 联系方式

| 手机   | 16650010560            |
| ------ | ---------------------- |
| Email  | mirrorcloud3@gmail.com |
| 微信号 | sweetboy2331           |



## 教育

2019 - 2023 	洛阳理工学院	本科(土木工程专业)



## 技能清单

以下均为我熟练使用的技能

| 编程语言   | Java、Python、C&C++                                          |
| ---------- | ------------------------------------------------------------ |
| 后端框架   | SpringFramework、Spring MVC、SpringBoot、SpringCloud         |
| 前端框架   | 前端三件套、Vue 框架                                         |
| 数据库     | MySQL5.7、Redis                                              |
| 持久化框架 | MyBatis、JDBC                                                |
| 开发工具   | git、virtualBox、Ubuntu、Docker、Postman、Navicat、Another Redis |
| 编程工具   | IDEA、VSCode                                                 |
| 文档编写   | markdown、Word、PowerPoint、Excel                            |



## 项目

* 基于SpringBoot开发的管理系统，[项目地址](https://github.com/Mirror18/Java_leaning/tree/main/sky_take_out)
* 基于SpringCloud开发的简易证券交易
* 使用Django框架开发一个blog
* 手写简易spring框架
* 手写简易Tomcat服务器

## 项目细节

### SpingBoot管理系统

1. 主要特色是前后端分离。
2. 前端分为两部分，一个面向用户的微信小程序，第二个是面对管理端的网页。管理端的网页则是通过vue进行编写。
3. 中间的数据传递载体是json,前后端映射路径不一致，则是通过nginx来进行端口映射。同时也可以开启负载均衡策略。
4. 数据持久化是采用mysql，但针对经常调用的数据是redis这个nosql进行存储。
5. nginx，mysql，redis的部署，则是通过虚拟机的docker进行部署，特点就是部署方便快捷，启动时只需配置好端口映射和文件映射，即可无障碍访问部署的项目。
6. 后端编写主要是用SpringBoot、Spirng MVC框架为主，主要编写Spring MVC内的实体类，公共类，服务模块。
7. 基于SpringTask这个组件而创建的定时任务。Excel报表的导出。前后端联调则是基于swagger。
14. 以上就是关于SpringBoot项目的一个粗略介绍。详细内容在我所存放项目的github仓库。主要是对于其中所用到的东西进行一个全而细致的说明如何使用。

### SpringCloud证券交易

1. 基于SpringCloud。分为API模块、定序模块、交易引擎、行情模块、推送模块、UI模块。
2. 借助parent模块简化依赖管理、借助Spring Cloud Config 解决多模块读取配置文件问题。
3. 使用docker完成本地开发环境的配置，主要配置MySQL、Redis、Kafka、ZooKeeper。
4. 交易引擎是最重要的模块，是基于事件驱动，还要保证交易完整性。这里采用内存来解决交易完整性，比起使用数据库事务要快上不少。为了解决多线程并发所带来的问题使用ConcurrentMap来保证线程安全。撮合引擎则是用红黑树进行排序。因为事件驱动，所以则通过订阅Kafka的Topic实现批量读消息。
5. 定序系统为了给消息定序，则创建了微型区块链，原理是单向链表。
6. API系统则是一个Servlet服务，主要要对请求进来的信息做一个验证和提供一些基本服务，例如查询redis缓存结果，创建新订单等。
7. 基于Redis和WebSocket推送JSON格式，使用Vert.x完成推送服务。
8. 编写UI则就和上述的spring Boot框架开发流程一致了。

### 手写Spring 和 Tomcat项目

则是参考源码和网上博客实现了简易的这两个服务。但没看懂太多。

参考文档来自廖雪峰和source-code-hunter

### Django博客

1. 项目介绍: 借助 Python中的Django框架开发blog站点。
2. 使用 MTV 模式编写站点应用，model编写处理数据的类。view编写页面展示的类和方法。template是前端页面骨架
3. 采用MySQL存储管理用户数据。利用表单提交数据。MySQL通过ubuntu的docker安装使用
4. 根据需求加入评论，三方登陆，文章，通知，标签，点赞。等应用
5. 模板的前端页面使用bootstrap4，layui框架布局站点页面。
6. 使用git提交到github。在云端使用git clone 下载代码。使用Neginx，Gunicorn部署到云端

## 致谢

感谢您花时间阅读我的简历,期待能有机会和您共事。