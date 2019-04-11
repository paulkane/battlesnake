package paulkane.battlesnake.move;

import org.junit.Before;
import org.junit.Test;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Food;
import paulkane.battlesnake.model.domain.MOVE;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.body;
import static paulkane.battlesnake.SnakeHelper.food;
import static paulkane.battlesnake.SnakeHelper.snake;

public class EagerFoodStrategyUTest {

    private final MoveStrategyFactory moveStrategyFactory = mock(MoveStrategyFactory.class);
    private final EagerFoodStrategy eagerFoodStrategy = new EagerFoodStrategy();

    @Before
    public void setup() {
        when(moveStrategyFactory.moveStrategy(anyString())).thenReturn(new ClockWiseMoveStrategy());
    }

    @Test
    public void headTowardsNearestFoodDown() {
        List<Food> foods = List.of(food(5, 6), food(6, 7), food(7, 6));
        BattleSnakeRequest moveRequest = battleSnakeRequest(snake(body(5, 5)));
        moveRequest.getBoard().setFood(foods);
        MOVE move = eagerFoodStrategy.move(moveRequest);

        assertThat(move).isEqualTo(MOVE.DOWN);
    }

    @Test
    public void headTowardsNearestFoodUp() {
        List<Food> foods = List.of(food(5, 4), food(6, 7), food(7, 6));
        BattleSnakeRequest moveRequest = battleSnakeRequest(snake(body(5, 5)));
        moveRequest.getBoard().setFood(foods);
        MOVE move = eagerFoodStrategy.move(moveRequest);

        assertThat(move).isEqualTo(MOVE.UP);
    }

    @Test
    public void headTowardsNearestFoodLeft() {
        List<Food> foods = List.of(food(4, 5), food(6, 7), food(7, 6));
        BattleSnakeRequest moveRequest = battleSnakeRequest(snake(body(5, 5)));
        moveRequest.getBoard().setFood(foods);
        MOVE move = eagerFoodStrategy.move(moveRequest);

        assertThat(move).isEqualTo(MOVE.LEFT);
    }

    @Test
    public void headTowardsNearestFoodRight() {
        List<Food> foods = List.of(food(6, 5), food(6, 7), food(7, 6));
        BattleSnakeRequest moveRequest = battleSnakeRequest(snake(body(5, 5)));
        moveRequest.getBoard().setFood(foods);
        MOVE move = eagerFoodStrategy.move(moveRequest);

        assertThat(move).isEqualTo(MOVE.RIGHT);
    }

    @Test
    public void goUPWhenThereIsNoFood() {
        BattleSnakeRequest moveRequest = battleSnakeRequest(snake(body(5, 5)));
        MOVE move = eagerFoodStrategy.move(moveRequest);

        assertThat(move).isEqualTo(MOVE.UP);
    }

    @Test
    public void headTowardsNearestFoodEvenIfThereIsASnakeInTheWay() {
        List<Food> foods = List.of(food(0, 0), food(15, 15), food(7, 5));
        BattleSnakeRequest moveRequest = battleSnakeRequest(snake(body(5, 5)), snake(body(6, 5)));
        moveRequest.getBoard().setFood(foods);
        MOVE move = eagerFoodStrategy.move(moveRequest);

        assertThat(move).isEqualTo(MOVE.RIGHT);
    }
}