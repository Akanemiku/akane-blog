<p align=center>
  <a href="#">
    <img src="./doc/favicon.png" alt="Akane Blog" style="width:150px;height:150px">
  </a>
</p>

# Akane Blog

![LICENSE](https://img.shields.io/badge/License-MIT-blue.svg)
![LICENSE](https://img.shields.io/badge/JDK-1.8%2B-brightgreen)
![LICENSE](https://img.shields.io/badge/SpringBoot-2.1.3.RELEASE-brightgreen)
![LICENSE](https://img.shields.io/badge/Redis-2.1.3.RELEASE-red)
![LICENSE](https://img.shields.io/badge/Druid-1.1.10-yellow) 
![LICENSE](https://img.shields.io/badge/Swagger2-2.8.0-brightgreen)

## 基于 SpringBoot + Thyemeleaf 的个人博客

### 1.技术栈

#### 后端

- 核心框架: Springboot 2.1.3
- 数据库: MySQL 8.0.13
- 连接池: Druid
- 缓存: Redis + SpringBoot-Cache
- MarkDown处理: Flexmark
- 其他: Lombok

#### 前端

- Thymeleaf
- Bootstrap 3
- JQuery
- MarkDown编辑: Editor.md
- 弹窗: SweetAlert

### 2.界面展示

#### 前台博客

![主页1.png](/doc/主页1.png)

![主页2.gif](/doc/主页2.gif)

![主页3.gif](/doc/主页3.gif)

#### 后台管理

![后台.gif](/doc/后台3.gif)

### 3.构建及运行

- 以``maven``形式导入本项目
- 新建数据库，导入sql``doc/akane_blog.sql``
- 修改``/src/main/resources/application.yml``文件
  - 修改数据库连接url、username及password
  - 若使用缓存，则修改``redis``的host及password，不使用则在启动类中将``@EnableCaching``注释掉
- 运行项目，执行``AkaneBlogApplication.java``
  - 可在``yml``文件中修改端口，项目默认8081
- 浏览器输入``http://localhost:8081/``进入博客主页，``http://localhost:8081/admin/login``进入后台管理页面

> MySQL 8以下版本需要在pom中指定对应``mysql-connector-java``对应版本及修改``driver-class``为``com.mysql.jdbc.Driver``

### 4.后续计划

- [ ] 1. 使用JWT+SpringSecurity进行权限校验
- [ ] 2. 接口防护，现仅在前端进行基础防护
- [ ] 3. 架构更改为SpringCloud，使用Eureka+Feign/Hystrix+Gateway
- [ ] 4. 使用消息队列

### 5. 联系方式

QQ：806394913

Email：yux.liu@foxmail.com

### 6. 开源协议

[MIT License](https://magicdawn.mit-license.org/)

Copyright (c) 2019-present, Akanemiku
