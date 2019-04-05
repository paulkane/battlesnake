package paulkane.battlesnake.move;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoveStrategyFactoryUTest {

    @Value("${snake.name.prefix}") private String snakeNamePrefix;
    @Autowired private MoveStrategyFactory moveStrategyFactory;

    @Test
    public void testDefaultMoveStrategy() {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy("");
        assertTrue(moveStrategy instanceof ClockWiseMoveStrategy);
    }

    @Test
    public void testStripWithPrefixFromStrategy() {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy(snakeNamePrefix + "clockwise");
        assertTrue(moveStrategy instanceof ClockWiseMoveStrategy);
    }

    @Test
    public void testClockWiseMoveStrategy() {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy("clockwise");
        assertTrue(moveStrategy instanceof ClockWiseMoveStrategy);
    }

    @Test
    public void testFallBackMoveStrategy() {
        MoveStrategy moveStrategy = moveStrategyFactory.fallBackMoveStrategy();
        assertTrue(moveStrategy instanceof RandomMoveStrategy);
    }
}