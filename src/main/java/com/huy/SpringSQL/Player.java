package com.huy.SpringSQL;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(name = "FIRST_NAME")
    private String name;

    public Player(String name, Long id){
        this.id = id;
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
