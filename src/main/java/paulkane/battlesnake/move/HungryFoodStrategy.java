package paulkane.battlesnake.move;

import org.springframework.stereotype.Component;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.MOVE;

@Component
public class HungryFoodStrategy implements MoveStrategy {

    private final ClockWiseMoveStrategy clockWiseMoveStrategy;
    private final EagerFoodStrategy eagerFoodStrategy;

    public HungryFoodStrategy(ClockWiseMoveStrategy clockWiseMoveStrategy, EagerFoodStrategy eagerFoodStrategy) {
        this.clockWiseMoveStrategy = clockWiseMoveStrategy;
        this.eagerFoodStrategy = eagerFoodStrategy;
    }

    @Override

    public String getName() {
        return "hungry-food";
    }

    @Override
    public MOVE move(BattleSnakeRequest moveRequest) {
        if (moveRequest.getYou().getHealth() > 20) {
            return clockWiseMoveStrategy.move(moveRequest);
        }
        return eagerFoodStrategy.move(moveRequest);
    }
}
