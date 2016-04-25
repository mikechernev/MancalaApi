package com.mikechernev.mancala.api.rest;

import com.mikechernev.mancala.api.dao.GameDao;
import com.mikechernev.mancala.api.dao.GameDaoImpl;
import com.mikechernev.mancala.api.dao.PlayerDao;
import com.mikechernev.mancala.api.dao.PlayerDaoImpl;
import com.mikechernev.mancala.api.domain.Board;
import com.mikechernev.mancala.api.domain.Game;
import com.mikechernev.mancala.api.managers.GameManager;
import com.mikechernev.mancala.api.persistance.Mongo;

import javax.swing.*;
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

        GameManager manager = new GameManager(gameDao);

        return manager.getGame(gameId);
    }

    @POST
    @Path("/")
    @Produces("application/json")
    public Game createGame() {
        Board board = new Board();
        Game game = new Game(board);
        GameDao gameDao = new GameDaoImpl(Mongo.getDatastore());
        GameManager manager = new GameManager(gameDao, game);

        return manager.createGame();
    }

    @PUT
    @Path("/{gameId}/addPlayer/{playerId}")
    @Produces("application/json")
    public Game addPlayer(@PathParam("gameId") String gameId, @PathParam("playerId") String playerId) {
        GameDao gameDao = new GameDaoImpl(Mongo.getDatastore());
        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());

        GameManager manager = new GameManager(gameDao, playerDao);

        return manager.addPlayer(gameId, playerId);
    }

    @POST
    @Path("/{gameId}/move/{pit}")
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Game makeMove(
            @PathParam("gameId") String gameId,
            @PathParam("pit") Integer pit,
            @FormParam("playerId") String playerId
    ) {
        GameDao gameDao = new GameDaoImpl(Mongo.getDatastore());
        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());

        GameManager manager = new GameManager(gameDao, playerDao);

        return manager.makeMove(gameId, playerId, pit);
    }
}

