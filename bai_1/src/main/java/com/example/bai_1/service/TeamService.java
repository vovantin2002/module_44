package com.example.bai_1.service;

import com.example.bai_1.model.Team;
import com.example.bai_1.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public List<Team> display() {
        return teamRepository.findAll();
    }
}
