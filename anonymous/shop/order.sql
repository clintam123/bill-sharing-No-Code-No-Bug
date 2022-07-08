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
    primary key (id, vendor_id, customer_id),
    constraint fk_order_customer1
        foreign key (customer_id) references customer (id),
    constraint fk_order_vendor1
        foreign key (vendor_id) references vendor (id)
);

create index fk_order_customer1_idx
    on `order` (customer_id);

create index fk_order_vendor1_idx
    on `order` (vendor_id);

