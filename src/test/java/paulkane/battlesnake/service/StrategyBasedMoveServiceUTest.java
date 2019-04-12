package paulkane.battlesnake.service;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;
import paulkane.battlesnake.model.domain.MOVE;
import paulkane.battlesnake.model.domain.Snake;
import paulkane.battlesnake.move.MoveStrategy;
import paulkane.battlesnake.move.MoveStrategyFactory;
import paulkane.battlesnake.safety.MoveSafety;
import paulkane.battlesnake.safety.SnakeSafety;
import paulkane.battlesnake.safety.WallSafety;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.body;
import static paulkane.battlesnake.SnakeHelper.snake;

public class StrategyBasedMoveServiceUTest {

    private final MoveStrategyFactory moveStrategyFactory = mock(MoveStrategyFactory.class);
    private final MoveStrategy moveStrategy = mock(MoveStrategy.class);
    private final List<MoveSafety> moveSafetyList = Lists.newArrayList(new WallSafety(), new SnakeSafety());
    private final StrategyBasedMoveService strategyBasedMoveService = new StrategyBasedMoveService(moveStrategyFactory,
        moveSafetyList);

    @Before
    public void setup() {
        when(moveStrategyFactory.moveStrategy(anyString())).thenReturn(moveStrategy);
        when(moveStrategyFactory.fallBackMoveStrategy()).thenReturn(moveStrategy);
        when(moveStrategy.move(any(BattleSnakeRequest.class))).thenReturn(MOVE.UP);
    }

    @Test
    public void testCannotMoveUpWhenNotSafeInTopRightCorner() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(snake(body(14, 0), body(13, 0)))
        );

        assertThat(actualMove.getMove()).isNotEqualTo(MOVE.UP);
    }

    @Test
    public void testCannotMoveUpWhenNotSafeBlockedByMySnake() {
        Snake snake = snake(body(0, 0), body(0, 1));
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(snake, snake)
        );

        assertThat(actualMove.getMove()).isNotEqualTo(MOVE.UP);
    }

    @Test
    public void testCannotMoveUpWhenNotSafeBlockedByAnotherSnake() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(snake(body(14, 14)), snake(body(14, 13)))
        );

        assertThat(actualMove.getMove()).isNotEqualTo(MOVE.UP);
    }

    @Test
    public void testMoveUpWhenNowhereIsSafe() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(
                snake(body(14, 0)),
                snake(body(14, 1)),
                snake(body(13, 0))
            )
        );

        assertThat(actualMove.getMove()).isEqualTo(MOVE.RIGHT);
    }

    @Test
    public void moveUpWhenItMightBeSafe() {
        Snake you = snake(body(14, 1));
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(
                you,
                you,
                snake(body(13, 1)),
                snake(body(14, 2))
            )
        );

        assertThat(actualMove.getMove()).isEqualTo(MOVE.UP);
    }
}