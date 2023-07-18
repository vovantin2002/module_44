package com.example.bai_1.service;

import com.example.bai_1.model.FootballPlayer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IFootballPlayerService {
    List<FootballPlayer> display();

    void add(FootballPlayer footballPlayer);

    void edit(FootballPlayer footballPlayer);

    FootballPlayer showFootballPlayerEdit(int id);

    void delete(int id);
}
