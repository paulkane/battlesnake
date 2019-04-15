package paulkane.battlesnake.safety;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.MOVE;

@Component
@Order(value = 1)
public class WallSafety implements MoveSafety {
    @Override
    public SAFE isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        Body head = battleSnakeRequest.getYou().getHead();

        return switch (move) {
            case UP -> head.getY() != 0 ? SAFE.YES : SAFE.NO;
            case RIGHT -> head.getX() < battleSnakeRequest.getBoard().getWidth() - 1 ? SAFE.YES : SAFE.NO;
            case LEFT -> head.getX() != 0 ? SAFE.YES : SAFE.NO;
            case DOWN -> head.getY() < battleSnakeRequest.getBoard().getHeight() - 1 ? SAFE.YES : SAFE.NO;
        };
    }
}
