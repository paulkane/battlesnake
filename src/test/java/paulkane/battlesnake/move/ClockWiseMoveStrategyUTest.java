package paulkane.battlesnake.move;

import org.junit.Test;
import paulkane.battlesnake.model.domain.MOVE;

import static org.assertj.core.api.Assertions.assertThat;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.body;
import static paulkane.battlesnake.SnakeHelper.snake;
import static paulkane.battlesnake.model.domain.MOVE.DOWN;
import static paulkane.battlesnake.model.domain.MOVE.LEFT;
import static paulkane.battlesnake.model.domain.MOVE.RIGHT;
import static paulkane.battlesnake.model.domain.MOVE.UP;

public class ClockWiseMoveStrategyUTest {

    private final ClockWiseMoveStrategy clockWiseMoveStrategy = new ClockWiseMoveStrategy();

    @Test
    public void in0_0Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(0, 0), body(1, 0))
            ));
        assertThat(newMove).isEqualTo(UP);
    }

    @Test
    public void in15_0Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(14, 0), body(14, 1))
            ));
        assertThat(newMove).isEqualTo(RIGHT);
    }

    @Test
    public void in15_15Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(14, 14), body(13, 14))
            ));
        assertThat(newMove).isEqualTo(DOWN);
    }

    @Test
    public void in0_15Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(0, 14), body(0, 13))
            ));
        assertThat(newMove).isEqualTo(LEFT);
    }
}

