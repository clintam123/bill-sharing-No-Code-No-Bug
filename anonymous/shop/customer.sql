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

