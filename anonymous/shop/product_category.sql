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

