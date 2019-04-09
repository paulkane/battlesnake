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

        Body head = battleSnakeRequest.getYou().getBody().get(0);

        Body moveTo = Body.builder().x(head.getX()).y(head.getY()).build();

        switch (move) {
            case UP:
                moveTo.setY(moveTo.getY() - 1);
                break;
            case DOWN:
                moveTo.setY(moveTo.getY() + 1);
                break;
            case RIGHT:
                moveTo.setX(moveTo.getX() + 1);
                break;
            case LEFT:
                moveTo.setX(moveTo.getX() - 1);
                break;
        }

        List<Snake> snakes = new ArrayList<>(battleSnakeRequest.getBoard().getSnakes());
        snakes.add(battleSnakeRequest.getYou());
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
