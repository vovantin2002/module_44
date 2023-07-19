package com.example.bai_1.model;

import javax.persistence.*;

@Entity
@Table(name = "football_players")
public class FootballPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String birthday;
    private int experience;
    private String position;
    private String image;
    private boolean isFlag;
    @ManyToOne
    @JoinColumn(name = "team_id",referencedColumnName = "team_id")
    private  Team team;

    public FootballPlayer() {
    }

    public FootballPlayer(int id, String name, String birthday, int experience, String position, String image, boolean isFlag, Team team) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.experience = experience;
        this.position = position;
        this.image = image;
        this.isFlag = isFlag;
        this.team = team;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
