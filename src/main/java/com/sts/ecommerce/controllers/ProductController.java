package com.sts.ecommerce.controllers;

import com.sts.ecommerce.dtos.CreateProductDto;
import com.sts.ecommerce.dtos.ProductDto;
import com.sts.ecommerce.entities.Product;
import com.sts.ecommerce.mappers.ProductMapper;
import com.sts.ecommerce.repsitories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author saif
 */
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        var product = productRepository.findById(id).map(productMapper::productToProductDto);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productRepository.findAll().stream().map(productMapper::productToProductDto).toList();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/create-product")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody CreateProductDto productInfo,
            UriComponentsBuilder uriComponentsBuilder
    ){
        var product = productMapper.createProductDtoToProduct(productInfo);
        var savedProduct = productRepository.save(product);
        var uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(productMapper.productToProductDto(savedProduct));
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody CreateProductDto productInfo
    ){
        var product = productRepository.findById(id);
        if(product.isEmpty()) return ResponseEntity.notFound().build();
        Product mappedProduct = productMapper.updateProductFromCreateProductDto(productInfo, product.get());
        Product updatedProduct = productRepository.save(mappedProduct);
        return ResponseEntity.ok(productMapper.productToProductDto(updatedProduct));
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        var product = productRepository.findById(id);
        if(product.isEmpty()) return ResponseEntity.notFound().build();
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
