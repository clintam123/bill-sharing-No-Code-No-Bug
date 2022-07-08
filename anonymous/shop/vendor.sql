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

create index fk_vendor_user_idx
    on vendor (user_id);

