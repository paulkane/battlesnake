package paulkane.battlesnake;

import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.StartResponse;

public interface StartService {
    StartResponse start(BattleSnakeRequest startRequest);
}
