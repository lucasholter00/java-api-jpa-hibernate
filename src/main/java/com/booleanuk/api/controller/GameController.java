package com.booleanuk.api.controller;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    GameRepository repo;

    @PostMapping
    public ResponseEntity<Game> add(@RequestBody Game game){
        Game savedGame = this.repo.save(game);
        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAll(){
        return ResponseEntity.ok(this.repo.findAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> putOne(@PathVariable int id, @RequestBody Game game){

        Game toBeEdited = this.repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found")
        );

        toBeEdited.setTitle(game.getTitle());
        toBeEdited.setGenre(game.getGenre());
        toBeEdited.setPublisher(game.getPublisher());
        toBeEdited.setDeveloper(game.getDeveloper());
        toBeEdited.setReleaseYear(game.getReleaseYear());
        toBeEdited.setEarlyAccess(game.isEarlyAccess());

        return new ResponseEntity<>(this.repo.save(toBeEdited), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteOne(@PathVariable int id){
        Game deletedGame = this.repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found")
        );
        this.repo.delete(deletedGame);
        return ResponseEntity.ok(deletedGame);
    }

}
