package paulkane.battlesnake.service;

import org.springframework.stereotype.Service;
import paulkane.battlesnake.MoveService;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;
import paulkane.battlesnake.move.MoveStrategy;
import paulkane.battlesnake.move.MoveStrategyFactory;

@Service
public class StrategyBasedMoveService implements MoveService {

    private MoveStrategyFactory moveStrategyFactory;

    public StrategyBasedMoveService(MoveStrategyFactory moveStrategyFactory) {
        this.moveStrategyFactory = moveStrategyFactory;
    }

    @Override
    public MoveResponse move(BattleSnakeRequest moveRequest) {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy(moveRequest.getYou().getName());
        return MoveResponse.builder()
            .move(moveStrategy.move(moveRequest))
            .build();
    }
}
