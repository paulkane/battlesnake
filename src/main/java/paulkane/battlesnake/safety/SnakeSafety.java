package paulkane.battlesnake.safety;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.MOVE;
import paulkane.battlesnake.model.domain.Snake;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(value = 1)
public class SnakeSafety implements MoveSafety {
    @Override
    public SAFE isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {

        Body head = battleSnakeRequest.getYou().getHead();

        Body moveTo = Body.builder().x(head.getX()).y(head.getY()).build();

        switch (move) {
            case UP -> moveTo.setY(moveTo.getY() - 1);
            case DOWN -> moveTo.setY(moveTo.getY() + 1);
            case RIGHT -> moveTo.setX(moveTo.getX() + 1);
            case LEFT -> moveTo.setX(moveTo.getX() - 1);
        }

        List<Snake> snakes = new ArrayList<>(battleSnakeRequest.getBoard().getSnakes());
        return isItEmpty(moveTo, snakes);
    }

    private SAFE isItEmpty(Body moveTo, List<Snake> snakes) {
        for (Snake snake : snakes) {
            for (Body body : snake.getBody()) {
                if (body.equals(moveTo)) {
                    if (body.equals(snake.getTail())) {
                        return SAFE.MAYBE;
                    }
                    return SAFE.NO;
                }
            }
        }

        return SAFE.YES;
    }
}
