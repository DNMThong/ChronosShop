package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Orders;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@EnableTransactionManagement
public interface OrderRepository extends JpaRepository<Orders, String> {
    Iterable<Orders> findAllByOrderByCreateTimeDesc();
    @Query("select count(o) from Orders o where o.status = 'Hoàn thành' and YEAR(o.createTime) = ?1 GROUP BY YEAR(o.createTime)")
    Integer countOrders(int year);

//    @Transactional
//    @Procedure(procedureName = "TaoOrderVaPayment", outputParameterName = "Orders")
//    @Modifying
//    Orders TaoOrderVaPayment(String userId, int shipId, String couponId, String status, LocalDateTime createTime, LocalDateTime updateTime, String paymentMethod, Long subTotal, Long subTotalFee, Long total, String currency);

    @Modifying
    @Procedure(name = "TaoOrderVaPayment")
    @Transactional
    Orders taoOrderVaPayment(@Param("user_id") String userId, @Param("ship_id") int shipId, @Param("coupon_id") String couponId, @Param("status") String status, @Param("create_time") LocalDateTime createTime, @Param("update_time") LocalDateTime updateTime, @Param("payment_method_name") String paymentMethod, @Param("subtotal") Long subTotal, @Param("subtotal_fee") Long subTotalFee, @Param("total") Long total, @Param("currency") String currency);

    Orders findByUser_UserIdAndAddressShipping_ShipIdAndCreateTime(String userId, int shipId, LocalDateTime createDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Orders SET status = ?1, update_time = ?2 WHERE order_id = ?3", nativeQuery = true)
    void updateOrderStatusByOrderId(String status, String updateTime, String orderId);
}
