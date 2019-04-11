package paulkane.battlesnake.stats;

import org.junit.Test;

public class TallyUTest {

    @Test
    public void addWin() {
        Tally.addWin("one", "mySnake");
        Tally.addWin("one", "mySnake");
        Tally.addWin("one", "mySnake");
        Tally.addWin("two", "mySnake");
        Tally.addWin("three", "anotherSnake");

        Tally.printTally();
    }
}