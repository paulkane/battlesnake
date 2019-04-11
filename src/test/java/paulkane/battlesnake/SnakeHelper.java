package paulkane.battlesnake;

import com.fasterxml.jackson.databind.ObjectMapper;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Board;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.Food;
import paulkane.battlesnake.model.domain.Snake;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class SnakeHelper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Snake snake(Body... body) {
        return Snake.builder().id(UUID.randomUUID().toString()).name("Test Snake").health(100).body(Arrays.asList(body)).build();
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

    public static BattleSnakeRequest battleSnakeRequestFromJson(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json, BattleSnakeRequest.class);
    }
}
