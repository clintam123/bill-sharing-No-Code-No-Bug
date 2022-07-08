create table if not exists user
(
    id            bigint auto_increment
        primary key,
    passwordHash  varchar(32) not null,
    registered_at datetime    not null,
    last_login    datetime    null,
    username      varchar(45) not null
);

