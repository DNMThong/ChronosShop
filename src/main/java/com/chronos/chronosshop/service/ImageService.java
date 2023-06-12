package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Feedback;
import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.Payment;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService{
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    @Autowired
    private ImageRepository repository;
    @Override
    public boolean save(Image image) {
        try {
            repository.save(image);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Image image) {
        try {
            repository.save(image);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            repository.deleteById(id);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Image> findAll() {
        return repository.findAll();
    }

    @Override
    public Image findById(String id) {
        Optional<Image> payment = repository.findById(id);
        return payment.orElse(null);
    }
    public boolean updateImage(){
        return true;
    }
}
