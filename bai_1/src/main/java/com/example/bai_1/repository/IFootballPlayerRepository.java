package com.example.bai_1.repository;

import com.example.bai_1.model.FootballPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IFootballPlayerRepository extends JpaRepository<FootballPlayer, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update football_player set is_flag = 1 where id = :id ", nativeQuery = true)
    void isDelete(@Param(value = "id") int id);
}
