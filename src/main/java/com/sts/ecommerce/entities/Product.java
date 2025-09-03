package com.sts.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author saif
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "category_id"})
    )
    private List<Category> categories;
    
    // Helper method to manage the bidirectional relationship
    public void addCategory(Category category) {
        categories.add(category);
        category.getProducts().add(this);
    }
    
    public void removeCategory(Category category) {
        categories.remove(category);
        category.getProducts().remove(this);
    }
}