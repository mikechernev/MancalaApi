package com.mikechernev.mancala.api.domain;

/**
 * Created by mike on 24/04/16.
 */
public class BoardSettings {

    private static final int STONES = 6;
    public static final int[] INITIAL_STATE = new int[]{
            STONES, STONES, STONES, STONES, STONES, STONES, 0,
            STONES, STONES, STONES, STONES, STONES, STONES, 0
    };
    public static final int PITS = 14;
    public static final int OFFSET = PITS / 2;
    public static final int HOST_KALAH = PITS / 2 - 1;
    public static final int GUEST_KALAH = PITS - 1;
}
