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

INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (69, 11, 10700, 1, 1, '2023-07-15 18:09:13', '2023-07-15 18:09:13', null, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (70, 11, 10893, 2, 1, '2023-07-15 18:09:51', '2023-07-15 18:09:51', null, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (71, 11, 10283, 1, 1, '2023-07-15 18:10:19', '2023-07-15 18:10:19', null, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (72, 11, 10159, 2, 1, '2023-07-16 17:51:27', '2023-07-16 17:51:27', null, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (74, 11, 10755, 1, 1, '2023-07-16 18:06:30', '2023-07-16 18:06:30', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (75, 11, 10779, 1, 1, '2023-07-16 18:42:15', '2023-07-16 18:42:15', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (77, 11, 10283, 1, 1, '2023-07-16 20:35:40', '2023-07-16 20:35:40', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (79, 11, 10755, 1, 1, '2023-07-20 10:04:23', '2023-07-20 10:04:23', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (80, 11, 10742, 1, 1, '2023-07-20 20:44:44', '2023-07-20 20:44:44', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (81, 11, 10283, 1, 1, '2023-07-20 20:49:05', '2023-07-20 20:49:05', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (82, 11, 10700, 1, 1, '2023-07-20 21:37:18', '2023-07-20 21:37:18', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (83, 11, 10779, 1, 1, '2023-07-20 21:37:29', '2023-07-20 21:37:29', 11, null);
INSERT INTO db_wukong_shop.shop_cart_item (cart_item_id, user_id, goods_id, goods_count, is_deleted, create_time, update_time, create_by, update_by) VALUES (84, 11, 10159, 1, 0, '2023-07-20 21:41:40', '2023-07-20 21:41:40', 11, null);