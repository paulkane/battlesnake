package paulkane.battlesnake.safety;

import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.MOVE;

@Component
public class WallSafety implements MoveSafety {
    @Override
    public boolean isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        Body head = battleSnakeRequest.getYou().getHead();

        return switch (move) {
            case UP -> head.getY() != 0;
            case RIGHT -> head.getX() < battleSnakeRequest.getBoard().getWidth() - 1;
            case LEFT -> head.getX() != 0;
            case DOWN -> head.getY() < battleSnakeRequest.getBoard().getHeight() - 1;
        };
    }
}
