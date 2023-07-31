package com.example.bai_1.model;

import java.util.HashMap;
import java.util.Map;

public class Favorites {
    private Map<FootballPlayer,Integer> footballPlayerIntegerMap=new HashMap<>();

    public Favorites() {
    }

    public Favorites(Map<FootballPlayer, Integer> footballPlayerIntegerMap) {
        this.footballPlayerIntegerMap = footballPlayerIntegerMap;
    }

    public Map<FootballPlayer, Integer> getFootballPlayerIntegerMap() {
        return footballPlayerIntegerMap;
    }

    public void setFootballPlayerIntegerMap(Map<FootballPlayer, Integer> footballPlayerIntegerMap) {
        this.footballPlayerIntegerMap = footballPlayerIntegerMap;
    }
    public void addFootballPlayer(FootballPlayer footballPlayer){
        if (footballPlayerIntegerMap.containsKey(footballPlayer)){
            Integer value=footballPlayerIntegerMap.get(footballPlayer);
            footballPlayerIntegerMap.replace(footballPlayer,value+1);
        }else {
            footballPlayerIntegerMap.put(footballPlayer,1);
        }
    }
}
