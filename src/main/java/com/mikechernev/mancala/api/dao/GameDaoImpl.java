package com.mikechernev.mancala.api.dao;

import com.mikechernev.mancala.api.domain.Game;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

/**
 * Created by mike on 23/04/16.
 */
public class GameDaoImpl implements GameDao {

    private Datastore ds;

    public GameDaoImpl (Datastore ds) {
        this.ds = ds;
    }

    public boolean create(Game game) {
        return this.save(game);
    }

    public Game get(String id) {
        ObjectId objectId = new ObjectId(id);
        return this.ds.get(Game.class, objectId);
    }

    public final boolean update(Game game) {
        return this.save(game);
    }

    private final boolean save(Game game) {
        this.ds.save(game);

        return true;
    }
}
