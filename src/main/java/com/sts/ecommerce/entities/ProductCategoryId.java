package com.sts.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author saif
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryId implements Serializable {
    private Long product;
    private Long category;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ProductCategoryId &&
                product.equals(((ProductCategoryId) obj).product) &&
                category.equals(((ProductCategoryId) obj).category);
    }

    @Override
    public int hashCode() {
        return product.hashCode() + category.hashCode();
    }
}