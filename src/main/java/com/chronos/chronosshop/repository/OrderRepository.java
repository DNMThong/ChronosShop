package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
}
