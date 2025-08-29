package com.sts.ecommerce.repsitories;

import com.sts.ecommerce.entities.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author saif
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @EntityGraph(attributePaths = {"categories"})
    @NonNull
    List<Product> findAll();

    @Override
    @EntityGraph(attributePaths = {"categories"})
    @NonNull
    Optional<Product> findById(@NonNull Long id);
}