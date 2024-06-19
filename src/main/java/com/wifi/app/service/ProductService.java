package com.wifi.app.service;






import com.wifi.app.entity.Product;
import com.wifi.app.objects.ProductDTO;
import com.wifi.app.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Transactional
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findProductById(id);
    }

    public boolean ProductExist(String name) {
        return  findProductByName(name).isPresent();
    }

    private Optional<Product> findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    public Product register(ProductDTO productDTO){
        Product product = new Product();
        modelMapper.map(productDTO, product);
        return save(product);
    }

    @Transactional
    public Product save(Product product){
        return productRepository.save(product);
    }
}
