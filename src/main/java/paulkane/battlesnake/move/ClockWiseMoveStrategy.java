package paulkane.battlesnake.move;

import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.MOVE;

import java.util.List;

@Component
public class ClockWiseMoveStrategy implements MoveStrategy {

    private static final String NAME = "clockwise";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public MOVE move(BattleSnakeRequest moveRequest) {

        Body head = getHead(moveRequest.getYou().getBody());
        Body neck;
        if (moveRequest.getYou().getBody().size() > 1) {
            neck = moveRequest.getYou().getBody().get(1);
        } else {
            neck = head;
        }

        MOVE direction = MOVE.left;

        if (head.equals(neck)) {
            return MOVE.down;
        }

        if (head.getX() == neck.getX()) {
            if (head.getY() == moveRequest.getBoard().getHeight() - 1) {
                return MOVE.left;
            }
            if (head.getY() > neck.getY()) {
                return MOVE.down;
            }
            if (head.getY() == 0) {
                return MOVE.right;
            }
            return MOVE.up;
        }

        if (head.getY() == neck.getY()) {
            if (head.getX() == 0) {
                return MOVE.up;
            }
            if (head.getX() < neck.getX()) {
                return MOVE.left;
            }
            if (head.getX() == moveRequest.getBoard().getWidth() - 1) {
                return MOVE.down;
            }
            return MOVE.right;
        }

        return direction;
    }

    private Body getTail(List<Body> body) {
        return body.get(body.size() - 1);
    }

    private Body getHead(List<Body> body) {
        return body.get(0);
    }
}
