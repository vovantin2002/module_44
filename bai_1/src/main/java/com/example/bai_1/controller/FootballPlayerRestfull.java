package com.example.bai_1.controller;

import com.example.bai_1.model.FootballPlayer;
import com.example.bai_1.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
public class FootballPlayerRestfull {
    @Autowired
    private IFootballPlayerService iFootballPlayerService;

    @GetMapping("")
    public ResponseEntity<List<FootballPlayer>> display() {
        List<FootballPlayer> footballPlayerList = iFootballPlayerService.list();
        if (footballPlayerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(footballPlayerList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballPlayer> displayFootballFlayer(@PathVariable int id) {
        FootballPlayer footballPlayer = iFootballPlayerService.showFootballPlayerEdit(id);
        if (footballPlayer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(footballPlayer, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<FootballPlayer> add(@RequestBody FootballPlayer footballPlayer) {
        if (footballPlayer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        iFootballPlayerService.add(footballPlayer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<FootballPlayer> update(@RequestParam int id, @RequestBody FootballPlayer footballPlayer) {
        if (iFootballPlayerService.showFootballPlayerEdit(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        iFootballPlayerService.edit(footballPlayer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
