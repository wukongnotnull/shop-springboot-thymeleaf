# 项目介绍
  基于 springboot 和 thymeleaf 的电商项目
  

## mac 系统升级，导致mysql 数据库无法启动解决方案
```bash
 sudo /usr/local/mysql/support-files/mysql.server start
 ```

## 下单和结算流程
- 购物页面
- 订单核算页面
- 订单详情页
- 支付方式选择页面
- 支付操作页面

## sql 语句
```sql
create table shop_admin_user
(
    admin_user_id   int auto_increment comment '管理员id'
        primary key,
    login_user_name varchar(50)       not null comment '管理员登陆名称',
    login_password  varchar(50)       not null comment '管理员登陆密码',
    nick_name       varchar(50)       not null comment '管理员显示昵称',
    locked          tinyint default 0 null comment '是否锁定 0未锁定 1已锁定无法登陆'
)
    charset = utf8;

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

create table shop_cart_item
(
    cart_item_id bigint auto_increment comment '购物项主键id'
        primary key,
    user_id      bigint                             not null comment '用户主键id',
    goods_id     bigint   default 0                 not null comment '关联商品id',
    goods_count  int      default 1                 not null comment '数量(最大为5)',
    is_deleted   tinyint  default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null comment '最新修改时间',
    create_by    bigint                             null comment '创建人',
    update_by    bigint                             null comment '更新人'
)
    charset = utf8;

create table shop_goods_category
(
    category_id    bigint auto_increment comment '分类id'
        primary key,
    category_level tinyint     default 0                 not null comment '分类级别(1-一级分类 2-二级分类 3-三级分类)',
    parent_id      bigint      default 0                 not null comment '父分类id',
    category_name  varchar(50) default ''                not null comment '分类名称',
    category_rank  int         default 0                 not null comment '排序值(字段越大越靠前)',
    is_deleted     tinyint     default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time    datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by      int         default 0                 not null comment '创建者id',
    update_time    datetime    default CURRENT_TIMESTAMP not null comment '修改时间',
    update_by      int         default 0                 null comment '修改者id'
)
    charset = utf8;

create table shop_goods_detail
(
    goods_id             bigint unsigned auto_increment comment '商品表主键id'
        primary key,
    goods_name           varchar(200) default ''                           not null comment '商品名',
    goods_intro          varchar(200) default ''                           not null comment '商品简介',
    goods_category_id    bigint       default 0                            not null comment '关联分类id',
    goods_cover_img      varchar(200) default '/admin/dist/img/no-img.png' not null comment '商品主图',
    goods_carousels      varchar(500) default '/admin/dist/img/no-img.png' not null comment '商品轮播图',
    goods_detail_content text                                              not null comment '商品详情',
    original_price       int          default 1                            not null comment '商品价格',
    selling_price        int          default 1                            not null comment '商品实际售价',
    stock_num            int          default 0                            not null comment '商品库存数量',
    tag                  varchar(20)  default ''                           not null comment '商品标签',
    goods_sell_status    tinyint      default 0                            not null comment '商品上架状态 0-下架 1-上架',
    create_by            int          default 0                            not null comment '添加者主键id',
    create_time          datetime     default CURRENT_TIMESTAMP            not null comment '商品添加时间',
    update_by            int          default 0                            not null comment '修改者主键id',
    update_time          datetime     default CURRENT_TIMESTAMP            not null comment '商品修改时间',
    is_deleted           tinyint      default 0                            null comment '删除标识字段（1-已删除，0-未删除）'
)
    charset = utf8;

create table shop_index_config
(
    config_id    bigint auto_increment comment '首页配置项主键id'
        primary key,
    config_name  varchar(50)  default ''                not null comment '显示字符(配置搜索时不可为空，其他可为空)',
    config_type  tinyint      default 0                 not null comment '1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐',
    goods_id     bigint       default 0                 not null comment '商品id 默认为0',
    redirect_url varchar(100) default '##'              not null comment '点击后的跳转地址(默认不跳转)',
    config_rank  int          default 0                 not null comment '排序值(字段越大越靠前)',
    is_deleted   tinyint      default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by    int          default 0                 not null comment '创建者id',
    update_time  datetime     default CURRENT_TIMESTAMP not null comment '最新修改时间',
    update_by    int          default 0                 null comment '修改者id'
)
    charset = utf8;

create table shop_order
(
    order_id     bigint auto_increment comment '订单表主键id'
        primary key,
    order_no     varchar(20)  default ''                not null comment '订单号',
    user_id      bigint       default 0                 not null comment '用户主键id',
    total_price  int          default 1                 not null comment '订单总价',
    pay_status   tinyint      default 0                 not null comment '支付状态:0.未支付,1.支付成功,-1:支付失败',
    pay_time     datetime                               null comment '支付时间',
    order_status tinyint      default 0                 not null comment '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
    extra_info   varchar(100) default ''                not null comment '订单body',
    user_name    varchar(30)  default ''                not null comment '收货人姓名',
    user_phone   varchar(11)  default ''                not null comment '收货人手机号',
    user_address varchar(100) default ''                not null comment '收货人收货地址',
    is_deleted   tinyint      default 0                 not null comment '删除标识字段(0-未删除 1-已删除)',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime     default CURRENT_TIMESTAMP not null comment '最新修改时间',
    pay_method   tinyint      default 0                 not null comment '支付方式：0.无 1.支付宝支付 2.微信支付'
)
    charset = utf8;

create table shop_order_item
(
    order_item_id   bigint auto_increment comment '订单关联购物项主键id'
        primary key,
    order_id        bigint       default 0                 not null comment '订单主键id',
    goods_id        bigint       default 0                 not null comment '关联商品id',
    goods_name      varchar(200) default ''                not null comment '下单时商品的名称(订单快照)',
    goods_cover_img varchar(200) default ''                not null comment '下单时商品的主图(订单快照)',
    selling_price   int          default 1                 not null comment '下单时商品的价格(订单快照)',
    goods_count     int          default 1                 not null comment '数量(订单快照)',
    create_time     datetime     default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '订单快照表（订单明细表）' charset = utf8;

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



```