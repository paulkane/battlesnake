package paulkane.battlesnake.safety;

import org.junit.Test;
import paulkane.battlesnake.SnakeHelper;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Snake;

import static org.assertj.core.api.Assertions.assertThat;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.body;
import static paulkane.battlesnake.SnakeHelper.snake;
import static paulkane.battlesnake.model.domain.MOVE.DOWN;
import static paulkane.battlesnake.model.domain.MOVE.LEFT;
import static paulkane.battlesnake.model.domain.MOVE.RIGHT;
import static paulkane.battlesnake.model.domain.MOVE.UP;

public class SnakeSafetyUTest {

    private final SnakeSafety snakeSafety = new SnakeSafety();

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

    @Test
    public void wrappedByMyOwnSnake()throws Exception {
        BattleSnakeRequest battleSnakeRequest = SnakeHelper.battleSnakeRequestFromJson(
        "{\"game\":{\"id\":\"6d062ce6-5a76-4a99-bfc5-67eb61209e3a\"},\"turn\":40,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":13,\"y\":6},{\"x\":1,\"y\":6},{\"x\":1,\"y\":10},{\"x\":2,\"y\":3},{\"x\":14,\"y\":0},{\"x\":3,\"y\":10},{\"x\":3,\"y\":4},{\"x\":7,\"y\":8},{\"x\":0,\"y\":14},{\"x\":13,\"y\":5}],\"snakes\":[{\"id\":\"202eaf6e-3888-4b6a-8213-dca16f06a319\",\"name\":\"hungry-food\",\"health\":97,\"body\":[{\"x\":6,\"y\":13},{\"x\":7,\"y\":13},{\"x\":8,\"y\":13},{\"x\":8,\"y\":12},{\"x\":7,\"y\":12},{\"x\":6,\"y\":12},{\"x\":5,\"y\":12},{\"x\":5,\"y\":13},{\"x\":5,\"y\":14},{\"x\":6,\"y\":14},{\"x\":7,\"y\":14},{\"x\":8,\"y\":14},{\"x\":9,\"y\":14}]}]},\"you\":{\"id\":\"202eaf6e-3888-4b6a-8213-dca16f06a319\",\"name\":\"hungry-food\",\"health\":97,\"body\":[{\"x\":6,\"y\":13},{\"x\":7,\"y\":13},{\"x\":8,\"y\":13},{\"x\":8,\"y\":12},{\"x\":7,\"y\":12},{\"x\":6,\"y\":12},{\"x\":5,\"y\":12},{\"x\":5,\"y\":13},{\"x\":5,\"y\":14},{\"x\":6,\"y\":14},{\"x\":7,\"y\":14},{\"x\":8,\"y\":14},{\"x\":9,\"y\":14}]}}\n"
        );
        assertThat(snakeSafety.isItSafe(RIGHT, battleSnakeRequest)).isFalse();
        assertThat(snakeSafety.isItSafe(LEFT, battleSnakeRequest)).isFalse();
        assertThat(snakeSafety.isItSafe(UP, battleSnakeRequest)).isFalse();
        assertThat(snakeSafety.isItSafe(DOWN, battleSnakeRequest)).isFalse();

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
        Snake me = snake(
            body(5, 5),
            body(5, 4),
            body(6, 4),
            body(6, 5),
            body(6, 6),
            body(5, 6),
            body(4, 6),
            body(4, 5),
            body(4, 4)
        );
        return battleSnakeRequest(
            me, me
        );
    }
}