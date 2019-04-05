package paulkane.battlesnake;

import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Board;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.Food;
import paulkane.battlesnake.model.domain.Snake;

import java.util.Arrays;

public class SnakeHelper {
    public static Snake snake(Body... body) {
        return Snake.builder().name("Test Snake").body(Arrays.asList(body)).build();
    }

    public static Body body(int x, int y) {
        return new Body(x, y);
    }

    public static Food food(int x, int y) {
        return new Food(x, y);
    }

    public static BattleSnakeRequest battleSnakeRequest(Snake you, Snake... otherSnakes) {
        return BattleSnakeRequest.builder()
            .board(
                Board.builder().height(15).width(15)
                    .snakes(Arrays.asList(otherSnakes))
                    .build()
            )
            .you(you)
            .build();
    }
}
