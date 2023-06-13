package com.chronos.chronosshop.entity.dto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private List<CartDto> cartDto;


}
