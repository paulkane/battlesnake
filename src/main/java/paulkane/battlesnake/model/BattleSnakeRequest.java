package paulkane.battlesnake.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import paulkane.battlesnake.model.domain.Board;
import paulkane.battlesnake.model.domain.Game;
import paulkane.battlesnake.model.domain.Snake;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattleSnakeRequest {
    private Game game;
    private int turn;
    private Board board;
    private Snake you;
}
