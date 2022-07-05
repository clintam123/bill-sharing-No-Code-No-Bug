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

