package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Orders;

import java.time.LocalDateTime;

public interface IOrderService extends IService<Orders,String>{

    Orders createOrderAndPayment(String userId, int shipId, String couponId, String status, LocalDateTime createTime, LocalDateTime updateTime, String paymentMethod, Long subTotal, Long subTotalFee, Long total, String currency);

    Orders findByUser_UserIdAndAddressShipping_ShipIdAndCreateTime(String userId, int shipId, LocalDateTime createDate);

}
