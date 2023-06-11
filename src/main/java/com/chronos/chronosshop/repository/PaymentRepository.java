package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Query(value = "SELECT SUM(total), MONTH(payment_date) FROM Payment WHERE status = N'Đã thanh toán' " +
            "and YEAR(payment_date) = ?1 GROUP BY MONTH(payment_date) ORDER BY MONTH(payment_date) ASC", nativeQuery = true)
    List<Object[]> thongKeDoanhThuTheoNam(int year);

    @Query(value = "SELECT YEAR(payment_date) FROM Payment WHERE status = N'Đã thanh toán' GROUP BY YEAR(payment_date) ORDER BY YEAR(payment_date) DESC", nativeQuery = true)
    List<Object> listYea();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Payment SET status = ?1 WHERE payment_id = ?2", nativeQuery = true)
    void updatePaymentStatusByPaymentId(String status, String paymentId);
}
