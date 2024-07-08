package com.peters.ecommerce.product.service;

import com.peters.ecommerce.product.dto.*;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findProductById(Integer id);

    List<ProductResponse> findAllProducts();

    List<CategoryResponse> findAllCategories();
}
