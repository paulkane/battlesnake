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
@Order(value = 999)
public class HeadToHeadPrediction implements MovePrediction {
    @Override
    public boolean isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        Body moveTo = moveTo(move, battleSnakeRequest.getYou().getHead());
        String mySnake = battleSnakeRequest.getYou().getId();
        int mySize = battleSnakeRequest.getYou().getBody().size();

        List<Body> moveAgain = moveAgain(moveTo);

        for (Snake otherSnake : battleSnakeRequest.getBoard().getSnakes()) {
            Body snakeHead = otherSnake.getHead();
            int otherSnakeSize = otherSnake.getBody().size();
            if (otherSnake.getId().equals(mySnake) || mySize > otherSnakeSize) {
                continue;
            }

            for (Body targetSpace : moveAgain) {
                if (snakeHead.equals(targetSpace)) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<Body> moveAgain(Body head) {
        List<Body> moveAgain = new ArrayList<>();

        for (MOVE move : MOVE.values()) {
            moveAgain.add(moveTo(move, head));
        }

        return moveAgain;
    }

    private Body moveTo(MOVE move, Body body) {
        return switch (move) {
            case UP -> new Body(body.getX(), body.getY() - 1);
            case DOWN -> new Body(body.getX(), body.getY() + 1);
            case LEFT -> new Body(body.getX() - 1, body.getY());
            case RIGHT -> new Body(body.getX() + 1, body.getY());
        };
    }
}
