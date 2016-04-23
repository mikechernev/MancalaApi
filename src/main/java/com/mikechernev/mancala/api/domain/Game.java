package com.mikechernev.mancala.api.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by mike on 23/04/16.
 */
@Entity
public class Game {
    @Id private ObjectId _id;
    private String id;

    private Player host;
    private Player guest;

    // This is required by Morphia
    public Game() {
    }

    public Game(Player host) {
        this.host = host;
    }

    public boolean addPlayer(Player player) {
        if (addHost(player)) {
            return true;
        }

        if (player.getId().equals(host.getId())) {
            return true;
        }

        return addGuest(player);
    }


    public boolean addHost(Player host) {
        if (this.host != null) {
            return false;
        }

        this.host = host;

        return true;
    }

    public boolean addGuest(Player guest) {
        if (this.guest != null) {
            return false;
        }

        this.guest = guest;

        return true;
    }

    public String getId() {
        if (id == null) {
            id = _id.toString();
        }

        return id;
    }

    public Player getHost() {
        return host;
    }

    public Player getGuest() {
        return guest;
    }
}
