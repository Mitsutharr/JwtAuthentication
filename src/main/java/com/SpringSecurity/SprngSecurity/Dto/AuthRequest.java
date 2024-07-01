package com.SpringSecurity.SprngSecurity.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AuthRequest {

    private String username;
    private String password;
}
