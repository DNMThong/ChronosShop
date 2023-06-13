package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT SUM(od.quantity) FROM OrderDetail od where od.order.status = 'Hoàn thành' and YEAR(od.order.createTime) = ?1 GROUP BY YEAR(od.order.createTime)")
    Integer sumSanPhamDaBanTrongNam(int year);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_detail(product_color_id, product_price, quantity) VALUES " +
            "(?1, ?2, ?3)", nativeQuery = true)
    void insertOrderDetail(int product_color_id, int product_price, int quantity);
}
