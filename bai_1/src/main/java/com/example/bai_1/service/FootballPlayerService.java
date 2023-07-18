package com.example.bai_1.service;

import com.example.bai_1.model.FootballPlayer;
import com.example.bai_1.repository.IFootballPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballPlayerService implements IFootballPlayerService {
    @Autowired
    private IFootballPlayerRepository iFootballPlayerRepository;

    @Override
    public List<FootballPlayer> display() {
        return iFootballPlayerRepository.findAll();
    }

    @Override
    public void add(FootballPlayer footballPlayer) {
        iFootballPlayerRepository.save(footballPlayer);
    }

    @Override
    public void edit(FootballPlayer footballPlayer) {
        iFootballPlayerRepository.save(footballPlayer);
    }

    @Override
    public FootballPlayer showFootballPlayerEdit(int id) {
        return iFootballPlayerRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        iFootballPlayerRepository.isDelete(id);
    }
}
