package com.mikechernev.mancala.api.rest;

import com.mikechernev.mancala.api.domain.PlayerSettings;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by mike on 24/04/16.
 */
@Path("/settings")
public class SettingsEndpoints {

    @GET
    @Path("/")
    @Produces("application/json")
    public PlayerSettings getSettings() {
        return new PlayerSettings();
    }
}
