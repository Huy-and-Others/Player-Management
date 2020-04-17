package com.huy.SpringSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class PlayerService{
    @Autowired
    private PlayerRepository repo;

    public Iterable<Player> getAll(){
        return repo.findAll();
    }

    public Player getByID(Long id){
        return repo.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "does not exist"));
    }

    public Player save(Player player){
        return repo.save(player);
    }

    public Player updateById(Long id, Player player) throws InvalidConfigurationPropertyValueException {
        repo.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "does not exist"));
        player.setId(id);
        return repo.save(player);
    }

    public Optional<Player> deleteById(Long id){
        Optional<Player> player = Optional.ofNullable(repo.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "does not exist")));;
        repo.deleteById(id);
        return player;
    }

}
