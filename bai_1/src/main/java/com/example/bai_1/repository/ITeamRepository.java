package com.example.bai_1.repository;

import com.example.bai_1.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamRepository extends JpaRepository<Team,Integer> {
}
