package com.mikechernev.mancala.api.domain;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by mike on 24/04/16.
 */
@Embedded
public class Move {
    @Reference
    private Player player;

    private int pit;
    private int endPit;

    // Needed for morphia
    public Move() {
    }

    public Move(Player player, int pit) {
        this.player = player;
        this.pit = pit;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getPit() {
        return this.pit;
    }

    public int getEndPit() {
        return this.endPit;
    }

    public void setEndPit(int endPit) {
        this.endPit = endPit;
    }
}
