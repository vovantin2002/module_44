package com.example.bai_1.model;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @Column(name = "team_id")
    private int id;
    private String name;
    private String playerList;
    private String coach;

    public Team(int id, String name, String playerList, String coach) {
        this.id = id;
        this.name = name;
        this.playerList = playerList;
        this.coach = coach;
    }

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerList() {
        return playerList;
    }

    public void setPlayerList(String playerList) {
        this.playerList = playerList;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
