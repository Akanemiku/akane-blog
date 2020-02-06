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

## Personal blog based on SpringBoot + Thyemeleaf

### 1. Technology stack

#### Back-End

- Core framework: Springboot 2.1.3
- Database: MySQL 8.0.13
- Pool: Druid
- Cache: Redis + SpringBoot-Cache
- MarkDown: Flexmark
- Others: Lombok

#### Fore-End

- Thymeleaf
- Bootstrap 3
- JQuery
- MarkDown editor: Editor.md
- Alert: SweetAlert

### 2. Interface display

#### Blog

![主页1.png](/doc/主页1.png)

![主页2.gif](/doc/主页2.gif)

![主页3.gif](/doc/主页3.gif)

#### Admin

![后台.gif](/doc/后台.gif)

### 3. Build&Run

- Import the project in the form of ``maven``
- Create new Datebase，importing sql``doc/akane_blog.sql``
- Modify ``/src/main/resources/application.yml`` file
  - Modify database connection url、username and password
  - If cache is used, modify ``redis`` host and password，if not use then delete ``@EnableCaching``
- Running projects, executing ``AkaneBlogApplication.java``
  - Modify port in ``yml``，default: 8081
- Input ``http://localhost:8081/`` in browser，``http://localhost:8081/admin/login`` enter the admin page


### 4. Following plan...

- [ ] 1. JWT+SpringSecurity for Permission check
- [ ] 2. Restful API Protect
- [ ] 3. Change frame into SpringCloud，with Eureka+Feign/Hystrix+Gateway
- [ ] 4. Message queue

### 5. Contect

QQ：806394913

Email：yux.liu@foxmail.com

### 6. Protocol

[MIT License](https://magicdawn.mit-license.org/)

Copyright (c) 2019-present, Akanemiku
