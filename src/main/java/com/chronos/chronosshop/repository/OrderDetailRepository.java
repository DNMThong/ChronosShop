package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT SUM(od.quantity) FROM OrderDetail od where od.order.status = 'Hoàn thành' and YEAR(od.order.createTime) = ?1 GROUP BY YEAR(od.order.createTime)")
    Integer sumSanPhamDaBanTrongNam(int year);
}
