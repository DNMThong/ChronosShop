package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
    @Query("select count(o) from Orders o where o.status = 'Hoàn thành' and YEAR(o.createTime) = ?1 GROUP BY YEAR(o.createTime)")
    Integer countOrders(int year);
}
