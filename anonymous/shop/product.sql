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

create index fk_product_vendor1_idx
    on product (vendor_id);

