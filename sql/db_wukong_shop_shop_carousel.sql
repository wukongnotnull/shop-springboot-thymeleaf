create table shop_carousel
(
    carousel_id   int auto_increment comment '首页轮播图主键id'
        primary key,
    carousel_url  varchar(200) default ''                not null comment '轮播图',
    redirect_url  varchar(200) default '#'               not null comment '点击后的跳转地址(默认不跳转)',
    carousel_rank int          default 0                 not null comment '排序值(字段越大越靠前)',
    is_deleted    tinyint      default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by     int          default 0                 not null comment '创建者id',
    update_time   datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    update_by     int          default 0                 not null comment '修改者id'
)
    charset = utf8;

INSERT INTO db_wukong_shop.shop_carousel (carousel_id, carousel_url, redirect_url, carousel_rank, is_deleted, create_time, create_by, update_time, update_by) VALUES (2, 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/7f46857e9876f168ae660dbfb0909169.jpeg?w=2452&h=920', '', 13, 0, '2019-11-29 00:00:00', 0, '2019-11-29 00:00:00', 0);
INSERT INTO db_wukong_shop.shop_carousel (carousel_id, carousel_url, redirect_url, carousel_rank, is_deleted, create_time, create_by, update_time, update_by) VALUES (5, 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0942f8338bc19a2cde9394479fc123c8.jpg?thumb=1&w=2452&h=920&f=webp&q=90', '', 0, 0, '2019-11-29 00:00:00', 0, '2019-11-29 00:00:00', 0);