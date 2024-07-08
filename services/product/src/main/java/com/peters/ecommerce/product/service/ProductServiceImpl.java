package com.peters.ecommerce.product.service;

import com.peters.ecommerce.product.dto.*;
import com.peters.ecommerce.product.exceptions.ProductNotFoundException;
import com.peters.ecommerce.product.exceptions.ProductPurchaseException;
import com.peters.ecommerce.product.model.Category;
import com.peters.ecommerce.product.model.Product;
import com.peters.ecommerce.product.repository.CategoryRepository;
import com.peters.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    @Override
    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exists!");
        }
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = getProduct(storedProducts, i, storedRequest);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product,storedRequest.get(i).quantity()));
            //TODO: In future, implement payment processing
        }
        return purchasedProducts;
    }

    private static Product getProduct(List<Product> storedProducts, int i, List<ProductPurchaseRequest> storedRequest) {
        var product = storedProducts.get(i);
        var productRequest = storedRequest.get(i);
        if(product.getAvailableQuantity() < productRequest.quantity()){
            throw new ProductPurchaseException(String.format("Insufficient stock quantity for product with ID %s, the available quantity for this product is %s", product.getId(), product.getAvailableQuantity()));
        }
        var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
        product.setAvailableQuantity(newAvailableQuantity);
        return product;
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(()->new ProductNotFoundException(String.format("No product found for the provided ID: %s", id)));
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        List<Product> products = repository.findAll();
        if(products.isEmpty()) return List.of();
        return products.stream().map(mapper::toProductResponse).toList();
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) return List.of();
        return categories.stream().map(mapper::toCategoryResponse).toList();
    }
}
