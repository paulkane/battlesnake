package paulkane.battlesnake.move;

import org.junit.Test;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Board;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.MOVE;
import paulkane.battlesnake.model.domain.Snake;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static paulkane.battlesnake.model.domain.MOVE.down;
import static paulkane.battlesnake.model.domain.MOVE.left;
import static paulkane.battlesnake.model.domain.MOVE.right;
import static paulkane.battlesnake.model.domain.MOVE.up;

public class ClockWiseMoveStrategyUTest {

    private ClockWiseMoveStrategy clockWiseMoveStrategy = new ClockWiseMoveStrategy();

    @Test
    public void in0_0Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(getRequest(
                snake(body(0, 0), body(1, 0))
            ));
        assertThat(newMove).isEqualTo(up);
    }

    @Test
    public void in15_0Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(getRequest(
                snake(body(14, 0), body(14, 1))
            ));
        assertThat(newMove).isEqualTo(right);
    }

    @Test
    public void in15_15Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(getRequest(
                snake(body(14, 14), body(13, 14))
            ));
        assertThat(newMove).isEqualTo(down);
    }

    @Test
    public void in0_15Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(getRequest(
                snake(body(0, 14), body(0, 13))
            ));
        assertThat(newMove).isEqualTo(left);
    }

    private Snake snake(Body... body) {
        return Snake.builder().body(Arrays.asList(body)).build();
    }

    private Body body(int x, int y) {
        return Body.builder().x(x).y(y).build();
    }

    private BattleSnakeRequest getRequest(Snake you) {
        return BattleSnakeRequest.builder()
            .board(
                Board.builder().height(15).width(15).build()
            )
            .you(you)
            .build();
    }
}

