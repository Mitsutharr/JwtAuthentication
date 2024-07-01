package com.SpringSecurity.SprngSecurity.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    private int productId;
    private String name;
    private int qty;
    private double price;
}
