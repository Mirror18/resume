## 个人信息

| 姓名     | 马凯                                   |
| :------- | -------------------------------------- |
| 年龄     | 23                                     |
| 所在城市 | 广州                                   |
| 期待职位 | Java 程序员、Python 程序员、C++ 程序员 |
| 期待薪资 | 8K                                     |
| 技术博客 | https://mirror18.github.io/            |
| Github   | https://github.com/Mirror18            |
| 在线简历 | https://mirror18.github.io/resume/     |

## 联系方式

| 手机   | 16650010560            |
| ------ | ---------------------- |
| Email  | mirrorcloud3@gmail.com |
| 微信号 | sweetboy2331           |



## 教育

2019 - 2023 	洛阳理工学院	本科

## 技能清单

以下均为我熟练使用的技能

| 编程语言   | Java、Python、C&C++                                          |
| ---------- | ------------------------------------------------------------ |
| 后端框架   | SpringFramework、Spring MVC、SpringBoot、SpringCloud、       |
| 前端框架   | 前端三件套、vue 框架                                         |
| 数据库     | MySQL5.7、redis                                              |
| 持久化框架 | MyBatis、JDBC                                                |
| 开发工具   | git、virtualBox、Ubuntu、Docker、Postman、Navicat、Another Redis |
| 编程工具   | IDEA、VSCode、                                               |
| 文档编写   | markdown、Word、PowerPoint、Excel                            |


## 社团和组织经历

* 洛阳理工学院
  2019.10 - 2021.06
  宣传部部长
  日常工作拍照记录摄影,让外界更好的了解到青协的日常活动。
  担任校园海报大赛的承办方负责人,协调评委,场地时间。调用百度识图api 初审参赛者作品
  组织宣传部成员纳新,每次活动后审核宣传部成员提交的图片文案

## 项目

* 基于SpringBoot开发的管理系统，[项目地址](https://github.com/Mirror18/Java_leaning/tree/main/sky_take_out)
* 基于SpringCloud开发的简易证券交易
* 使用Django框架开发一个blog
* 手写简易spring框架
* 手写简易Tomcat服务器

## 项目细节

### SpingBoot管理系统

      1. 主要特色是前后端分离。
      2. 前端分为两部分，一个面向用户的微信小程序，第二个是面对管理端的网页。
      3. 而管理端的网页则是通过vue进行编写。
      4. 中间的数据传递载体是json,前后端映射路径不一致，则是通过nginx来进行端口映射。同时也可以开启负载均衡策略。
      5. 数据持久化是采用mysql，但针对经常调用的数据是redis这个nosql进行存储。
      6. nginx，mysql，redis的部署，则是通过虚拟机的docker进行部署，特点就是部署方便快捷，启动时只需配置好端口映射和文件映射，即可无障碍访问部署的项目。
      7. 后端编写主要是用SpringBoot、Spirng MVC框架为主，主要编写Spring MVC内的实体类，公共类，服务模块。
      8. 在公共模块中编写一些要用到的类，例如自定义的常量类，枚举类，还有一些需要捕获的异常类，其中最重要的就是转换类和工具类，结果类，上下文传递类。但是对于结果的封装，数据在程序间的传递，还有传递给view的数据转换都是至关重要。
      9. 实体类，主要是对于前端发来的request的所使用的类接收，还有response的类，针对MySQL的实体类进行的定义。
      10. server模块，通过编写controller层，调用ServiceImpl方法，调用mapper接口，通过SpringBoot的框架进行对数据库的增删查改。mapper的接口是调用MyBatis这个组件的。
      11. 基于SpringTask这个组件而创建的定时任务。主要针对的就是在用户端操作后有一个默认时限需要执行的任务。如果用户没有操作的话。
      12. Excel报表的导出。
      13. 前后端联调则是基于swagger，可以在开发的时候测试书写是否正确。
      14. 以上就是关于SpringBoot框架的一个粗略介绍。详细内容在我所存放项目的github仓库。主要是对于其中所用到的东西进行一个全而细致的说明如何使用。

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

1. 项目介绍: 借助 Python中的Django框架开发blog站点。demo项目特点

2. 使用 venv 创建虚拟环境，配制所用环境。

3. 使用Django开发的时候，使用 MTV 模式编写站点应用，model编写处理数据的类。view编写页面展示的类和方法。template是前端页面骨架

4. 采用MySQL存储管理用户数据。利用表单提交数据。MySQL通过ubuntu的docker安装使用

5. 根据需求加入评论，三方登陆，文章，通知，标签，点赞。等应用

6. 模板的前端页面使用bootstrap4，layui框架布局站点页面。

7. 使用git提交到github。在云端使用git clone 下载代码。使用Neginx，Gunicorn部署到云端

1. 熟悉设计模式，创建型模式，结构型模式，行为型模式

   1. 创建型模式，对象的创建和使用想分离，两者可以互相转换。著名的工厂方法
   2. 结构型模式，如何组合各种对象，用于获取更好，更灵活的结构，例如装饰器
   3. 行为型模式，算法和对象之间的职责分配，通过对象组合，行为行模式可以描述一组对象应该如何协作完成一个整体任务。例如迭代器7.掌握计算机网络，计算机组成原理，计算机操作系统，数据结构，等相关内容。

## 致谢

感谢您花时间阅读我的简历,期待能有机会和您共事。