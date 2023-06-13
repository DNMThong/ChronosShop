package com.chronos.chronosshop.entity.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    private int shipId;

    private AddressShippingDto addressShippingDto;

    private String couponId;

    private OrderDetailDto orderDetailDto;

    private int shippingFee;

    private String paymentMethod;

}
