create schema bill_sharing;
use bill_sharing;

create table if not exists category
(
    id      bigint auto_increment
        primary key,
    title   varchar(75) not null,
    content text        null,
    code    varchar(45) not null
);

create table if not exists user
(
    id            bigint auto_increment
        primary key,
    passwordHash  varchar(32) not null,
    registered_at datetime    not null,
    last_login    datetime    null,
    username      varchar(45) not null
);

create table if not exists customer
(
    id         bigint      not null
        primary key,
    first_name varchar(45) not null,
    last_name  varchar(45) not null,
    phone      varchar(15) not null,
    email      varchar(45) not null,
    user_id    bigint      not null,
    admin      tinyint     not null,
    constraint fk_customer_user1
        foreign key (user_id) references user (id)
);

create index fk_customer_user1_idx
    on customer (user_id);

create table if not exists vendor
(
    id       bigint auto_increment
        primary key,
    intro    tinytext    not null,
    profile  text        not null,
    address  varchar(45) not null,
    city     varchar(45) not null,
    province varchar(45) not null,
    user_id  bigint      not null,
    constraint fk_vendor_user
        foreign key (user_id) references user (id)
);

create table if not exists `order`
(
    id          bigint auto_increment,
    session_id  varchar(100)       null,
    token       varchar(100)       null,
    status      smallint default 0 not null,
    shipping    float    default 0 not null,
    total       float    default 0 not null,
    discount    float    default 0 not null,
    grand_total float    default 0 not null,
    created_at  datetime           not null,
    updated_at  datetime           null,
    vendor_id   bigint             not null,
    customer_id bigint             not null,
    primary key (id),
    constraint fk_order_customer1
        foreign key (customer_id) references customer (id),
    constraint fk_order_vendor1
        foreign key (vendor_id) references vendor (id)
);

create index fk_order_customer1_idx
    on `order` (customer_id);

create index fk_order_vendor1_idx
    on `order` (vendor_id);

create table if not exists product
(
    id          bigint auto_increment
        primary key,
    title       varchar(75)          not null,
    description text                 null,
    sku         varchar(100)         not null,
    price       float      default 0 not null,
    discount    float      default 0 null,
    quantity    smallint   default 0 not null,
    status      tinyint(1) default 0 not null,
    created_at  datetime             not null,
    updated_at  datetime             null,
    vendor_id   bigint               not null,
    constraint fk_product_vendor1
        foreign key (vendor_id) references vendor (id)
);

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

create index fk_product_vendor1_idx
    on product (vendor_id);

create table if not exists product_category
(
    product_id  bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id),
    constraint fk_pc_category
        foreign key (category_id) references category (id),
    constraint fk_pc_product
        foreign key (product_id) references product (id)
);

create index idx_pc_category
    on product_category (category_id);

create index idx_pc_product
    on product_category (product_id);

create table if not exists product_review
(
    id          bigint auto_increment,
    product_id  bigint             not null,
    title       varchar(100)       not null,
    rating      smallint default 0 not null,
    created_at  datetime           not null,
    modified_at datetime           null,
    content     text               null,
    customer_id bigint             not null,
    primary key (id, customer_id),
    constraint fk_product_review_customer1
        foreign key (customer_id) references customer (id),
    constraint fk_review_product
        foreign key (product_id) references product (id)
);

create index fk_product_review_customer1_idx
    on product_review (customer_id);

create index idx_review_product
    on product_review (product_id);

create index fk_vendor_user_idx
    on vendor (user_id);


