package paulkane.battlesnake.move;

import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.MOVE;

import java.util.Random;

@Component
public class RandomMoveStrategy implements MoveStrategy {
    private final Random random = new Random();

    @Override
    public String getName() {
        return "random";
    }

    @Override
    public MOVE move(BattleSnakeRequest moveRequest) {
        return MOVE.values()[random.nextInt(4)];
    }
}
