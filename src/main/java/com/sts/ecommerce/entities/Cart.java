package com.sts.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.*;

/**
 * @author saif
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cartItems"})
@Table(name = "carts")
public class Cart {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "date_created", updatable = false, nullable = false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "cart")
    @Builder.Default
    private Set<CartItem> cartItems = new LinkedHashSet<>();
}