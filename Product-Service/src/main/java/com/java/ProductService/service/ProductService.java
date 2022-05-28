package com.java.ProductService.service;

import com.java.ProductService.dto.ProductRequestDTO;
import com.java.ProductService.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
ProductResponseDTO create(ProductRequestDTO productRequestDTO);
List<ProductResponseDTO> getAllProducts();
}
