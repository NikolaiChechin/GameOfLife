import com.chechin.Game;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class GameTest {

    private void assertBoards(boolean[][] initialBoard, boolean[][] expectedBoard) {
        Game game = new Game(initialBoard);
        Game nextGeneration = game.getNextGeneration();
        assertArrayEquals(nextGeneration.getBoard(), expectedBoard);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyBoard() {
        boolean initialBoard[][] = {};
        Game game = new Game(initialBoard);
    }

    @Test
    public void twoNeighborsDie() {
        boolean initialBoard[][] =
                {{true, false, false},
                 {true, false, false},
                 {false, false, false}};
        boolean expectedBoard[][] =
                {{false, false, false},
                 {false, false, false},
                 {false, false, false}};
        assertBoards(initialBoard, expectedBoard);
    }

    @Test
    public void threeNeighborsBringLife() {
        boolean initialBoard[][] =
                {{true, false, false},
                 {true, false, false},
                 {true, false, false}};
        boolean expectedBoard[][] =
                {{false, false, false},
                 {true, true, false},
                 {false, false, false}};
        assertBoards(initialBoard, expectedBoard);
    }

    @Test
    public void fourNeighborsLeftAlive() {
        boolean initialBoard[][] =
                {{true, true, false},
                 {true, true, false},
                 {false, false, false}};
        boolean expectedBoard[][] =
                {{true, true, false},
                 {true, true, false},
                 {false, false, false}};
        assertBoards(initialBoard, expectedBoard);
    }
}
