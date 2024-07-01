package com.SpringSecurity.SprngSecurity.service;

import com.SpringSecurity.SprngSecurity.Dto.Product;
import com.SpringSecurity.SprngSecurity.Entity.UserInfo;
import com.SpringSecurity.SprngSecurity.Repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    List<Product> productList = null;



    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());


    }


    public List<Product> getProducts() {

        return  productList;
    }

    public Product getProduct(int id) {

        return productList.stream().filter(product -> product.getProductId()==id)
                .findAny().orElseThrow(()-> new RuntimeException("Product" + id + "not found"));

    }

    public String addUser(UserInfo userInfo)
    {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to the System";
    }
}
