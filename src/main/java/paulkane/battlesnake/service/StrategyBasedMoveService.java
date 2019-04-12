package paulkane.battlesnake.service;

import org.springframework.stereotype.Service;
import paulkane.battlesnake.MoveService;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;
import paulkane.battlesnake.model.domain.MOVE;
import paulkane.battlesnake.move.MoveStrategy;
import paulkane.battlesnake.move.MoveStrategyFactory;
import paulkane.battlesnake.safety.MovePrediction;
import paulkane.battlesnake.safety.MoveSafety;

import java.util.ArrayList;
import java.util.List;

@Service
public class StrategyBasedMoveService implements MoveService {
    private final MoveStrategyFactory moveStrategyFactory;
    private final List<MoveSafety> moveSafetyList;
    private final List<MovePrediction> movePredictionList;

    public StrategyBasedMoveService(MoveStrategyFactory moveStrategyFactory,
                                    List<MoveSafety> moveSafetyList,
                                    List<MovePrediction> movePredictionList) {
        this.moveStrategyFactory = moveStrategyFactory;
        this.moveSafetyList = moveSafetyList;
        this.movePredictionList = movePredictionList;
    }

    @Override
    public MoveResponse move(BattleSnakeRequest moveRequest) {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy(moveRequest.getYou().getName());
        System.out.println(moveStrategy.getName());
        MOVE move = finalMove(moveStrategy.move(moveRequest), moveRequest);

        return MoveResponse.builder()
            .move(move)
            .build();
    }

    private MOVE finalMove(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        List<MOVE> remainingMoves = new ArrayList<>(List.of(MOVE.values()));
        remainingMoves.remove(move);

        MOVE attemptMove = move;

        while (remainingMoves.size() > 0 && !isSafe(attemptMove, battleSnakeRequest)) {
            attemptMove = moveStrategyFactory.fallBackMoveStrategy().move(battleSnakeRequest);

            if (remainingMoves.contains(attemptMove)) {
                remainingMoves.remove(attemptMove);
            } else {
                attemptMove = remainingMoves.remove(0);
            }
        }

        if (remainingMoves.size() > 0) {
            List<MOVE> predictMove = new ArrayList<>(remainingMoves);
            do {
                attemptMove = predictMove.remove(0);
            }
            while (predictMove.size() > 0 && !isPredictSafe(attemptMove, battleSnakeRequest));
        }

        return attemptMove;
    }

    private boolean isPredictSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        for (MovePrediction movePrediction : movePredictionList) {
            if (!movePrediction.isItSafe(move, battleSnakeRequest)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        for (MoveSafety moveSafety : moveSafetyList) {
            if (!moveSafety.isItSafe(move, battleSnakeRequest)) {
                return false;
            }
        }

        return true;
    }
}
