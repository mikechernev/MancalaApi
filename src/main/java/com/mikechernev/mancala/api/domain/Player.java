package com.mikechernev.mancala.api.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by mike on 23/04/16.
 */

@Entity
public class Player {
    @Id
    private ObjectId _id;
    private String id;
    private String name;

    // This is required by Morphia
    public Player() {
    }

    public Player(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        if (id == null) {
            id = _id.toString();
        }

        return id;
    }
}
