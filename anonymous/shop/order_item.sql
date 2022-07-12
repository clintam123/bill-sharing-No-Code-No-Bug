create table if not exists order_item
(
    id          bigint auto_increment
        primary key,
    product_id  bigint             not null,
    order_id    bigint             not null,
    quantity    smallint default 0 not null,
    created_at  datetime           not null,
    updated_at  datetime           null,
    content     text               null,
    customer_id bigint             not null,
    total       float              not null,
    constraint fk_order_item_customer1
        foreign key (customer_id) references customer (id),
    constraint fk_order_item_order
        foreign key (order_id) references `order` (id),
    constraint fk_order_item_product
        foreign key (product_id) references product (id)
);

create index fk_order_item_customer1_idx
    on order_item (customer_id);

create index idx_order_item_order
    on order_item (order_id);

create index idx_order_item_product
    on order_item (product_id);

