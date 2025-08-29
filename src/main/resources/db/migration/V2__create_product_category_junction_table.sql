-- Remove the old foreign key constraint from products table
ALTER TABLE products DROP FOREIGN KEY products_categories_id_fk;

-- Remove the category_id column from products table
ALTER TABLE products DROP COLUMN category_id;

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