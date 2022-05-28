package com.java.ProductService.service.impl;

import com.java.ProductService.dto.ProductRequestDTO;
import com.java.ProductService.dto.ProductResponseDTO;
import com.java.ProductService.entity.Product;
import com.java.ProductService.repository.ProductRepo;
import com.java.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        productRepo.save(product);
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        return allProducts.stream().map(product -> modelMapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }
}
