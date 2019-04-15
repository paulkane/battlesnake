package paulkane.battlesnake.safety;

import org.junit.Test;
import paulkane.battlesnake.model.domain.MOVE;

import static org.assertj.core.api.Assertions.assertThat;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.body;
import static paulkane.battlesnake.SnakeHelper.snake;

public class WallSafetyUTest {

    private final WallSafety wallSafety = new WallSafety();

    @Test
    public void givenImAtTheTopCannotGoUp() {
        assertThat(wallSafety.isItSafe(MOVE.UP, battleSnakeRequest(snake(body(0, 0))))).isEqualTo(SAFE.NO);
    }

    @Test
    public void givenImAtTheBottomCannotGoDown() {
        assertThat(wallSafety.isItSafe(MOVE.DOWN, battleSnakeRequest(snake(body(0, 14))))).isEqualTo(SAFE.NO);
    }

    @Test
    public void givenImAtTheLeftCannotGoLeft() {
        assertThat(wallSafety.isItSafe(MOVE.LEFT, battleSnakeRequest(snake(body(0, 10))))).isEqualTo(SAFE.NO);
    }

    @Test
    public void givenImAtTheRightCannotGoRight() {
        assertThat(wallSafety.isItSafe(MOVE.RIGHT, battleSnakeRequest(snake(body(14, 0))))).isEqualTo(SAFE.NO);
    }

    @Test
    public void givenImInTheMiddleCanGoInAnyDirection() {
        assertThat(wallSafety.isItSafe(MOVE.UP, battleSnakeRequest(snake(body(5, 5))))).isEqualTo(SAFE.YES);
        assertThat(wallSafety.isItSafe(MOVE.DOWN, battleSnakeRequest(snake(body(5, 5))))).isEqualTo(SAFE.YES);
        assertThat(wallSafety.isItSafe(MOVE.LEFT, battleSnakeRequest(snake(body(5, 5))))).isEqualTo(SAFE.YES);
        assertThat(wallSafety.isItSafe(MOVE.RIGHT, battleSnakeRequest(snake(body(5, 5))))).isEqualTo(SAFE.YES);
    }
}