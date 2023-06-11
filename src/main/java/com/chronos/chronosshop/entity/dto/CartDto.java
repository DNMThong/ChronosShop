package com.chronos.chronosshop.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartDto {

    private String userId;

    private int productColorId;

    private Integer quantity;

}

