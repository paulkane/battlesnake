package paulkane.battlesnake;

import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;

public interface MoveService {
    MoveResponse move(BattleSnakeRequest moveRequest);
}
