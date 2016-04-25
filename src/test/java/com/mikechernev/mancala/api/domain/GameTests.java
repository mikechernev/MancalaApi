package com.mikechernev.mancala.api.domain;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.easymock.EasyMock;

public class GameTests {
    private Game game;
    private Board mockBoard;
    private Player mockPlayerHost;
    private Player mockPlayerGuest;
    private Move mockMove;

    @Before
    public void setup() {
        this.mockBoard = EasyMock.createMock(Board.class);
        this.game = new Game(this.mockBoard);
        this.mockPlayerHost = EasyMock.createMock(Player.class);
        this.mockPlayerGuest = EasyMock.createMock(Player.class);
        this.mockMove = EasyMock.createMock(Move.class);
    }

    @Test
    public void testSetHost() {
        String hostName = "Mike";
        game.addPlayer(mockPlayerHost);
        EasyMock.expect(mockPlayerHost.getName()).andReturn(hostName);
        EasyMock.replay(mockPlayerHost);
        mockBoard.resetBoard();
        EasyMock.replay(mockBoard);

        assertEquals(game.getHostName(), hostName);
    }

    @Test
    public void testSetHostAndGuest() {
        String guestName = "Bore";

        game.addPlayer(mockPlayerHost);

        game.addPlayer(mockPlayerGuest);
        EasyMock.expect(mockPlayerGuest.getName()).andReturn(guestName);
        EasyMock.replay(mockPlayerGuest);

        assertEquals(game.getGuestName(), guestName);
    }

    @Test
    public void testMoveWithNoPlayerInTurn() {
        assertFalse(game.makeMove(mockMove));

    }

    // TODO Complete Game Unit tests
    @Test
    public void testInvalidMovePlayer() {
//        game.addPlayer(mockPlayerHost);
//        game.addPlayer(mockPlayerGuest);
//
//        EasyMock.expect(mockMove.getPlayer()).andReturn(mockPlayerHost);
//        EasyMock.replay(mockMove);
//        EasyMock.expect(mockPlayerHost.equals(mockPlayerGuest)).andReturn(false);
//        EasyMock.replay(mockPlayerHost);

//        assertFalse(game.makeMove(mockMove));

    }
}
