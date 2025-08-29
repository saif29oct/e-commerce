create table categories
(
    id   tinyint unsigned auto_increment
        primary key,
    name varchar(255) not null
);

create table products
(
    id          bigint auto_increment
        primary key,
    name        varchar(255)     not null,
    description varchar(256)     null,
    price       decimal(10, 2)   null,
    category_id tinyint unsigned null,
    constraint products_categories_id_fk
        foreign key (category_id) references categories (id)
);

create table tags
(
    id   int auto_increment
        primary key,
    name varchar(100) not null
);

create table users
(
    id       bigint auto_increment
        primary key,
    name     varchar(50)  not null,
    email    varchar(100) not null,
    password varchar(64)  not null
);

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

create
    definer = root@`%` procedure getProductsByPriceRange(IN minPrice decimal(10, 2), IN maxPrice decimal(10, 2))
BEGIN
    SELECT `id`, `name`, `description`, `price`, `category_id` FROM products WHERE price >= minPrice AND price <= maxPrice;
END;

