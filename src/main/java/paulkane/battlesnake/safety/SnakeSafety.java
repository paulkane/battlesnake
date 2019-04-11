package paulkane.battlesnake.safety;

import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.MOVE;
import paulkane.battlesnake.model.domain.Snake;

import java.util.ArrayList;
import java.util.List;

@Component
public class SnakeSafety implements MoveSafety {
    @Override
    public boolean isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {

        Body head = battleSnakeRequest.getYou().getHead();

        Body moveTo = Body.builder().x(head.getX()).y(head.getY()).build();

        switch (move) {
            case UP -> moveTo.setY(moveTo.getY() - 1);
            case DOWN -> moveTo.setY(moveTo.getY() + 1);
            case RIGHT -> moveTo.setX(moveTo.getX() + 1);
            case LEFT -> moveTo.setX(moveTo.getX() - 1);
        }

        List<Snake> snakes = new ArrayList<>(battleSnakeRequest.getBoard().getSnakes());
        return !isThereASnakeAt(moveTo, snakes);
    }

    private boolean isThereASnakeAt(Body moveTo, List<Snake> snakes) {
        for (Snake snake : snakes) {
            for (Body body : snake.getBody()) {
                if (body.equals(moveTo)) {
                    return true;
                }
            }
        }

        return false;
    }
}
