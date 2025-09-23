-- Consolidated database schema for the e-commerce application
-- This file consolidates all previous migrations (V1-V7) into a single migration

-- Create categories table
CREATE TABLE categories
(
    id   TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create a product table
CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(256)   NULL,
    price       DECIMAL(10, 2) NULL
);

-- Create tags table
CREATE TABLE tags
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Create users table
CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(64)  NOT NULL
);

-- Create addresses table
CREATE TABLE addresses
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    street  VARCHAR(255) NULL,
    city    VARCHAR(100) NULL,
    zip     VARCHAR(100) NULL,
    user_id BIGINT       NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Create profiles table
CREATE TABLE profiles
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    bio            TEXT                     NULL,
    phone_number   VARCHAR(15)              NULL,
    date_of_birth  DATE                     NULL,
    loyalty_points INT UNSIGNED DEFAULT '0' NULL,
    CONSTRAINT profiles_users_id_fk
        FOREIGN KEY (id) REFERENCES users (id)
);

-- Create user_tags table
CREATE TABLE user_tags
(
    user_id BIGINT NOT NULL,
    tag_id  INT    NOT NULL,
    PRIMARY KEY (user_id, tag_id),
    CONSTRAINT user_tags_tags_id_fk
        FOREIGN KEY (tag_id) REFERENCES tags (id)
            ON DELETE CASCADE,
    CONSTRAINT user_tags_users_id_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE
);

-- Create wishlist table
CREATE TABLE wishlist
(
    user_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, product_id),
    CONSTRAINT wishlist_product_fk
        FOREIGN KEY (product_id) REFERENCES products (id)
            ON DELETE CASCADE,
    CONSTRAINT wishlist_user_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE
);

-- Create the junction table for many-to-many relationship between products and categories
CREATE TABLE product_category
(
    product_id  BIGINT           NOT NULL,
    category_id TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_product_category_product
        FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_category_category
        FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

-- Create carts table (consolidated from V2 and V3)
CREATE TABLE carts
(
    id           BINARY(16) NOT NULL PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    date_created DATE       NOT NULL             DEFAULT (CURRENT_DATE)
);

-- Create cart_items table (consolidated from V2, V4, V5, V6, and V7)
CREATE TABLE cart_items
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart             BINARY(16) NOT NULL,
    product_id       BIGINT     NOT NULL,
    product_quantity INT DEFAULT 1 NOT NULL,
    CONSTRAINT cart_item_product_unique
        UNIQUE (cart, product_id),
    CONSTRAINT cart_items_ibfk_1
        FOREIGN KEY (cart) REFERENCES carts (id)
            ON DELETE CASCADE,
    CONSTRAINT cart_items_ibfk_2
        FOREIGN KEY (product_id) REFERENCES products (id)
            ON DELETE CASCADE
);