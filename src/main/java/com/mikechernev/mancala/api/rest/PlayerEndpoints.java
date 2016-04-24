package com.mikechernev.mancala.api.rest;

import com.mikechernev.mancala.api.dao.PlayerDao;
import com.mikechernev.mancala.api.dao.PlayerDaoImpl;
import com.mikechernev.mancala.api.domain.Player;
import com.mikechernev.mancala.api.managers.PlayerManager;
import com.mikechernev.mancala.api.persistance.Mongo;

import javax.ws.rs.*;

/**
 * Created by mike on 23/04/16.
 */

@Path("/players")
public class PlayerEndpoints {

    @GET
    @Path("/{playerId}")
    @Produces("application/json")
    public Player getPlayer(@PathParam("playerId") String playerId) {
        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());

        PlayerManager manager = new PlayerManager(playerDao);

        return manager.getPlayer(playerId);
    }

    @POST
    @Path("/")
    @Produces("application/json")
    public Player createPlayer() {
        Player player = new Player();
        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());

        PlayerManager manager = new PlayerManager(playerDao, player);

        return manager.createPlayer();
    }

    @PUT
    @Path("/{playerId}")
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Player updatePlayer(@PathParam("playerId") String playerId, @FormParam("name") String name) {
        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());

        PlayerManager manager = new PlayerManager(playerDao);
        return manager.setPlayerName(playerId, name);
    }
}

