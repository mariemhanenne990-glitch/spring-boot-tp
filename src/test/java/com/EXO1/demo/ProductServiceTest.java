package com.EXO1.demo;

import com.EXO1.demo.model.Product;
import com.EXO1.demo.repository.ProductRepository;
import com.EXO1.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void testGetAllProducts() {
        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setPrice(1500.0);

        Product p2 = new Product();
        p2.setName("Phone");
        p2.setPrice(800.0);

        when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));
        List<Product> result = service.getAllProducts();
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Tablet");
        product.setPrice(500.0);

        when(repository.save(product)).thenReturn(product);
        Product result = service.createProduct(product);
        assertNotNull(result);
        assertEquals("Tablet", result.getName());
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Monitor");

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> result = service.getProductById(1L);
        assertTrue(result.isPresent());
        assertEquals("Monitor", result.get().getName());
    }

    @Test
    void testDeleteProduct() {
        service.deleteProduct(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}