package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
    Iterable<Orders> findAllByOrderByCreateTimeDesc();

    @Query("select count(o) from Orders o where o.status = 'Hoàn thành' and YEAR(o.createTime) = ?1 GROUP BY YEAR(o.createTime)")
    Integer countOrders(int year);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Orders SET status = ?1, update_time = ?2 WHERE order_id = ?3", nativeQuery = true)
    void updateOrderStatusByOrderId(String status, String updateTime, String orderId);
}
