-- 博客系统数据库
-- 删除原有数据库
DROP DATABASE if EXISTS blog;
CREATE DATABASE blog;
USE blog;

-- 创建表
-- 文章表
CREATE TABLE blog_article
(
    `article_id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '文章ID',
    `article_title`         VARCHAR(60)  NOT NULL DEFAULT '' COMMENT '文章标题',
    `article_digest`        VARCHAR(255) NOT NULL DEFAULT '' COMMENT '文章摘要',
    `article_content`       LONGTEXT     NOT NULL COMMENT '文章内容',
    `article_create_time`   TIMESTAMP    NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '文章创建时间',
    `article_update_time`   TIMESTAMP    NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '文章更新时间',
    `article_read_count`    INT          NOT NULL DEFAULT 0 COMMENT '文章阅读数',
    `article_comment_count` INT          NOT NULL DEFAULT 0 COMMENT '文章评论数',
    `article_set_top`       TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '文章是否置顶，0:否; 1:是',
    `article_deleted`       TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '文章是否删除，0:否; 1:是',
    `article_ip`            VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '创建文章的IP',
    `article_user_id`       BIGINT(20)   NOT NULL DEFAULT 0 COMMENT '发布的用户的ID',
    `article_tag_id`        BIGINT(20)   NOT NULL DEFAULT 0 COMMENT '所属标签的ID',
    PRIMARY KEY (article_id),
    KEY article_user_id (article_user_id),
    KEY article_tag_id (article_tag_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8 COMMENT '文章表';

-- 用户表
CREATE TABLE blog_user
(
    `user_id`                BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_name`              VARCHAR(20)  NOT NULL COMMENT '用户名',
    `user_password`          VARCHAR(32)  NOT NULL COMMENT '用户密码',
    `user_nickname`          VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '用户昵称',
    `user_email`             VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '用户邮箱',
    `user_avatar`            VARCHAR(255) NOT NULL DEFAULT '' COMMENT '用户照片',
    `user_registration_time` TIMESTAMP    NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册时间',
    `user_introduction`      LONGTEXT     NOT NULL COMMENT '用户自我简介',
    PRIMARY KEY (user_id),
    KEY user_name (user_name),
    KEY user_nickname (user_nickname),
    KEY user_email (user_email)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8 COMMENT '用户表';

-- 登录日志表
CREATE TABLE blog_loginlog
(
    `loginlog_user_id`       BIGINT(20)  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `loginlog_user_password` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户密码',
    `loginlog_ip`            VARCHAR(20) NOT NULL DEFAULT '' COMMENT '登录IP地址',
    `loginlog_time`          TIMESTAMP   NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '登陆时间',
    KEY user_loginlog (loginlog_user_id, loginlog_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8 COMMENT '登录日志表';

-- 文章标签表
CREATE TABLE blog_tag
(
    `tag_id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '标签ID',
    `tag_name`        VARCHAR(20) NOT NULL DEFAULT '' COMMENT '标签名',
    `tag_create_time` TIMESTAMP   NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '标签创建时间',
    PRIMARY KEY (tag_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8 COMMENT '标签表';

-- 评论表
CREATE TABLE blog_comment
(
    `comment_id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `comment_username`    VARCHAR(20) NOT NULL DEFAULT '' COMMENT '评论用户名',
    `comment_content`     LONGTEXT    NOT NULL COMMENT '评论内容',
    `comment_ip`          VARCHAR(20) NOT NULL DEFAULT '' COMMENT '评论IP',
    `comment_parent_id`   BIGINT(20)  NOT NULL DEFAULT 0 COMMENT '评论对应的父ID，为0代表顶级评论',
    `comment_base_id`     BIGINT(20)  NOT NULL DEFAULT 0 COMMENT '评论所属的第一个评论ID，为0代表顶级评论',
    `comment_create_time` TIMESTAMP   NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '评论创建时间',
    `comment_place`       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '评论地点',
    `comment_type`        TINYINT(1)  NOT NULL DEFAULT 0 COMMENT '评论类型，0:留言; 1:文章评论',
    `comment_article_id`  BIGINT(20)  NOT NULL DEFAULT -1 COMMENT '评论的文章的ID，-1代表是留言',
    PRIMARY KEY (comment_id),
    KEY comment_username (comment_username),
    KEY comment_ip (comment_ip),
    KEY comment_create_time (comment_create_time),
    KEY comment_place (comment_place),
    KEY comment_type (comment_type),
    KEY comment_article_id (comment_article_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8 COMMENT '评论表';

-- 友链表
CREATE TABLE blog_link
(
    `link_id`          BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '友链ID',
    `link_name`        VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '友链名称',
    `link_site`        VARCHAR(255) NOT NULL DEFAULT '' COMMENT '友链网址',
    `link_avatar`      VARCHAR(255) NOT NULL DEFAULT '' COMMENT '友链头像',
    `link_description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '友链描述',
    `link_create_time` TIMESTAMP    NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '友链创建时间',
    `link_user_id`     BIGINT(20)   NOT NULL DEFAULT 0 COMMENT '友链所属的用户ID',
    PRIMARY KEY (link_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8 COMMENT '友链表';