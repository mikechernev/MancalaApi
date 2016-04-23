package com.mikechernev.mancala.api.rest;

import com.mikechernev.mancala.api.dao.*;
import com.mikechernev.mancala.api.domain.Game;
import com.mikechernev.mancala.api.domain.Player;
import com.mikechernev.mancala.api.persistance.Mongo;

import javax.ws.rs.*;

/**
 * Created by mike on 23/04/16.
 */

@Path("/games")
public class GameEndpoints {

    @GET
    @Path("/{gameId}")
    @Produces("application/json")
    public Game getGame(@PathParam("gameId") String gameId) {
        GameDao gameDao = new GameDaoImpl(Mongo.getDatastore());
        Game game = gameDao.get(gameId);

        return game;
    }

    @POST
    @Path("/")
    @Produces("application/json")
    public Game createGame() {
        Game game = new Game();
        GameDao gameDao = new GameDaoImpl(Mongo.getDatastore());
        gameDao.create(game);

        return game;
    }

    @PUT
    @Path("/{gameId}/addPlayer/{playerId}")
    @Produces("application/json")
    public Game addPlayer(@PathParam("gameId") String gameId, @PathParam("playerId") String playerId) {
        GameDao gameDao = new GameDaoImpl(Mongo.getDatastore());
        Game game = gameDao.get(gameId);

        if (game == null) {
            return null;
        }

        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());
        Player player = playerDao.get(playerId);

        if (player == null) {
            return game;
        }

        game.addPlayer(player);

        gameDao.update(game);

        return game;
    }
}

