package com.cg.service;


import com.cg.model.Deposit;
import com.cg.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Override
    public Optional<Deposit> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save(deposit);
    }
}
