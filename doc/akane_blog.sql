/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : akane_blog

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-02-06 20:57:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `coid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cid` int(10) unsigned DEFAULT '0',
  `created` int(10) unsigned DEFAULT '0',
  `author` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorId` int(10) unsigned DEFAULT '0',
  `ownerId` int(10) unsigned DEFAULT '0',
  `email` varchar(200) DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client` varchar(200) DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `status` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`coid`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `created` (`created`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('9', '39', '1580992172', '刘长卿', null, null, '123@qq.com', 'https://github.com/Akanemiku/akane-blog', '192.168.163.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36', '写的太好啦', '1');
INSERT INTO `t_comment` VALUES ('10', '39', '1580992217', '徐嘉熙', null, null, '123@163.com', 'https://github.com/Akanemiku/akane-blog', '192.168.163.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36', '不错！！！', '1');
INSERT INTO `t_comment` VALUES ('11', '40', '1580992250', '唐舞桐', null, null, '123@foxmail.com', 'https://github.com/Akanemiku/akane-blog', '192.168.163.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36', '博主真厉害！', '1');

-- ----------------------------
-- Table structure for t_content
-- ----------------------------
DROP TABLE IF EXISTS `t_content`;
CREATE TABLE `t_content` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `slug` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `modified` int(10) unsigned DEFAULT '0',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容文字',
  `authorId` int(10) unsigned DEFAULT '0',
  `type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'post',
  `status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'publish',
  `tags` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `categories` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `hits` int(10) unsigned DEFAULT '0',
  `commentsNum` int(10) unsigned DEFAULT '0',
  `allowComment` tinyint(1) DEFAULT '1',
  `allowPing` tinyint(1) DEFAULT '1',
  `allowFeed` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`cid`) USING BTREE,
  UNIQUE KEY `slug` (`slug`) USING BTREE,
  KEY `created` (`created`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_content
-- ----------------------------
INSERT INTO `t_content` VALUES ('34', '测试标题1', null, '1580273781', '1580288181', '## Welcome to GitHub Pages\r\n\r\nYou can use the [editor on GitHub](https://github.com/Akanemiku/MingMing/edit/master/README.md) to maintain and preview the content for your website in Markdown files.\r\n\r\nWhenever you commit to this repository, GitHub Pages will run [Jekyll](https://jekyllrb.com/) to rebuild the pages in your site, from the content in your Markdown files.\r\n\r\n### Markdown\r\n\r\nMarkdown is a lightweight and easy-to-use syntax for styling your writing. It includes conventions for\r\n\r\n```markdown\r\nSyntax highlighted code block\r\n\r\n# Header 1\r\n## Header 2\r\n### Header 3\r\n\r\n- Bulleted\r\n- List\r\n\r\n1. Numbered\r\n2. List\r\n\r\n**Bold** and _Italic_ and `Code` text\r\n\r\n[Link](url) and ![Image](src)\r\n```\r\n\r\nFor more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/).\r\n\r\n### Jekyll Themes\r\n\r\nYour Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/Akanemiku/MingMing/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.\r\n\r\n### Support or Contact\r\n\r\nHaving trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.', null, 'post', 'publish', 'test', '默认分类', '29', '1', '1', null, null);
INSERT INTO `t_content` VALUES ('39', 'Spring Security 介绍', null, '1580520204', '1580520204', '## Spring Security 介绍\r\n\r\nSpring Security 应该属于 Spring 全家桶中学习曲线比较陡峭的几个模块之一，下面我将从起源和定义这两个方面来简单介绍一下它。\r\n\r\n- **起源：** Spring Security 实际上起源于 Acegi Security，这个框架能为基于 Spring 的企业应用提供强大而灵活安全访问控制解决方案，并且框架这个充分利用 Spring 的 IoC 和 AOP 功能，提供声明式安全访问控制的功能。后面，随着这个项目发展， Acegi Security 成为了Spring官方子项目，后来被命名为 “Spring Security”。\r\n- **定义：**Spring Security 是一个功能强大且高度可以定制的框架，侧重于为Java 应用程序提供身份验证和授权。——[官方介绍](https://spring.io/projects/spring-security)。', null, 'post', 'publish', 'Spring', 'SpringBoot', '5', '2', '1', null, null);
INSERT INTO `t_content` VALUES ('40', '测试文章2', null, '1580411575', '1580411575', '# Test2\r\n\r\n### 跨域\r\n\r\n如果你没有设置`exposedHeaders(\"Authorization\")`暴露 header 中的\"Authorization\"属性给客户端应用程序的话，前端是获取不到 token 信息的。\r\n\r\n```java\r\n@Configuration\r\npublic class CorsConfiguration implements WebMvcConfigurer {\r\n\r\n    @Override\r\n    public void addCorsMappings(CorsRegistry registry) {\r\n        registry.addMapping(\"/**\")\r\n                .allowedOrigins(\"*\")\r\n                //暴露header中的其他属性给客户端应用程序\r\n                //如果不设置这个属性前端无法通过response header获取到Authorization也就是token\r\n                .exposedHeaders(\"Authorization\")\r\n                .allowCredentials(true)\r\n                .allowedMethods(\"GET\", \"POST\", \"DELETE\", \"PUT\")\r\n                .maxAge(3600);\r\n    }\r\n}\r\n```', null, 'post', 'publish', 'tag1', '测试分类', '4', '1', '1', null, null);

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `action` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '事件',
  `data` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据',
  `authorId` int(10) DEFAULT NULL COMMENT '作者编号',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip地址',
  `created` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('184', '修改个人信息', '{\"activated\":0,\"created\":1490756162,\"email\":\"864655735@qq.com\",\"groupName\":\"visitor\",\"logged\":0,\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"screenName\":\"admin\",\"uid\":1,\"username\":\"admin\"}', '1', '192.168.163.1', '1580613798');
INSERT INTO `t_log` VALUES ('185', '修改个人信息', '{\"activated\":0,\"created\":1490756162,\"email\":\"1234@qq.com\",\"groupName\":\"visitor\",\"logged\":0,\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"screenName\":\"admin\",\"uid\":1,\"username\":\"admin\"}', '1', '192.168.163.1', '1580613983');
INSERT INTO `t_log` VALUES ('186', '修改密码', '{\"activated\":0,\"created\":1490756162,\"email\":\"1234@qq.com\",\"groupName\":\"visitor\",\"logged\":0,\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"screenName\":\"admin\",\"uid\":1,\"username\":\"admin\"}', '1', '192.168.163.1', '1580615558');
INSERT INTO `t_log` VALUES ('187', '修改密码', '{\"activated\":0,\"created\":1490756162,\"email\":\"1234@qq.com\",\"groupName\":\"visitor\",\"logged\":0,\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"screenName\":\"admin\",\"uid\":1,\"username\":\"admin\"}', '1', '192.168.163.1', '1580615887');
INSERT INTO `t_log` VALUES ('188', '修改个人信息', '{\"activated\":0,\"created\":1490756162,\"email\":\"1234@qq.com\",\"groupName\":\"visitor\",\"logged\":0,\"password\":\"111111\",\"screenName\":\"admin\",\"uid\":1,\"username\":\"admin\"}', '1', '192.168.163.1', '1580615988');
INSERT INTO `t_log` VALUES ('189', '修改个人信息', '{\"activated\":0,\"created\":1490756162,\"email\":\"1234@qq.com\",\"groupName\":\"visitor\",\"logged\":0,\"password\":\"96e79218965eb72c92a549dd5a330112\",\"screenName\":\"admin\",\"uid\":1,\"username\":\"admin\"}', '1', '192.168.163.1', '1580616252');
INSERT INTO `t_log` VALUES ('190', '修改密码', '{\"activated\":0,\"created\":1490756162,\"email\":\"12345@qq.com\",\"groupName\":\"visitor\",\"logged\":0,\"password\":\"96e79218965eb72c92a549dd5a330112\",\"screenName\":\"admin\",\"uid\":1,\"username\":\"admin\"}', '1', '192.168.163.1', '1580616274');
INSERT INTO `t_log` VALUES ('191', '删除文章', '\"37\"', '1', '192.168.163.1', '1580714495');
INSERT INTO `t_log` VALUES ('193', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580892810');
INSERT INTO `t_log` VALUES ('194', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580912398');
INSERT INTO `t_log` VALUES ('195', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580912924');
INSERT INTO `t_log` VALUES ('196', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580961795');
INSERT INTO `t_log` VALUES ('197', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580974777');
INSERT INTO `t_log` VALUES ('198', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580974836');
INSERT INTO `t_log` VALUES ('199', '保存系统设置', '\"{\\\"social_zhihu\\\":\\\"\\\",\\\"social_resume\\\":\\\"a\\\",\\\"social_jianshu\\\":\\\"\\\",\\\"social_github\\\":\\\"https://github.com/Akanemiku\\\",\\\"social_twitter\\\":\\\"\\\",\\\"social_csdn\\\":\\\"\\\",\\\"social_weibo\\\":\\\"\\\"}\"', '1', '192.168.163.1', '1580974884');
INSERT INTO `t_log` VALUES ('200', '保存系统设置', '\"{\\\"site_record\\\":\\\"\\\",\\\"site_description\\\":\\\"a\\\",\\\"site_title\\\":\\\"a\\\",\\\"allow_install\\\":\\\"\\\"}\"', '1', '192.168.163.1', '1580974886');
INSERT INTO `t_log` VALUES ('201', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580975391');
INSERT INTO `t_log` VALUES ('202', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580989028');
INSERT INTO `t_log` VALUES ('203', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580989085');
INSERT INTO `t_log` VALUES ('204', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580989504');
INSERT INTO `t_log` VALUES ('205', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580990691');
INSERT INTO `t_log` VALUES ('206', '删除文章', '\"35\"', '1', '192.168.163.1', '1580991083');
INSERT INTO `t_log` VALUES ('207', '删除文章', '\"38\"', '1', '192.168.163.1', '1580991088');
INSERT INTO `t_log` VALUES ('208', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580991161');
INSERT INTO `t_log` VALUES ('209', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580992263');
INSERT INTO `t_log` VALUES ('210', '登录后台', '\"admin 用户\"', '1', '192.168.163.1', '1580993242');

-- ----------------------------
-- Table structure for t_meta
-- ----------------------------
DROP TABLE IF EXISTS `t_meta`;
CREATE TABLE `t_meta` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `slug` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `contentType` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`mid`) USING BTREE,
  KEY `slug` (`slug`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_meta
-- ----------------------------
INSERT INTO `t_meta` VALUES ('50', '默认分类', '默认分类', 'category', null, null, null);
INSERT INTO `t_meta` VALUES ('51', '测试分类', null, 'category', null, null, null);
INSERT INTO `t_meta` VALUES ('52', 'test', 'test', 'tag', null, null, null);
INSERT INTO `t_meta` VALUES ('72', 'Bing', 'https://cn.bing.com/', 'link', null, '', '1');
INSERT INTO `t_meta` VALUES ('75', 'Magi', 'https://magi.com/', 'link', null, '', '2');
INSERT INTO `t_meta` VALUES ('76', 'Spring', 'Spring', 'tag', null, null, null);
INSERT INTO `t_meta` VALUES ('77', 'SpringBoot', 'SpringBoot', 'category', null, null, null);
INSERT INTO `t_meta` VALUES ('78', 'tag1', 'tag1', 'tag', null, null, null);

-- ----------------------------
-- Table structure for t_option
-- ----------------------------
DROP TABLE IF EXISTS `t_option`;
CREATE TABLE `t_option` (
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `value` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_option
-- ----------------------------
INSERT INTO `t_option` VALUES ('allow_install', '', null);
INSERT INTO `t_option` VALUES ('site_description', '', null);
INSERT INTO `t_option` VALUES ('site_record', '', null);
INSERT INTO `t_option` VALUES ('site_title', '', null);
INSERT INTO `t_option` VALUES ('social_csdn', '', null);
INSERT INTO `t_option` VALUES ('social_github', 'https://github.com/Akanemiku', null);
INSERT INTO `t_option` VALUES ('social_jianshu', '', null);
INSERT INTO `t_option` VALUES ('social_resume', '', null);
INSERT INTO `t_option` VALUES ('social_twitter', '', null);
INSERT INTO `t_option` VALUES ('social_weibo', '', null);
INSERT INTO `t_option` VALUES ('social_zhihu', '', null);

-- ----------------------------
-- Table structure for t_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_relation`;
CREATE TABLE `t_relation` (
  `cid` int(10) unsigned NOT NULL,
  `mid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cid`,`mid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_relation
-- ----------------------------
INSERT INTO `t_relation` VALUES ('34', '50');
INSERT INTO `t_relation` VALUES ('34', '52');
INSERT INTO `t_relation` VALUES ('39', '50');
INSERT INTO `t_relation` VALUES ('39', '76');
INSERT INTO `t_relation` VALUES ('39', '77');
INSERT INTO `t_relation` VALUES ('40', '51');
INSERT INTO `t_relation` VALUES ('40', '78');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `homeUrl` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `screenName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `activated` int(10) unsigned DEFAULT '0',
  `logged` int(10) unsigned DEFAULT '0',
  `groupName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'visitor',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `name` (`username`) USING BTREE,
  UNIQUE KEY `mail` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '12345@qq.com', null, 'admin', '1577808000', '0', '0', 'admin');
