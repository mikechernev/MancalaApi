package com.mikechernev.mancala.api.domain;

import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTests {
    private Board board;
    private int[] state = new int[] {0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 20};
    private Move moveMock;

    @Before
    public void setup() {
        this.board = new Board();
        this.moveMock = EasyMock.createMock(Move.class);
    }

    @Test
    public void testIsGuestPitHostKalah() {
        boolean isGuestKalah = board.isGuestPit(BoardSettings.HOST_KALAH);
        assertFalse(isGuestKalah);
    }

    @Test
    public void testIsGuestPitGuestKalah() {
        boolean isGuestKalah = board.isGuestPit(BoardSettings.GUEST_KALAH);
        assertFalse(isGuestKalah);
    }

    @Test
    public void testIsGuestPit() {
        boolean isGuestKalah = board.isGuestPit(BoardSettings.HOST_KALAH + 1);
        assertTrue(isGuestKalah);
    }

    @Test
    public void testIsHostPitHostKalah() {
        boolean isHostKalah = board.isHostPit(BoardSettings.HOST_KALAH);
        assertFalse(isHostKalah);
    }

    @Test
    public void testIsHostPitGuestPit() {
        boolean isHostKalah = board.isHostPit(BoardSettings.HOST_KALAH + 1);
        assertFalse(isHostKalah);
    }

    @Test
    public void testIsHostPit() {
        boolean isHostKalah = board.isHostPit(0);
        assertTrue(isHostKalah);
    }

    @Test
    public void testIsHostOver() {
        boolean isHostOver = board.isHostOver();
        assertFalse(isHostOver);
    }

    @Test
    public void testIsHostOverTrue() {
        board = new Board(state);
        boolean isHostOver = board.isHostOver();
        assertTrue(isHostOver);
    }

    @Test
    public void testIsGuestOver() {
        board = new Board(state);
        boolean isGuestOver = board.isGuestOver();
        assertTrue(isGuestOver);
    }

    @Test
    public void testGetGuestStones() {
        board = new Board(state);
        int stones = board.getGuestStones();
        assertEquals(state[13], stones);
    }

    @Test
    public void testGetHostStones() {
        board = new Board(state);
        int stones = board.getHostStones();
        assertEquals(state[6], stones);
    }

    @Test
    public void testIsEndPitHomeKalahFirstHost() {
        EasyMock.expect(moveMock.getPit()).andReturn(0);
        EasyMock.expect(moveMock.getEndPit()).andReturn(BoardSettings.HOST_KALAH);
        EasyMock.replay(moveMock);
        boolean isEndPit = board.isEndPitHomeKalah(moveMock);
        assertTrue(isEndPit);
    }

    @Test
    public void testIsEndPitHomeKalahGuestHost() {
        EasyMock.expect(moveMock.getPit()).andReturn(BoardSettings.GUEST_KALAH);
        EasyMock.expect(moveMock.getEndPit()).andReturn(BoardSettings.HOST_KALAH);
        EasyMock.replay(moveMock);
        boolean isEndPit = board.isEndPitHomeKalah(moveMock);
        assertFalse(isEndPit);
    }

    @Test
    public void testIsEndPitHomeKalahHostKalah() {
        EasyMock.expect(moveMock.getPit()).andReturn(BoardSettings.HOST_KALAH);
        EasyMock.expect(moveMock.getEndPit()).andReturn(BoardSettings.HOST_KALAH);
        EasyMock.replay(moveMock);
        boolean isEndPit = board.isEndPitHomeKalah(moveMock);
        assertTrue(isEndPit);
    }

    @Test
    public void testIsEndPitHomeKalahFirstGuest() {
        EasyMock.expect(moveMock.getPit()).andReturn(7);
        EasyMock.expect(moveMock.getEndPit()).andReturn(BoardSettings.GUEST_KALAH);
        EasyMock.replay(moveMock);
        boolean isEndPit = board.isEndPitHomeKalah(moveMock);
        assertTrue(isEndPit);
    }

    @Test
    public void testIsEndPitHomeKalahHostGuest() {
        EasyMock.expect(moveMock.getPit()).andReturn(BoardSettings.HOST_KALAH);
        EasyMock.expect(moveMock.getEndPit()).andReturn(BoardSettings.GUEST_KALAH);
        EasyMock.replay(moveMock);
        boolean isEndPit = board.isEndPitHomeKalah(moveMock);
        assertFalse(isEndPit);
    }

    @Test
    public void testIsEndPitHomeKalahGuestKalah() {
        EasyMock.expect(moveMock.getPit()).andReturn(BoardSettings.GUEST_KALAH);
        EasyMock.expect(moveMock.getEndPit()).andReturn(BoardSettings.GUEST_KALAH);
        EasyMock.replay(moveMock);
        boolean isEndPit = board.isEndPitHomeKalah(moveMock);
        assertTrue(isEndPit);
    }

    @Test
    public void testResetBoard() {
        board.resetBoard();
        assertArrayEquals(board.getState(), BoardSettings.INITIAL_STATE);
    }

    @Test
    public void testIsMoveValidHostKalah() {
        EasyMock.expect(moveMock.getPit()).andReturn(BoardSettings.HOST_KALAH);
        EasyMock.replay(moveMock);
        boolean isValid = board.isMoveValid(moveMock);
        assertFalse(isValid);
    }

    @Test
    public void testIsMoveValidGuestKalah() {
        EasyMock.expect(moveMock.getPit()).andReturn(BoardSettings.GUEST_KALAH);
        EasyMock.replay(moveMock);
        boolean isValid = board.isMoveValid(moveMock);
        assertFalse(isValid);
    }

    @Test
    public void testIsMoveValidInitState() {
        EasyMock.expect(moveMock.getPit()).andReturn(0);
        EasyMock.replay(moveMock);
        boolean isValid = board.isMoveValid(moveMock);
        assertTrue(isValid);
    }

    @Test
    public void testIsMoveValidOverState() {
        EasyMock.expect(moveMock.getPit()).andReturn(0);
        EasyMock.replay(moveMock);
        board = new Board(state);
        boolean isValid = board.isMoveValid(moveMock);
        assertFalse(isValid);
    }

    @Test
    public void testCollectStonesInitState() {
        board.collectStones();
        int[] new_state = board.getState();
        int[] expected = new int[] {0, 0, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 0, 36};
        assertArrayEquals(expected, new_state);
    }

    @Test
    public void testCollectStones() {
        board = new Board(state);
        board.collectStones();
        int[] new_state = board.getState();
        assertArrayEquals(state, new_state);
    }

    @Test
    public void testApplyMove() {
        EasyMock.expect(moveMock.getPit()).andReturn(1);
        moveMock.setEndPit(7);
        EasyMock.replay(moveMock);
        board.applyMove(moveMock);
        int[] new_state = board.getState();
        int[] expected = new int[] {6, 0, 7, 7, 7, 7, 1, 7, 6, 6, 6, 6, 6, 0};
        assertArrayEquals(expected, new_state);
    }

    @Test
    public void testApplyMoveEndsInHostKalah() {
        EasyMock.expect(moveMock.getPit()).andReturn(0);
        moveMock.setEndPit(6);
        EasyMock.replay(moveMock);
        board.applyMove(moveMock);
        int[] new_state = board.getState();
        int[] expected = new int[] {0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0};
        assertArrayEquals(expected, new_state);
    }

    @Test
    public void testApplyMoveGuestOver() {
        EasyMock.expect(moveMock.getPit()).andReturn(12);
        moveMock.setEndPit(4);
        EasyMock.replay(moveMock);
        board.applyMove(moveMock);
        int[] new_state = board.getState();
        int[] expected = new int[] {7, 7, 7, 7, 7, 6, 0, 6, 6, 6, 6, 6, 0, 1};
        assertArrayEquals(expected, new_state);
    }

    @Test
    public void testApplyMoveCapturing() {
        EasyMock.expect(moveMock.getPit()).andReturn(0);
        moveMock.setEndPit(2);
        EasyMock.replay(moveMock);
        board = new Board(new int[] {2, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, 11, 12, 0});
        board.applyMove(moveMock);
        int[] new_state = board.getState();
        int[] expected = new int[] {0, 2, 0, 0, 0, 0, 21, 0, 0, 0, 0, 11, 12, 0};
        assertArrayEquals(expected, new_state);
    }

    @Test
    public void testApplyMoveCapturingEmptyPit() {
        EasyMock.expect(moveMock.getPit()).andReturn(0);
        moveMock.setEndPit(2);
        EasyMock.replay(moveMock);
        board = new Board(new int[] {2, 1, 0, 0, 0, 0, 10, 0, 0, 0, 0, 11, 12, 0});
        board.applyMove(moveMock);
        int[] new_state = board.getState();
        int[] expected = new int[] {0, 2, 1, 0, 0, 0, 10, 0, 0, 0, 0, 11, 12, 0};
        assertArrayEquals(expected, new_state);
    }

    @Test
    public void testApplyMoveSkipOppKalah() {
        EasyMock.expect(moveMock.getPit()).andReturn(5);
        moveMock.setEndPit(0);
        EasyMock.replay(moveMock);
        board = new Board(new int[] {1, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0});
        board.applyMove(moveMock);
        int[] new_state = board.getState();
        int[] expected = new int[] {2, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0};
        assertArrayEquals(expected, new_state);
    }

    @Test
    public void testApplyMoveSkipCollectKalah() {
        EasyMock.expect(moveMock.getPit()).andReturn(5);
        moveMock.setEndPit(1);
        EasyMock.replay(moveMock);
        board = new Board(new int[] {0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0});
        board.applyMove(moveMock);
        int[] new_state = board.getState();
        int[] expected = new int[] {1, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 0, 1, 0};
        assertArrayEquals(expected, new_state);
    }
}
