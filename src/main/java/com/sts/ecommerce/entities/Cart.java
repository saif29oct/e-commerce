package com.sts.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Column(name = "date_created", insertable = false, updatable = false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "cart")
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();

    // Helper methods to manage the bidirectional relationship
    public void addCartItem(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        if (cartItems != null) {
            cartItems.remove(cartItem);
            cartItem.setCart(null);
        }
    }

    @PrePersist
    protected void onCreate() {
        if (dateCreated == null) {
            dateCreated = LocalDate.now();
        }
    }
    
    public void sumOfTwoNumbers(int number1, int number2) {
        
    }
}