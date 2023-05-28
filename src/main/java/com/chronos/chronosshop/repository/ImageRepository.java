package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
