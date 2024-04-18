package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Connect4TDDSpec {

    /**
     * clase bajo testeo
     */
    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs()).isZero();


    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        try {
            tested.putDiscInColumn(Connect4.COLUMNS + 1);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isNotEmpty();

        }

    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        int column = 6;
        assertThat(tested.putDiscInColumn(column)).isZero();
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        int column = 6;
        tested.putDiscInColumn(column);
        assertThat(tested.putDiscInColumn(column)).isOne();

    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        int column = 6;
        tested.putDiscInColumn(column);
        tested.putDiscInColumn(column);
        assertThat(tested.getNumberOfDiscs()).isEqualTo(2);


    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        int column = 6;
        for (int i = 0; i < Connect4.ROWS; i++) {
            tested.putDiscInColumn(column);
        }
        try {
            tested.putDiscInColumn(column);
            fail("Me lo esperaba");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }


    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat(tested.getCurrentPlayer()).isEqualTo("R");
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        tested.switchPlayer();
        assertThat(tested.getCurrentPlayer()).isEqualTo("G");

    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        tested.getCurrentPlayer();
        assertThat(output.toString()).isNotEmpty();


    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(output.toString()).isNotEmpty();
    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        assertThat(tested.isFinished()).isFalse();
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        for (int i = 0; i < Connect4.ROWS; i++) {
            for (int j = 0; j < Connect4.COLUMNS; j++) {
                tested.putDiscInColumn(j);
            }
        }
        assertThat(tested.isFinished()).isTrue();
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {

        int column = 1;
        for (int i = 0; i < 4; i++) {
            tested.putDiscInColumn(column);
            if (i < 3) {
                tested.switchPlayer();
            }
        }
        assertThat(tested.getWinner()).isEqualTo("R");

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {
        int column;
        for (int i = 0; i < 4; i++) {
            column = i;
            tested.putDiscInColumn(column);
            if (i < 3) {
                tested.switchPlayer();
            }
        }
        assertThat(tested.getWinner()).isEqualTo("R");
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        int column;
        for (int i = 0; i < 4; i++) {
            column = i;
            for (int j = 0; j < i; j++) {
                tested.putDiscInColumn(column);
                tested.switchPlayer();
            }
            tested.putDiscInColumn(column);
            if (i < 3) {
                tested.switchPlayer();
            }
        }
        assertThat(tested.getWinner()).isEqualTo("R");
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        int column;
        for (int i = 0; i < 4; i++) {
            column = 3 - i;
            for (int j = 0; j < i; j++) {
                tested.putDiscInColumn(column);
                tested.switchPlayer();
            }
            tested.putDiscInColumn(column);
            if (i < 3) {
                tested.switchPlayer();
            }
        }
        assertThat(tested.getWinner()).isEqualTo("R");
    }
}
