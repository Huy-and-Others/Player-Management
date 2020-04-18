package com.huy.SpringSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService service;

    @GetMapping("/players")
    public @ResponseBody Iterable<Player> getAll(){
        return service.getAll();
    }

    @GetMapping("/players/{id}")
    public @ResponseBody
    Player getByID(@PathVariable(required = true) Long id){
        return service.getById(id);
    }

    @PostMapping("/players/")
    public @ResponseBody
    Player save(@RequestBody Player player){
        return service.save(player);
    }

    @PutMapping("/players/{id}")
    public @ResponseBody
    Player updateById(@PathVariable(required = true) Long id, @RequestBody Player player) throws InvalidConfigurationPropertyValueException {
        player.setId(id);
        return service.updateById(player);
    }

    @DeleteMapping("/players/{id}")
    public @ResponseBody Optional<Player> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

}
