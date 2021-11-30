package com.example.mongo_back.services;


import com.example.mongo_back.domain.Counter;
import com.example.mongo_back.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterRepository counterRepository;

    @Override
    public Long getNext() {
        Counter counter = counterRepository.findTopByOrderByIdDesc().orElse(new Counter(null, 1L));
        Long id = counter.getSeq();
        counter.setSeq(id + 1);
        counterRepository.save(counter);
        return id;
    }
}
