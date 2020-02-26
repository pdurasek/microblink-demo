--liquibase formatted sql

--changeset patrik:21022020
create type contact as enum ('phone', 'mobile', 'email');

create table book
(
    isbn                   varchar      not null,
    id                     serial       not null
        constraint book_pk
            primary key,
    title                  varchar(255) not null,
    language               varchar      not null,
    number_of_pages        integer,
    publisher              varchar,
    genre                  varchar,
    copies                 integer      not null,
    copies_available       integer      not null,
    last_updated_date_time timestamp,
    creation_date_time     timestamp
);

create table user_account
(
    id                     serial  not null
        constraint user_account_pk
            primary key,
    first_name             varchar not null,
    last_name              varchar not null,
    date_of_birth          date,
    is_valid               boolean,
    creation_date_time     timestamp,
    last_updated_date_time timestamp
);

create table user_contact
(
    id                     serial  not null
        constraint user_contact_pk
            primary key,
    contact_type           contact not null,
    contact                varchar not null,
    user_account_id        integer
        constraint user_contact_user_fk
            references user_account,
    last_updated_date_time timestamp,
    creation_date_time     timestamp
);

create table rent_record
(
    id                     serial  not null
        constraint rent_record_pk
            primary key,
    user_account_id        integer not null
        constraint rent_record_user_fk
            references user_account,
    book_id                integer not null
        constraint rent_record_book_fk
            references book,
    rented_date            date    not null,
    due_date               date    not null,
    returned_date          date,
    overdue_days            integer,
    last_updated_date_time timestamp,
    creation_date_time     timestamp
);
