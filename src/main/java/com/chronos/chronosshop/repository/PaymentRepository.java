package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Query("SELECT SUM(p.total), MONTH(p.paymentDate) FROM Payment p WHERE p.status = 'Hoàn thành' " +
            "and YEAR(p.paymentDate) = ?1 GROUP BY MONTH(p.paymentDate) ORDER BY MONTH(p.paymentDate) ASC")
    List<Object[]> thongKeDoanhThuTheoNam(int year);

    @Query("SELECT YEAR(p.paymentDate) FROM Payment p WHERE p.status = 'Hoàn thành' GROUP BY YEAR(p.paymentDate) ORDER BY YEAR(p.paymentDate) DESC")
    List<Object> listYear();
}
