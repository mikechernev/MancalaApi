package com.mikechernev.mancala.api.domain;

/**
 * Created by mike on 24/04/16.
 */
public class PlayerSettings {
    public static final String HOST_IDENTIFIER = "host";
    public static final String GUEST_IDENTIFIER = "guest";
    public static final String SPECTATOR_IDENTIFIER = "spectator";

    public final String getHostIdentifier() {
        return HOST_IDENTIFIER;
    }

    public final String getGuestIdentifier() {
        return GUEST_IDENTIFIER;
    }
}
