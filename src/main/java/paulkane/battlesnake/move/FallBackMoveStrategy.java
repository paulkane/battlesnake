package paulkane.battlesnake.move;

import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.MOVE;

import java.util.Random;

@Component
public class FallBackMoveStrategy implements MoveStrategy {
    private Random random = new Random();

    @Override
    public String getName() {
        return "fallback";
    }

    @Override
    public MOVE move(BattleSnakeRequest moveRequest) {
        return MOVE.values()[random.nextInt(4)];
    }
}
