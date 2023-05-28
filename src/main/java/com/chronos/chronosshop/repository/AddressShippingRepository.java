package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.AddressShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressShippingRepository extends JpaRepository<AddressShipping, Integer> {
}
