package paulkane.battlesnake.move;

import org.junit.Test;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Snake;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.snake;

public class HungryFoodStrategyUTest {

    private final ClockWiseMoveStrategy clockWiseMoveStrategy = mock(ClockWiseMoveStrategy.class);
    private final EagerFoodStrategy eagerFoodStrategy = mock(EagerFoodStrategy.class);
    private final HungryFoodStrategy hungryFoodStrategy =
        new HungryFoodStrategy(clockWiseMoveStrategy, eagerFoodStrategy);

    @Test
    public void useClockwiseMoveStrategy() {
        hungryFoodStrategy.move(battleSnakeRequest(snake()));
        verify(clockWiseMoveStrategy).move(any(BattleSnakeRequest.class));
    }

    @Test
    public void useEagerFoodStrategy() {
        Snake snake = snake();
        snake.setHealth(49);
        hungryFoodStrategy.move(battleSnakeRequest(snake));
        verify(eagerFoodStrategy).move(any(BattleSnakeRequest.class));
    }
}