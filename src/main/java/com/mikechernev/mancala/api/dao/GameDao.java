package com.mikechernev.mancala.api.dao;

import com.mikechernev.mancala.api.domain.Game;
import org.bson.types.ObjectId;

/**
 * Created by mike on 23/04/16.
 */
public interface GameDao {
    boolean create(Game game);

    Game get(String id);

    boolean update(Game game);
}
