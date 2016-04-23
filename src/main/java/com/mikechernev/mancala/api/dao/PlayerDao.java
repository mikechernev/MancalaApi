package com.mikechernev.mancala.api.dao;

import com.mikechernev.mancala.api.domain.Player;

/**
 * Created by mike on 23/04/16.
 */
public interface PlayerDao {
    boolean create(Player player);

    Player get(String id);

    boolean update(Player player);

}
