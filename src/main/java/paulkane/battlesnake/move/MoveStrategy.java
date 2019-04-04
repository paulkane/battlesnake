package paulkane.battlesnake.move;

import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.MOVE;

public interface MoveStrategy {
    String getName();

    MOVE move(BattleSnakeRequest moveRequest);
}
