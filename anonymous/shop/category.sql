create table if not exists category
(
    id      bigint auto_increment
        primary key,
    title   varchar(75) not null,
    content text        null,
    code    varchar(45) not null
);

