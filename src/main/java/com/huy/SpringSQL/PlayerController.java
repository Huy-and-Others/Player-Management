package com.huy.SpringSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
public class PlayerController {
    @Autowired
    private PlayerRepository repo;

    @GetMapping("/players")
    public @ResponseBody Iterable<Player> getAll(){
        return repo.findAll();
    }

    @GetMapping("/players/{id}")
    public @ResponseBody
    Player getByID(@PathVariable(required = true) Long id){
        return repo.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "does not exist"));
    }

    @PostMapping("/players/")
    public @ResponseBody
    Player save(@RequestBody Player player){
        return repo.save(player);
    }

    @PutMapping("/players/{id}")
    public @ResponseBody
    Player updateById(@PathVariable(required = true) Long id, @RequestBody Player player) throws InvalidConfigurationPropertyValueException {
        repo.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "does not exist"));
        player.setId(id);
        return repo.save(player);
    }

    @DeleteMapping("/players/{id}")
    public @ResponseBody Optional<Player> deleteById(@PathVariable Long id){
        Optional<Player> player = Optional.ofNullable(repo.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "does not exist")));;
        repo.deleteById(id);
        return player;
    }


}
