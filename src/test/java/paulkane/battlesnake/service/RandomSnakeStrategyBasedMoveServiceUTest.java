package paulkane.battlesnake.service;

import org.junit.Test;
import paulkane.battlesnake.model.StartResponse;
import paulkane.battlesnake.model.domain.HEAD_TYPE;
import paulkane.battlesnake.model.domain.TAIL_TYPE;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomSnakeStrategyBasedMoveServiceUTest {

    private final RandomStrategy randomStrategy = mock(RandomStrategy.class);
    private final HexColour hexColour = new HexColour(randomStrategy);
    private final RandomSnakeStartService randomSnakeStartService =
        new RandomSnakeStartService(randomStrategy, hexColour);

    @Test
    public void testStart() {
        when(randomStrategy.getNextInt(HEAD_TYPE.values().length)).thenReturn(1);
        when(randomStrategy.getNextInt(TAIL_TYPE.values().length)).thenReturn(2);

        StartResponse firstResponse = randomSnakeStartService.start(null);

        assertThat(firstResponse.getHeadType()).isEqualTo(HEAD_TYPE.BELUGA);
        assertThat(firstResponse.getTailType()).isEqualTo(TAIL_TYPE.BOLT);
        assertThat(firstResponse.getColor()).isEqualTo("#00ffff");
    }
}