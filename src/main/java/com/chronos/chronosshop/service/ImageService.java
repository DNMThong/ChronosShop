package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository repository;

    public List<Image> listAllByProductSku() {
        // tạo câu @Query SELECT * FROM Image WHERE productSku = '?';
        return null;
    }

    public void save(Image image) {
        repository.save(image);
    }

    public Image get(String id) {
        Optional<Image> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
