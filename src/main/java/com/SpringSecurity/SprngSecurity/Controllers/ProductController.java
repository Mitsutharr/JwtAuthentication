package com.SpringSecurity.SprngSecurity.Controllers;

import com.SpringSecurity.SprngSecurity.Dto.AuthRequest;
import com.SpringSecurity.SprngSecurity.Dto.Product;
import com.SpringSecurity.SprngSecurity.Entity.UserInfo;
import com.SpringSecurity.SprngSecurity.service.JwtService;
import com.SpringSecurity.SprngSecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    private String Welcome()
    {
        return "welcome this end point is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return productService.addUser(userInfo);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProducts()
    {
        return productService.getProducts();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProduct(@PathVariable int id)
    {
        return productService.getProduct(id);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else {
            throw new UsernameNotFoundException("Invalid User Request!!");
        }

    }

















}
