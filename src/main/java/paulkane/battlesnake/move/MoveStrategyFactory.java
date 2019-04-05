package paulkane.battlesnake.move;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MoveStrategyFactory {

    private final String snakeNamePrefix;
    private final String moveStrategyDefault;
    private final String moveStrategyFallback;
    private Map<String, MoveStrategy> moveStrategies = new HashMap<>();

    public MoveStrategyFactory(@Value("${snake.name.prefix:}") String snakeNamePrefix,
                               @Value("${move.strategy.default}") String moveStrategyDefault,
                               @Value("${move.strategy.fallback}") String moveStrategyFallback,
                               List<MoveStrategy> moveStrategies) {
        this.moveStrategyDefault = moveStrategyDefault;
        this.moveStrategyFallback = moveStrategyFallback;
        initStrategies(moveStrategies);
        this.snakeNamePrefix = snakeNamePrefix;
    }

    private void initStrategies(List<MoveStrategy> moveStrategies) {
        for (MoveStrategy moveStrategy : moveStrategies) {
            this.moveStrategies.put(moveStrategy.getName().toLowerCase(), moveStrategy);
        }
    }

    public MoveStrategy moveStrategy(String strategyName) {
        String name = strip(strategyName);

        if (moveStrategies.containsKey(name)) {
            return moveStrategies.get(name);
        }

        return defaultMoveStrategy();
    }

    private MoveStrategy defaultMoveStrategy() {
        return moveStrategies.get(moveStrategyDefault);
    }

    public MoveStrategy fallBackMoveStrategy() {
        return moveStrategies.get(moveStrategyFallback);
    }

    private String strip(String strategyName) {
        if (strategyName.startsWith(snakeNamePrefix)) {
            return strategyName.substring(snakeNamePrefix.length());
        }
        return strategyName;
    }
}
