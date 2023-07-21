create table shop_ordinary_user
(
    user_id        bigint auto_increment comment '用户主键id'
        primary key,
    nick_name      varchar(50)  default ''                not null comment '用户昵称',
    login_name     varchar(11)  default ''                not null comment '登陆名称(默认为手机号)',
    password_md5   varchar(32)  default ''                not null comment 'MD5加密后的密码',
    introduce_sign varchar(100) default ''                not null comment '个性签名',
    address        varchar(100) default ''                not null comment '收货地址',
    is_deleted     tinyint      default 0                 not null comment '注销标识字段(0-正常 1-已注销)',
    locked_flag    tinyint      default 0                 not null comment '锁定标识字段(0-未锁定 1-已锁定)',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '注册时间',
    create_by      bigint       default 0                 null comment '创建人',
    update_time    datetime     default CURRENT_TIMESTAMP null comment '修改时间',
    update_by      bigint       default 0                 null comment '修改人',
    version        int          default 0                 null comment '当前版本'
)
    charset = utf8;

INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (6, '测试用户1', '13711113333', 'dda01dc6d334badcd031102be6bee182', '测试用户1', 'XXXXXXXXXXXXXX', 0, 0, '2019-08-29 10:51:39', 0, '2023-07-17 07:43:22', 6, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (7, '测试用户2测试用户2测试用户2测试用户2', '13811113333', 'dda01dc6d334badcd031102be6bee182', '测试用户2', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, 0, '2019-08-29 10:55:08', 0, '2023-07-14 15:56:38', 0, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (8, '测试用户3', '13911113333', 'dda01dc6d334badcd031102be6bee182', '测试用户3', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, 0, '2019-08-29 10:55:16', 0, '2023-07-14 15:56:38', 0, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (10, '齐天大圣', '悟空非空也', '', '我是至尊宝', '', 0, 0, '2023-07-15 08:07:54', 0, '2023-07-15 08:07:54', 0, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (11, '15312345676', '15312345676', '0370d67d81acf9c0e824f568fadfbcb5', '', 'seeddd22sss', 0, 0, '2023-07-15 10:48:29', 0, '2023-07-20 02:12:40', 11, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (12, '15312345677', '15312345677', '4e7f0086eea300ee2cce99a10820c09e', '', '', 0, 0, '2023-07-15 10:51:10', 0, '2023-07-15 10:51:10', 0, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (13, '15312345678', '15312345678', '7bb72ba2953a0a800d8fd24018d512af', '', '', 0, 0, '2023-07-15 10:53:34', 0, '2023-07-15 10:53:34', 0, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (14, '15311111111', '15311111111', '81dc9bdb52d04dc20036dbd8313ed055', '', '', 0, 0, '2023-07-15 20:37:49', 0, '2023-07-15 20:37:49', 0, 0);
INSERT INTO db_wukong_shop.shop_ordinary_user (user_id, nick_name, login_name, password_md5, introduce_sign, address, is_deleted, locked_flag, create_time, create_by, update_time, update_by, version) VALUES (15, '13612345678', '13612345678', '6844fabc5d3545adff51d7ff3497ab64', '', '', 0, 0, '2023-07-15 20:42:47', 0, '2023-07-15 20:42:47', 0, 0);