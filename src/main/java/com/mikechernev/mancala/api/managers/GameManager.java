package com.mikechernev.mancala.api.managers;

import com.mikechernev.mancala.api.dao.GameDao;
import com.mikechernev.mancala.api.dao.PlayerDao;
import com.mikechernev.mancala.api.domain.Game;
import com.mikechernev.mancala.api.domain.Move;
import com.mikechernev.mancala.api.domain.Player;

/**
 * Created by mike on 24/04/16.
 */
public class GameManager {

    private GameDao gameDao;
    private Game game;

    private PlayerDao playerDao;

    public GameManager(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public GameManager(GameDao gameDao, Game game) {
        this.gameDao = gameDao;
        this.game = game;
    }

    public GameManager(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public Game getGame(String gameId) {
        return this.gameDao.get(gameId);
    }

    public Game createGame() {
        this.gameDao.create(this.game);

        return this.game;
    }

    public Game addPlayer(String gameId, String playerId) {
        this.game = this.gameDao.get(gameId);

        if (this.game == null) {
            return null;
        }

        Player player = this.playerDao.get(playerId);

        if (player == null) {
            return this.game;
        }

        this.game.addPlayer(player);
        this.game.setCurrentPlayer(player);

        this.gameDao.update(this.game);

        return this.game;
    }

    public Game makeMove(String gameId, String playerId, int pit) {
        this.game = this.gameDao.get(gameId);
        if (this.game == null) {
            return null;
        }

        Player player = this.playerDao.get(playerId);

        if (player == null) {
            return this.game;
        }
        Move move = new Move(player, pit);
        this.game.makeMove(move);

        this.game.setCurrentPlayer(player);
        this.gameDao.update(this.game);

        return this.game;
    }
}
