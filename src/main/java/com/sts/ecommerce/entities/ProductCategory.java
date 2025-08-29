package com.sts.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author saif
 */
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_category")
@IdClass(ProductCategoryId.class)  // require for defining composite primary key
public class ProductCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}