package paulkane.battlesnake.service;

import org.springframework.stereotype.Service;
import paulkane.battlesnake.MoveService;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;
import paulkane.battlesnake.model.domain.MOVE;
import paulkane.battlesnake.move.MoveStrategy;
import paulkane.battlesnake.move.MoveStrategyFactory;
import paulkane.battlesnake.safety.MoveSafety;
import paulkane.battlesnake.safety.SAFE;

import java.util.ArrayList;
import java.util.List;

import static paulkane.battlesnake.safety.SAFE.MAYBE;
import static paulkane.battlesnake.safety.SAFE.YES;

@Service
public class StrategyBasedMoveService implements MoveService {
    private final MoveStrategyFactory moveStrategyFactory;
    private final List<MoveSafety> moveSafetyList;

    public StrategyBasedMoveService(MoveStrategyFactory moveStrategyFactory,
                                    List<MoveSafety> moveSafetyList) {
        this.moveStrategyFactory = moveStrategyFactory;
        this.moveSafetyList = moveSafetyList;
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
        List<MOVE> maybeSafe = new ArrayList<>();
        remainingMoves.remove(move);

        MOVE attemptMove = move;

        while (remainingMoves.size() > 0) {
            SAFE safe = safe(attemptMove, battleSnakeRequest);
            if (safe.equals(YES)) {
                break;
            }
            if (safe.equals(MAYBE)) {
                maybeSafe.add(attemptMove);
            }
            attemptMove = moveStrategyFactory.fallBackMoveStrategy().move(battleSnakeRequest);

            if (remainingMoves.contains(attemptMove)) {
                remainingMoves.remove(attemptMove);
            } else {
                attemptMove = remainingMoves.remove(0);
            }
        }

        if (remainingMoves.size() == 0 && maybeSafe.size() > 0) {
            return maybeSafe.get(0);
        }

        return attemptMove;
    }

    private SAFE safe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        for (MoveSafety moveSafety : moveSafetyList) {
            SAFE isSafe = moveSafety.isItSafe(move, battleSnakeRequest);
            if (!isSafe.equals(YES)) {
                return isSafe;
            }
        }

        return YES;
    }
}
