package paulkane.battlesnake.move;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoveStrategyFactoryUTest {

    @Autowired private MoveStrategyFactory moveStrategyFactory;

    @Test
    public void testDefaultMoveStrategy() {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy("");
        assertNotNull(moveStrategy);
    }

    @Test
    public void testStripPaulFromStrategy() {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy("paulkane-clockwise");
        assertTrue(moveStrategy instanceof ClockWiseMoveStrategy);
    }

    @Test
    public void testClockWiseMoveStrategy() {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy("clockwise");
        assertTrue(moveStrategy instanceof ClockWiseMoveStrategy);
    }
}