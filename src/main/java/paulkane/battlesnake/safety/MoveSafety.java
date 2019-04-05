package paulkane.battlesnake.safety;

import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.MOVE;

public interface MoveSafety {
    boolean isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest);
}
