package paulkane.battlesnake.service;

import org.springframework.stereotype.Service;
import paulkane.battlesnake.StartService;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.StartResponse;
import paulkane.battlesnake.model.domain.HEAD_TYPE;
import paulkane.battlesnake.model.domain.TAIL_TYPE;

@Service
public class RandomSnakeStartService implements StartService {

    private static final String RED = "#ff0000";
    private final RandomStrategy randomStrategy;

    public RandomSnakeStartService(RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public StartResponse start(BattleSnakeRequest startRequest) {
        return StartResponse.builder()
            .color(RED)
            .headType(HEAD_TYPE.values()[randomStrategy.getNextInt(HEAD_TYPE.values().length)])
            .tailType(TAIL_TYPE.values()[randomStrategy.getNextInt(TAIL_TYPE.values().length)])
            .build();
    }
}
