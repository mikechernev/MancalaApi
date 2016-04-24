package com.mikechernev.mancala.api.managers;

import com.mikechernev.mancala.api.dao.PlayerDao;
import com.mikechernev.mancala.api.domain.Player;

/**
 * Created by mike on 24/04/16.
 */
public class PlayerManager {
    private Player player;
    private PlayerDao playerDao;

    public PlayerManager(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public PlayerManager(PlayerDao playerDao, Player player) {
        this.player = player;
        this.playerDao = playerDao;
    }

    public Player getPlayer(String playerId) {
        return this.playerDao.get(playerId);
    }

    public Player createPlayer() {
        this.playerDao.create(this.player);

        return this.player;
    }

    public Player setPlayerName(String playerId, String name) {
        this.player = this.playerDao.get(playerId);
        if (this.player == null) {
            return null;
        }

        if (name == null) {
            return this.player;
        }

        this.player.setName(name);
        this.playerDao.update(this.player);

        return this.player;
    }
}
