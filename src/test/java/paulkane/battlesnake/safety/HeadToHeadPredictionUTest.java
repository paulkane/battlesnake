package paulkane.battlesnake.safety;

import org.junit.Test;
import paulkane.battlesnake.SnakeHelper;
import paulkane.battlesnake.model.domain.MOVE;

import static org.assertj.core.api.Assertions.assertThat;
import static paulkane.battlesnake.SnakeHelper.battleSnakeRequest;
import static paulkane.battlesnake.SnakeHelper.body;
import static paulkane.battlesnake.SnakeHelper.snake;

public class HeadToHeadPredictionUTest {

    private final HeadToHeadPrediction headToHeadPrediction = new HeadToHeadPrediction();

    @Test
    public void safeToMoveUp() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.UP, battleSnakeRequest(snake(body(5, 5)),
            snake(body(5, 6)),
            snake(body(4, 5)),
            snake(body(6, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void safeToMoveDown() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.DOWN, battleSnakeRequest(snake(body(5, 5)),
            snake(body(5, 4)),
            snake(body(4, 5)),
            snake(body(6, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void safeToMoveLeft() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.LEFT, battleSnakeRequest(snake(body(5, 5)),
            snake(body(5, 6)),
            snake(body(5, 4)),
            snake(body(6, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void safeToMoveRight() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.RIGHT, battleSnakeRequest(snake(body(5, 5)),
            snake(body(5, 6)),
            snake(body(5, 4)),
            snake(body(4, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void notSafeToMoveUp() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.UP, battleSnakeRequest(snake(body(5, 5)),
            snake(body(5, 3))
        ));

        assertThat(isSafe).isEqualTo(SAFE.MAYBE);
    }

    @Test
    public void notSafeToMoveDown() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.DOWN, battleSnakeRequest(snake(body(5, 5)),
            snake(body(5, 7))
        ));

        assertThat(isSafe).isEqualTo(SAFE.MAYBE);
    }

    @Test
    public void notSafeToMoveLeft() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.LEFT, battleSnakeRequest(snake(body(5, 5)),
            snake(body(3, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.MAYBE);
    }

    @Test
    public void notSafeToMoveRight() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.RIGHT, battleSnakeRequest(snake(body(5, 5)),
            snake(body(7, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.MAYBE);
    }

    @Test
    public void safeToMoveUpAsImBigger() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.UP, battleSnakeRequest(snake(body(5, 5), body(5, 4)),
            snake(body(5, 3))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void safeToMoveDownAsImBigger() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.DOWN, battleSnakeRequest(snake(body(5, 5), body(5, 4)),
            snake(body(5, 7))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void safeToMoveLeftAsImBigger() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.LEFT, battleSnakeRequest(snake(body(5, 5), body(5, 4)),
            snake(body(3, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void safeToMoveRightAsImBigger() {
        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.RIGHT, battleSnakeRequest(snake(body(5, 5), body(5, 4)),
            snake(body(7, 5))
        ));

        assertThat(isSafe).isEqualTo(SAFE.YES);
    }

    @Test
    public void safeToMove() throws Exception {

        SAFE isSafe = headToHeadPrediction.isItSafe(MOVE.UP, SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"28db16de-49a1-4290-baf0-6557263c4040\"},\"turn\":3,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":13,\"y\":7},{\"x\":10,\"y\":13},{\"x\":10,\"y\":1},{\"x\":3,\"y\":4},{\"x\":13,\"y\":4},{\"x\":5,\"y\":3},{\"x\":4,\"y\":8},{\"x\":2,\"y\":2},{\"x\":4,\"y\":6},{\"x\":6,\"y\":13}],\"snakes\":[{\"id\":\"67dcb688-48ce-4f49-9c7b-4d3c7e11e31f\",\"name\":\"clockwise\",\"health\":97,\"body\":[{\"x\":4,\"y\":3},{\"x\":3,\"y\":3},{\"x\":3,\"y\":2}]},{\"id\":\"6bdf72ff-35f2-4dfa-a498-30fb144b843a\",\"name\":\"random\",\"health\":97,\"body\":[{\"x\":5,\"y\":0},{\"x\":4,\"y\":0},{\"x\":3,\"y\":0}]},{\"id\":\"43b2d872-3fc1-48c7-9455-0130161304c1\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":12,\"y\":11},{\"x\":13,\"y\":11},{\"x\":13,\"y\":12}]}]},\"you\":{\"id\":\"43b2d872-3fc1-48c7-9455-0130161304c1\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":12,\"y\":11},{\"x\":13,\"y\":11},{\"x\":13,\"y\":12}]}}"));
        assertThat(isSafe).isEqualTo(SAFE.YES);
    }
}