package paulkane.battlesnake.service;

import org.springframework.stereotype.Service;
import paulkane.battlesnake.StartService;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.StartResponse;
import paulkane.battlesnake.model.domain.HEAD_TYPE;
import paulkane.battlesnake.model.domain.TAIL_TYPE;

@Service
public class RandomSnakeStartService implements StartService {

    private final RandomStrategy randomStrategy;
    private final HexColour hexColour;

    public RandomSnakeStartService(RandomStrategy randomStrategy, HexColour hexColour) {
        this.randomStrategy = randomStrategy;
        this.hexColour = hexColour;
    }

    @Override
    public StartResponse start(BattleSnakeRequest startRequest) {
        return StartResponse.builder()
            .color(hexColour.getColour())
            .headType(HEAD_TYPE.values()[randomStrategy.getNextInt(HEAD_TYPE.values().length)])
            .tailType(TAIL_TYPE.values()[randomStrategy.getNextInt(TAIL_TYPE.values().length)])
            .build();
    }
}
