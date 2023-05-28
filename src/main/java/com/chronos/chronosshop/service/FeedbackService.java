package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Feedback;
import com.chronos.chronosshop.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository repository;

    // PHẦN NÀY SẼ LÀM CUỐI CÙNG TRONG JAVA 5

//    public List<Feedback> listAll() {
//        return repository.findAll();
//    }
//
//    public void save(Feedback feedback) {
//        repository.save(feedback);
//    }
//
//    public Feedback get(Integer id) {
//        Optional<Feedback> result = repository.findById(id);
//        if (result.isPresent()) {
//            return result.get();
//        }
//        return null;
//    }
//
//    public void delete(Integer id) {
//        repository.deleteById(id);
//    }
}
