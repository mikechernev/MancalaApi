package com.mikechernev.mancala.api.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 23/04/16.
 */
@Entity
public class Game {
    @Id
    private ObjectId _id;
    private String id;

    @Reference
    private Player host;

    @Reference
    private Player guest;

    @Embedded
    private Board board;

    private List<Move> moves = new ArrayList<Move>();

    @Reference
    private Player playerInTurn;

    @Reference
    private Player winner;

    private boolean gameOver = false;

    @Transient
    private Player currentPlayer;

    // Needed for Morphia
    public Game() {
    }

    public Game(Board board) {
        this.board = board;
    }

    public boolean addPlayer(Player player) {
        if (addHost(player) || player.equals(host)) {
            return true;
        }

        return addGuest(player);
    }

    public String getId() {
        if (this.id == null) {
            this.id = this._id.toString();
        }

        return this.id;
    }

    public String getHostName() {
        if (this.host == null) {
            return null;
        }

        return this.host.getName();
    }

    public String getGuestName() {
        if (this.guest == null) {
            return null;
        }

        return this.guest.getName();
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean getisGameOver() {
        return this.gameOver;
    }

    public String getPlayerInTurn() {
        return this.mapPlayerToIdentifier(this.playerInTurn);
    }

    public String getWinner() {
        return this.mapPlayerToIdentifier(this.winner);
    }

    public String getCurrentPlayer() {
        // TODO:
        if (this.currentPlayer == null) {
            return PlayerSettings.SPECTATOR_IDENTIFIER;
        }

        if (this.currentPlayer.equals(this.host)) {
            return PlayerSettings.HOST_IDENTIFIER;
        }

        if (this.currentPlayer.equals(this.guest)) {
            return PlayerSettings.GUEST_IDENTIFIER;
        }

        return PlayerSettings.SPECTATOR_IDENTIFIER;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean makeMove(Move move) {
        if (this.gameOver || !this.isMoveValid(move)) {
            return false;
        }

        this.board.applyMove(move);
        this.moves.add(move);

        if (this.isGameOver()) {
            this.endGame();
            return true;
        }

        if (!this.board.isEndPitHomeKalah(move)) {
            this.switchPlayerInTurn();
        }

        return true;
    }

    private boolean addHost(Player host) {
        if (this.host != null) {
            return false;
        }

        this.host = host;

        return true;
    }

    private boolean addGuest(Player guest) {
        if (this.guest != null) {
            return false;
        }

        this.guest = guest;

        // If the guest is set the game can start
        this.startGame();

        return true;
    }

    private void startGame() {
        this.playerInTurn = this.host;
        this.gameOver = false;
        this.board.resetBoard();
    }

    private boolean isMoveValid(Move move) {
        if (this.playerInTurn == null || !this.playerInTurn.equals(move.getPlayer())) {
            return false;
        }

        if (!this.board.isMoveValid(move)) {
            return false;
        }

        if (move.getPlayer().equals(this.host)) {
            return this.board.isHostPit(move.getPit());
        }

        return this.board.isGuestPit(move.getPit());
    }

    private void switchPlayerInTurn() {
        if (this.playerInTurn == null || this.playerInTurn.equals(this.guest)) {
            this.playerInTurn = this.host;
            return;
        }

        this.playerInTurn = this.guest;
    }

    private boolean isGameOver() {
        return this.board.isHostOver() || this.board.isGuestOver();
    }

    private void endGame() {
        this.board.collectStones();
        this.gameOver = true;
        this.playerInTurn = null;

        int hostStones = this.board.getHostStones();
        int guestStones = this.board.getGuestStones();

        // Don't set a winner if it's a tie
        if (hostStones == guestStones) {
            return;
        }

        if (hostStones > guestStones) {
            this.winner = this.host;
            return;
        }

        this.winner = this.guest;
    }

    private String mapPlayerToIdentifier(Player player) {
        if (player == null) {
            return null;
        }

        if (player.equals(this.host)) {
            return PlayerSettings.HOST_IDENTIFIER;
        }

        return PlayerSettings.GUEST_IDENTIFIER;

    }
}
