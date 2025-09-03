-- Initial database schema for the e-commerce application

-- Create categories table
create table categories
(
    id   tinyint unsigned auto_increment
        primary key,
    name varchar(255) not null
);

-- Create products table
create table products
(
    id          bigint auto_increment
        primary key,
    name        varchar(255)     not null,
    description varchar(256)     null,
    price       decimal(10, 2)   null
);

-- Create tags table
create table tags
(
    id   int auto_increment
        primary key,
    name varchar(100) not null
);

-- Create users table
create table users
(
    id       bigint auto_increment
        primary key,
    name     varchar(50)  not null,
    email    varchar(100) not null,
    password varchar(64)  not null
);

-- Create addresses table
create table addresses
(
    id      bigint auto_increment
        primary key,
    street  varchar(255) null,
    city    varchar(100) null,
    zip     varchar(100) null,
    user_id bigint       not null,
    constraint fk_user
        foreign key (user_id) references users (id)
);

-- Create profiles table
create table profiles
(
    id             bigint auto_increment
        primary key,
    bio            text                     null,
    phone_number   varchar(15)              null,
    date_of_birth  date                     null,
    loyalty_points int unsigned default '0' null,
    constraint profiles_users_id_fk
        foreign key (id) references users (id)
);

-- Create user_tags table
create table user_tags
(
    user_id bigint not null,
    tag_id  int    not null,
    primary key (user_id, tag_id),
    constraint user_tags_tags_id_fk
        foreign key (tag_id) references tags (id)
            on delete cascade,
    constraint user_tags_users_id_fk
        foreign key (user_id) references users (id)
            on delete cascade
);

-- Create wishlist table
create table wishlist
(
    user_id    bigint not null,
    product_id bigint not null,
    primary key (user_id, product_id),
    constraint wishlist_product_fk
        foreign key (product_id) references products (id)
            on delete cascade,
    constraint wishlist_user_fk
        foreign key (user_id) references users (id)
            on delete cascade
);

-- Create the junction table for many-to-many relationship between products and categories
CREATE TABLE product_category
(
    product_id  BIGINT NOT NULL,
    category_id TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_product_category_product
        FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_category_category
        FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

-- Note: This schema addresses the data type inconsistency between:
-- 1. Database schema (categories.id = TINYINT UNSIGNED) 
-- 2. JPA entities (Category.id = Long)
-- 
-- The approach is to modify the JPA entities to match the database schema rather than 
-- changing the database which would be a breaking change.
-- The JPA entity Category should use @Column(columnDefinition = "TINYINT UNSIGNED") for the id field.