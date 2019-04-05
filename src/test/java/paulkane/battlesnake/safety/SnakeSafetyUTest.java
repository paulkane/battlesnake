package paulkane.battlesnake.safety;

import org.junit.Test;
import paulkane.battlesnake.model.BattleSnakeRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.body;
import static paulkane.battlesnake.SnakeHelper.snake;
import static paulkane.battlesnake.model.domain.MOVE.DOWN;
import static paulkane.battlesnake.model.domain.MOVE.LEFT;
import static paulkane.battlesnake.model.domain.MOVE.RIGHT;
import static paulkane.battlesnake.model.domain.MOVE.UP;

public class SnakeSafetyUTest {

    private SnakeSafety snakeSafety = new SnakeSafety();

    @Test
    public void givenAnotherSnakeNextToMeDoNotCrashIntoIt_UP() {
        assertThat(snakeSafety.isItSafe(UP, nextToBattleSnakeRequest())).isFalse();
    }

    @Test
    public void givenAnotherSnakeNextToMeDoNotCrashIntoIt_DOWN() {
        assertThat(snakeSafety.isItSafe(DOWN, nextToBattleSnakeRequest())).isFalse();
    }

    @Test
    public void givenAnotherSnakeNextToMeDoNotCrashIntoIt_LEFT() {
        assertThat(snakeSafety.isItSafe(LEFT, nextToBattleSnakeRequest())).isFalse();
    }

    @Test
    public void givenAnotherSnakeNextToMeDoNotCrashIntoIt_RIGHT() {
        assertThat(snakeSafety.isItSafe(RIGHT, nextToBattleSnakeRequest())).isFalse();
    }

    @Test
    public void givenAnotherSnakeNowhereNearMeCanMoveAnyDirection() {
        assertThat(snakeSafety.isItSafe(RIGHT, notNextToBattleSnakeRequest())).isTrue();
        assertThat(snakeSafety.isItSafe(LEFT, notNextToBattleSnakeRequest())).isTrue();
        assertThat(snakeSafety.isItSafe(UP, notNextToBattleSnakeRequest())).isTrue();
        assertThat(snakeSafety.isItSafe(DOWN, notNextToBattleSnakeRequest())).isTrue();
    }

    @Test
    public void doNotCrashIntoMyOwnSnake() {
        assertThat(snakeSafety.isItSafe(RIGHT, wrappedByOwnSnakeBattleSnakeRequest())).isFalse();
        assertThat(snakeSafety.isItSafe(LEFT, wrappedByOwnSnakeBattleSnakeRequest())).isFalse();
        assertThat(snakeSafety.isItSafe(UP, wrappedByOwnSnakeBattleSnakeRequest())).isFalse();
        assertThat(snakeSafety.isItSafe(DOWN, wrappedByOwnSnakeBattleSnakeRequest())).isFalse();
    }

    private BattleSnakeRequest nextToBattleSnakeRequest() {
        return battleSnakeRequest(
            snake(body(4, 4)),
            snake(body(3, 4)),
            snake(body(5, 4)),
            snake(body(4, 3)),
            snake(body(4, 5))
        );
    }

    private BattleSnakeRequest notNextToBattleSnakeRequest() {
        return battleSnakeRequest(
            snake(body(10, 10)),
            snake(body(3, 4)),
            snake(body(5, 4)),
            snake(body(4, 3)),
            snake(body(4, 5))
        );
    }

    private BattleSnakeRequest wrappedByOwnSnakeBattleSnakeRequest() {
        return battleSnakeRequest(
            snake(
                body(5, 5),
                body(5, 4),
                body(6, 4),
                body(6, 5),
                body(6, 6),
                body(5, 6),
                body(4, 6),
                body(4, 5),
                body(4, 4)
            )
        );
    }
}