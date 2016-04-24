package com.mikechernev.mancala.api.rest;

import com.mikechernev.mancala.api.dao.PlayerDao;
import com.mikechernev.mancala.api.dao.PlayerDaoImpl;
import com.mikechernev.mancala.api.domain.Player;
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

        return playerDao.get(playerId);
    }

    @POST
    @Path("/")
    @Produces("application/json")
    public Player createPlayer() {
        Player player = new Player();
        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());

        playerDao.create(player);

        return player;
    }

    @PUT
    @Path("/{playerId}")
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Player updatePlayer(@PathParam("playerId") String playerId, @FormParam("name") String name) {
        PlayerDao playerDao = new PlayerDaoImpl(Mongo.getDatastore());

        Player player = playerDao.get(playerId);
        if (player == null) {
            return null;
        }

        if (name == null) {
            return player;
        }

        player.setName(name);
        playerDao.update(player);

        return player;
    }
}

