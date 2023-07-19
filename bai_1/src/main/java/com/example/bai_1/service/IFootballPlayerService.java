package com.example.bai_1.service;

import com.example.bai_1.model.FootballPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IFootballPlayerService {
    Page<FootballPlayer> display(Pageable pageable);
    List<FootballPlayer> list();

    Page<FootballPlayer> search(Pageable pageable, String name, String minDob, String maxDob);

    void add(FootballPlayer footballPlayer);

    void edit(FootballPlayer footballPlayer);

    FootballPlayer showFootballPlayerEdit(int id);

    void delete(int id);
}
