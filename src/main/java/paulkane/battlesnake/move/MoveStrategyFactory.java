package paulkane.battlesnake.move;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MoveStrategyFactory {

    private List<MoveStrategy> moveStrategies;

    public MoveStrategyFactory(List<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }

    public MoveStrategy moveStrategy(String strategyName) {
        String name = strip(strategyName);

        for (MoveStrategy moveStrategy : moveStrategies) {
            if (moveStrategy.getName().equalsIgnoreCase(name)) {
                return moveStrategy;
            }
        }

        return moveStrategies.get(0);
    }

    private String strip(String strategyName) {
        if (strategyName.startsWith("paulkane-")) {
            return strategyName.substring(9);
        }
        return strategyName;
    }
}
