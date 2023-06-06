package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Coupon;
import com.chronos.chronosshop.entity.Feedback;
import com.chronos.chronosshop.repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {
    private static final Logger logger = LoggerFactory.getLogger(IFeedbackService.class);
    @Autowired
    private FeedbackRepository repository;

    @Override
    public boolean save(Feedback feedback) {
        try {
            repository.save(feedback);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Feedback feedback) {
        try {
            repository.save(feedback);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            repository.deleteById(id);
            repository.flush();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Feedback> findAll() {
        return repository.findAll();
    }

    @Override
    public Feedback findById(Integer id) {
        Optional<Feedback> feedback = repository.findById(id);
        return feedback.orElse(null);
    }


}
