package paulkane.battlesnake.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import paulkane.battlesnake.SnakeHelper;
import paulkane.battlesnake.model.BattleSnakeRequest;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StrategyBasedMoveServiceITest {

    @Autowired StrategyBasedMoveService strategyBasedMoveService;

    @Test
    public void testEnv() throws IOException {

        /*
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":0,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":100,"body":[{"x":10,"y":2},{"x":10,"y":2},{"x":10,"y":2}]},{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":100,"body":[{"x":7,"y":11},{"x":7,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":100,"body":[{"x":6,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}]},"you":{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":100,"body":[{"x":7,"y":11},{"x":7,"y":11},{"x":7,"y":11}]}}
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":0,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":100,"body":[{"x":10,"y":2},{"x":10,"y":2},{"x":10,"y":2}]},{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":100,"body":[{"x":7,"y":11},{"x":7,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":100,"body":[{"x":6,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}]},"you":{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":100,"body":[{"x":10,"y":2},{"x":10,"y":2},{"x":10,"y":2}]}}
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":0,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":100,"body":[{"x":10,"y":2},{"x":10,"y":2},{"x":10,"y":2}]},{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":100,"body":[{"x":7,"y":11},{"x":7,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":100,"body":[{"x":6,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}]},"you":{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":100,"body":[{"x":6,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}}
0-clockwise: [100] [Body(x=10, y=2), Body(x=10, y=2), Body(x=10, y=2)]
0-random: [100] [Body(x=7, y=11), Body(x=7, y=11), Body(x=7, y=11)]
0-food: [100] [Body(x=6, y=2), Body(x=6, y=2), Body(x=6, y=2)]
0-random: moved=RIGHT
0-food: moved=LEFT
0-clockwise: moved=LEFT
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":1,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":99,"body":[{"x":9,"y":2},{"x":10,"y":2},{"x":10,"y":2}]},{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":99,"body":[{"x":8,"y":11},{"x":7,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":99,"body":[{"x":5,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}]},"you":{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":99,"body":[{"x":9,"y":2},{"x":10,"y":2},{"x":10,"y":2}]}}
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":1,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":99,"body":[{"x":9,"y":2},{"x":10,"y":2},{"x":10,"y":2}]},{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":99,"body":[{"x":8,"y":11},{"x":7,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":99,"body":[{"x":5,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}]},"you":{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":99,"body":[{"x":5,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}}
1-clockwise: [99] [Body(x=9, y=2), Body(x=10, y=2), Body(x=10, y=2)]
1-food: [99] [Body(x=5, y=2), Body(x=6, y=2), Body(x=6, y=2)]
1-clockwise: moved=RIGHT
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":1,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"2f92afee-a0b6-4819-b3b8-b75ba51d8526","name":"clockwise","health":99,"body":[{"x":9,"y":2},{"x":10,"y":2},{"x":10,"y":2}]},{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":99,"body":[{"x":8,"y":11},{"x":7,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":99,"body":[{"x":5,"y":2},{"x":6,"y":2},{"x":6,"y":2}]}]},"you":{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":99,"body":[{"x":8,"y":11},{"x":7,"y":11},{"x":7,"y":11}]}}
1-food: moved=DOWN
1-random: [99] [Body(x=8, y=11), Body(x=7, y=11), Body(x=7, y=11)]
1-random: moved=DOWN
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":2,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":98,"body":[{"x":8,"y":12},{"x":8,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":98,"body":[{"x":5,"y":3},{"x":5,"y":2},{"x":6,"y":2}]}]},"you":{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":98,"body":[{"x":5,"y":3},{"x":5,"y":2},{"x":6,"y":2}]}}
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":2,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":98,"body":[{"x":8,"y":12},{"x":8,"y":11},{"x":7,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":98,"body":[{"x":5,"y":3},{"x":5,"y":2},{"x":6,"y":2}]}]},"you":{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":98,"body":[{"x":8,"y":12},{"x":8,"y":11},{"x":7,"y":11}]}}
2-food: [98] [Body(x=5, y=3), Body(x=5, y=2), Body(x=6, y=2)]
2-random: [98] [Body(x=8, y=12), Body(x=8, y=11), Body(x=7, y=11)]
2-food: moved=LEFT
2-random: moved=DOWN
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":3,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":97,"body":[{"x":8,"y":13},{"x":8,"y":12},{"x":8,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":97,"body":[{"x":4,"y":3},{"x":5,"y":3},{"x":5,"y":2}]}]},"you":{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":97,"body":[{"x":4,"y":3},{"x":5,"y":3},{"x":5,"y":2}]}}
{"game":{"id":"f47d4ba3-d202-4e6e-b676-8edccc28e930"},"turn":3,"board":{"height":15,"width":15,"food":[{"x":7,"y":0},{"x":9,"y":1},{"x":10,"y":3},{"x":6,"y":4},{"x":6,"y":14},{"x":8,"y":6},{"x":0,"y":13},{"x":14,"y":1},{"x":5,"y":7},{"x":12,"y":9}],"snakes":[{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":97,"body":[{"x":8,"y":13},{"x":8,"y":12},{"x":8,"y":11}]},{"id":"a3ed22de-1e62-4dde-8964-17a424ddaa91","name":"food","health":97,"body":[{"x":4,"y":3},{"x":5,"y":3},{"x":5,"y":2}]}]},"you":{"id":"5748ca78-0932-4a45-a8d5-30bd4c3601e0","name":"random","health":97,"body":[{"x":8,"y":13},{"x":8,"y":12},{"x":8,"y":11}]}}
3-food: [97] [Body(x=4, y=3), Body(x=5, y=3), Body(x=5, y=2)]
3-random: [97] [Body(x=8, y=13), Body(x=8, y=12), Body(x=8, y=11)]
3-food: moved=RIGHT
3-random: moved=DOWN
==========================Game Ended==========================
Winner was=random
         */
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"f47d4ba3-d202-4e6e-b676-8edccc28e930\"},\"turn\":3,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":7,\"y\":0},{\"x\":9,\"y\":1},{\"x\":10,\"y\":3},{\"x\":6,\"y\":4},{\"x\":6,\"y\":14},{\"x\":8,\"y\":6},{\"x\":0,\"y\":13},{\"x\":14,\"y\":1},{\"x\":5,\"y\":7},{\"x\":12,\"y\":9}],\"snakes\":[{\"id\":\"5748ca78-0932-4a45-a8d5-30bd4c3601e0\",\"name\":\"random\",\"health\":97,\"body\":[{\"x\":8,\"y\":13},{\"x\":8,\"y\":12},{\"x\":8,\"y\":11}]},{\"id\":\"a3ed22de-1e62-4dde-8964-17a424ddaa91\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":4,\"y\":3},{\"x\":5,\"y\":3},{\"x\":5,\"y\":2}]}]},\"you\":{\"id\":\"5748ca78-0932-4a45-a8d5-30bd4c3601e0\",\"name\":\"random\",\"health\":97,\"body\":[{\"x\":8,\"y\":13},{\"x\":8,\"y\":12},{\"x\":8,\"y\":11}]}}"));
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"f47d4ba3-d202-4e6e-b676-8edccc28e930\"},\"turn\":3,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":7,\"y\":0},{\"x\":9,\"y\":1},{\"x\":10,\"y\":3},{\"x\":6,\"y\":4},{\"x\":6,\"y\":14},{\"x\":8,\"y\":6},{\"x\":0,\"y\":13},{\"x\":14,\"y\":1},{\"x\":5,\"y\":7},{\"x\":12,\"y\":9}],\"snakes\":[{\"id\":\"5748ca78-0932-4a45-a8d5-30bd4c3601e0\",\"name\":\"random\",\"health\":97,\"body\":[{\"x\":8,\"y\":13},{\"x\":8,\"y\":12},{\"x\":8,\"y\":11}]},{\"id\":\"a3ed22de-1e62-4dde-8964-17a424ddaa91\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":4,\"y\":3},{\"x\":5,\"y\":3},{\"x\":5,\"y\":2}]}]},\"you\":{\"id\":\"a3ed22de-1e62-4dde-8964-17a424ddaa91\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":4,\"y\":3},{\"x\":5,\"y\":3},{\"x\":5,\"y\":2}]}}\n"));
    }

    @Test
    public void what() throws IOException {
        /*
{"game":{"id":"807936ba-aff8-4b4d-ade0-4538d23c24b5"},"turn":0,"board":{"height":15,"width":15,"food":[{"x":1,"y":13},{"x":5,"y":6},{"x":4,"y":14},{"x":2,"y":4},{"x":0,"y":9},{"x":6,"y":9},{"x":12,"y":1},{"x":7,"y":10},{"x":7,"y":2},{"x":8,"y":3}],"snakes":[{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":100,"body":[{"x":4,"y":1},{"x":4,"y":1},{"x":4,"y":1}]},{"id":"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519","name":"random","health":100,"body":[{"x":13,"y":14},{"x":13,"y":14},{"x":13,"y":14}]},{"id":"1d60ef55-64c7-4357-b371-a03a253ac503","name":"food","health":100,"body":[{"x":2,"y":5},{"x":2,"y":5},{"x":2,"y":5}]}]},"you":{"id":"1d60ef55-64c7-4357-b371-a03a253ac503","name":"food","health":100,"body":[{"x":2,"y":5},{"x":2,"y":5},{"x":2,"y":5}]}}
{"game":{"id":"807936ba-aff8-4b4d-ade0-4538d23c24b5"},"turn":0,"board":{"height":15,"width":15,"food":[{"x":1,"y":13},{"x":5,"y":6},{"x":4,"y":14},{"x":2,"y":4},{"x":0,"y":9},{"x":6,"y":9},{"x":12,"y":1},{"x":7,"y":10},{"x":7,"y":2},{"x":8,"y":3}],"snakes":[{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":100,"body":[{"x":4,"y":1},{"x":4,"y":1},{"x":4,"y":1}]},{"id":"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519","name":"random","health":100,"body":[{"x":13,"y":14},{"x":13,"y":14},{"x":13,"y":14}]},{"id":"1d60ef55-64c7-4357-b371-a03a253ac503","name":"food","health":100,"body":[{"x":2,"y":5},{"x":2,"y":5},{"x":2,"y":5}]}]},"you":{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":100,"body":[{"x":4,"y":1},{"x":4,"y":1},{"x":4,"y":1}]}}
{"game":{"id":"807936ba-aff8-4b4d-ade0-4538d23c24b5"},"turn":0,"board":{"height":15,"width":15,"food":[{"x":1,"y":13},{"x":5,"y":6},{"x":4,"y":14},{"x":2,"y":4},{"x":0,"y":9},{"x":6,"y":9},{"x":12,"y":1},{"x":7,"y":10},{"x":7,"y":2},{"x":8,"y":3}],"snakes":[{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":100,"body":[{"x":4,"y":1},{"x":4,"y":1},{"x":4,"y":1}]},{"id":"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519","name":"random","health":100,"body":[{"x":13,"y":14},{"x":13,"y":14},{"x":13,"y":14}]},{"id":"1d60ef55-64c7-4357-b371-a03a253ac503","name":"food","health":100,"body":[{"x":2,"y":5},{"x":2,"y":5},{"x":2,"y":5}]}]},"you":{"id":"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519","name":"random","health":100,"body":[{"x":13,"y":14},{"x":13,"y":14},{"x":13,"y":14}]}}
0-food: [100] [Body(x=2, y=5), Body(x=2, y=5), Body(x=2, y=5)]
0-clockwise: [100] [Body(x=4, y=1), Body(x=4, y=1), Body(x=4, y=1)]
0-random: [100] [Body(x=13, y=14), Body(x=13, y=14), Body(x=13, y=14)]
0-clockwise: moved=RIGHT
0-food: moved=RIGHT
0-random: moved=DOWN
{"game":{"id":"807936ba-aff8-4b4d-ade0-4538d23c24b5"},"turn":1,"board":{"height":15,"width":15,"food":[{"x":1,"y":13},{"x":5,"y":6},{"x":4,"y":14},{"x":2,"y":4},{"x":0,"y":9},{"x":6,"y":9},{"x":12,"y":1},{"x":7,"y":10},{"x":7,"y":2},{"x":8,"y":3}],"snakes":[{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":99,"body":[{"x":5,"y":1},{"x":4,"y":1},{"x":4,"y":1}]},{"id":"1d60ef55-64c7-4357-b371-a03a253ac503","name":"food","health":99,"body":[{"x":3,"y":5},{"x":2,"y":5},{"x":2,"y":5}]}]},"you":{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":99,"body":[{"x":5,"y":1},{"x":4,"y":1},{"x":4,"y":1}]}}
{"game":{"id":"807936ba-aff8-4b4d-ade0-4538d23c24b5"},"turn":1,"board":{"height":15,"width":15,"food":[{"x":1,"y":13},{"x":5,"y":6},{"x":4,"y":14},{"x":2,"y":4},{"x":0,"y":9},{"x":6,"y":9},{"x":12,"y":1},{"x":7,"y":10},{"x":7,"y":2},{"x":8,"y":3}],"snakes":[{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":99,"body":[{"x":5,"y":1},{"x":4,"y":1},{"x":4,"y":1}]},{"id":"1d60ef55-64c7-4357-b371-a03a253ac503","name":"food","health":99,"body":[{"x":3,"y":5},{"x":2,"y":5},{"x":2,"y":5}]}]},"you":{"id":"1d60ef55-64c7-4357-b371-a03a253ac503","name":"food","health":99,"body":[{"x":3,"y":5},{"x":2,"y":5},{"x":2,"y":5}]}}
1-food: [99] [Body(x=3, y=5), Body(x=2, y=5), Body(x=2, y=5)]
1-clockwise: [99] [Body(x=5, y=1), Body(x=4, y=1), Body(x=4, y=1)]
1-clockwise: moved=DOWN
1-food: moved=LEFT
==========================Game Ended==========================
Winner was=clockwise


{"game":{"id":"807936ba-aff8-4b4d-ade0-4538d23c24b5"},"turn":2,"board":{"height":15,"width":15,"food":[{"x":1,"y":13},{"x":5,"y":6},{"x":4,"y":14},{"x":2,"y":4},{"x":0,"y":9},{"x":6,"y":9},{"x":12,"y":1},{"x":7,"y":10},{"x":7,"y":2},{"x":8,"y":3}],"snakes":[{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":98,"body":[{"x":5,"y":2},{"x":5,"y":1},{"x":4,"y":1}]}]},"you":{"id":"6a28ca02-9574-43b1-8d43-39fea3d15e8f","name":"clockwise","health":98,"body":[{"x":5,"y":2},{"x":5,"y":1},{"x":4,"y":1}]}}
==========================Game Ended==========================

         */
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"807936ba-aff8-4b4d-ade0-4538d23c24b5\"},\"turn\":0,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":1,\"y\":13},{\"x\":5,\"y\":6},{\"x\":4,\"y\":14},{\"x\":2,\"y\":4},{\"x\":0,\"y\":9},{\"x\":6,\"y\":9},{\"x\":12,\"y\":1},{\"x\":7,\"y\":10},{\"x\":7,\"y\":2},{\"x\":8,\"y\":3}],\"snakes\":[{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":100,\"body\":[{\"x\":4,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":1}]},{\"id\":\"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519\",\"name\":\"random\",\"health\":100,\"body\":[{\"x\":13,\"y\":14},{\"x\":13,\"y\":14},{\"x\":13,\"y\":14}]},{\"id\":\"1d60ef55-64c7-4357-b371-a03a253ac503\",\"name\":\"food\",\"health\":100,\"body\":[{\"x\":2,\"y\":5},{\"x\":2,\"y\":5},{\"x\":2,\"y\":5}]}]},\"you\":{\"id\":\"1d60ef55-64c7-4357-b371-a03a253ac503\",\"name\":\"food\",\"health\":100,\"body\":[{\"x\":2,\"y\":5},{\"x\":2,\"y\":5},{\"x\":2,\"y\":5}]}}"));
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"807936ba-aff8-4b4d-ade0-4538d23c24b5\"},\"turn\":0,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":1,\"y\":13},{\"x\":5,\"y\":6},{\"x\":4,\"y\":14},{\"x\":2,\"y\":4},{\"x\":0,\"y\":9},{\"x\":6,\"y\":9},{\"x\":12,\"y\":1},{\"x\":7,\"y\":10},{\"x\":7,\"y\":2},{\"x\":8,\"y\":3}],\"snakes\":[{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":100,\"body\":[{\"x\":4,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":1}]},{\"id\":\"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519\",\"name\":\"random\",\"health\":100,\"body\":[{\"x\":13,\"y\":14},{\"x\":13,\"y\":14},{\"x\":13,\"y\":14}]},{\"id\":\"1d60ef55-64c7-4357-b371-a03a253ac503\",\"name\":\"food\",\"health\":100,\"body\":[{\"x\":2,\"y\":5},{\"x\":2,\"y\":5},{\"x\":2,\"y\":5}]}]},\"you\":{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":100,\"body\":[{\"x\":4,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":1}]}}"));
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"807936ba-aff8-4b4d-ade0-4538d23c24b5\"},\"turn\":0,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":1,\"y\":13},{\"x\":5,\"y\":6},{\"x\":4,\"y\":14},{\"x\":2,\"y\":4},{\"x\":0,\"y\":9},{\"x\":6,\"y\":9},{\"x\":12,\"y\":1},{\"x\":7,\"y\":10},{\"x\":7,\"y\":2},{\"x\":8,\"y\":3}],\"snakes\":[{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":100,\"body\":[{\"x\":4,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":1}]},{\"id\":\"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519\",\"name\":\"random\",\"health\":100,\"body\":[{\"x\":13,\"y\":14},{\"x\":13,\"y\":14},{\"x\":13,\"y\":14}]},{\"id\":\"1d60ef55-64c7-4357-b371-a03a253ac503\",\"name\":\"food\",\"health\":100,\"body\":[{\"x\":2,\"y\":5},{\"x\":2,\"y\":5},{\"x\":2,\"y\":5}]}]},\"you\":{\"id\":\"fc382ea4-3f44-47f2-9d4d-a68bbfe1b519\",\"name\":\"random\",\"health\":100,\"body\":[{\"x\":13,\"y\":14},{\"x\":13,\"y\":14},{\"x\":13,\"y\":14}]}}"));
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"807936ba-aff8-4b4d-ade0-4538d23c24b5\"},\"turn\":1,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":1,\"y\":13},{\"x\":5,\"y\":6},{\"x\":4,\"y\":14},{\"x\":2,\"y\":4},{\"x\":0,\"y\":9},{\"x\":6,\"y\":9},{\"x\":12,\"y\":1},{\"x\":7,\"y\":10},{\"x\":7,\"y\":2},{\"x\":8,\"y\":3}],\"snakes\":[{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":99,\"body\":[{\"x\":5,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":1}]},{\"id\":\"1d60ef55-64c7-4357-b371-a03a253ac503\",\"name\":\"food\",\"health\":99,\"body\":[{\"x\":3,\"y\":5},{\"x\":2,\"y\":5},{\"x\":2,\"y\":5}]}]},\"you\":{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":99,\"body\":[{\"x\":5,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":1}]}}"));
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"807936ba-aff8-4b4d-ade0-4538d23c24b5\"},\"turn\":1,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":1,\"y\":13},{\"x\":5,\"y\":6},{\"x\":4,\"y\":14},{\"x\":2,\"y\":4},{\"x\":0,\"y\":9},{\"x\":6,\"y\":9},{\"x\":12,\"y\":1},{\"x\":7,\"y\":10},{\"x\":7,\"y\":2},{\"x\":8,\"y\":3}],\"snakes\":[{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":99,\"body\":[{\"x\":5,\"y\":1},{\"x\":4,\"y\":1},{\"x\":4,\"y\":1}]},{\"id\":\"1d60ef55-64c7-4357-b371-a03a253ac503\",\"name\":\"food\",\"health\":99,\"body\":[{\"x\":3,\"y\":5},{\"x\":2,\"y\":5},{\"x\":2,\"y\":5}]}]},\"you\":{\"id\":\"1d60ef55-64c7-4357-b371-a03a253ac503\",\"name\":\"food\",\"health\":99,\"body\":[{\"x\":3,\"y\":5},{\"x\":2,\"y\":5},{\"x\":2,\"y\":5}]}}"));
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"807936ba-aff8-4b4d-ade0-4538d23c24b5\"},\"turn\":2,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":1,\"y\":13},{\"x\":5,\"y\":6},{\"x\":4,\"y\":14},{\"x\":2,\"y\":4},{\"x\":0,\"y\":9},{\"x\":6,\"y\":9},{\"x\":12,\"y\":1},{\"x\":7,\"y\":10},{\"x\":7,\"y\":2},{\"x\":8,\"y\":3}],\"snakes\":[{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":98,\"body\":[{\"x\":5,\"y\":2},{\"x\":5,\"y\":1},{\"x\":4,\"y\":1}]}]},\"you\":{\"id\":\"6a28ca02-9574-43b1-8d43-39fea3d15e8f\",\"name\":\"clockwise\",\"health\":98,\"body\":[{\"x\":5,\"y\":2},{\"x\":5,\"y\":1},{\"x\":4,\"y\":1}]}}"));
    }

    @Test
    public void crashInToSelf() throws Exception {
        /*
        2019-04-10 17:13:13.495  INFO 63726 --- [nio-8080-exec-6] paulkane.battlesnake.Controller          : 3-food: moved=RIGHT
2019-04-10 17:13:13.495  INFO 63726 --- [nio-8080-exec-8] paulkane.battlesnake.Controller          : 3-clockwise: moved=LEFT
         */
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"28db16de-49a1-4290-baf0-6557263c4040\"},\"turn\":3,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":13,\"y\":7},{\"x\":10,\"y\":13},{\"x\":10,\"y\":1},{\"x\":3,\"y\":4},{\"x\":13,\"y\":4},{\"x\":5,\"y\":3},{\"x\":4,\"y\":8},{\"x\":2,\"y\":2},{\"x\":4,\"y\":6},{\"x\":6,\"y\":13}],\"snakes\":[{\"id\":\"67dcb688-48ce-4f49-9c7b-4d3c7e11e31f\",\"name\":\"clockwise\",\"health\":97,\"body\":[{\"x\":4,\"y\":3},{\"x\":3,\"y\":3},{\"x\":3,\"y\":2}]},{\"id\":\"6bdf72ff-35f2-4dfa-a498-30fb144b843a\",\"name\":\"random\",\"health\":97,\"body\":[{\"x\":5,\"y\":0},{\"x\":4,\"y\":0},{\"x\":3,\"y\":0}]},{\"id\":\"43b2d872-3fc1-48c7-9455-0130161304c1\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":12,\"y\":11},{\"x\":13,\"y\":11},{\"x\":13,\"y\":12}]}]},\"you\":{\"id\":\"43b2d872-3fc1-48c7-9455-0130161304c1\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":12,\"y\":11},{\"x\":13,\"y\":11},{\"x\":13,\"y\":12}]}}"));
        printRequest(SnakeHelper.battleSnakeRequestFromJson(
            "{\"game\":{\"id\":\"28db16de-49a1-4290-baf0-6557263c4040\"},\"turn\":3,\"board\":{\"height\":15,\"width\":15,\"food\":[{\"x\":13,\"y\":7},{\"x\":10,\"y\":13},{\"x\":10,\"y\":1},{\"x\":3,\"y\":4},{\"x\":13,\"y\":4},{\"x\":5,\"y\":3},{\"x\":4,\"y\":8},{\"x\":2,\"y\":2},{\"x\":4,\"y\":6},{\"x\":6,\"y\":13}],\"snakes\":[{\"id\":\"67dcb688-48ce-4f49-9c7b-4d3c7e11e31f\",\"name\":\"clockwise\",\"health\":97,\"body\":[{\"x\":4,\"y\":3},{\"x\":3,\"y\":3},{\"x\":3,\"y\":2}]},{\"id\":\"6bdf72ff-35f2-4dfa-a498-30fb144b843a\",\"name\":\"random\",\"health\":97,\"body\":[{\"x\":5,\"y\":0},{\"x\":4,\"y\":0},{\"x\":3,\"y\":0}]},{\"id\":\"43b2d872-3fc1-48c7-9455-0130161304c1\",\"name\":\"food\",\"health\":97,\"body\":[{\"x\":12,\"y\":11},{\"x\":13,\"y\":11},{\"x\":13,\"y\":12}]}]},\"you\":{\"id\":\"67dcb688-48ce-4f49-9c7b-4d3c7e11e31f\",\"name\":\"clockwise\",\"health\":97,\"body\":[{\"x\":4,\"y\":3},{\"x\":3,\"y\":3},{\"x\":3,\"y\":2}]}}"));
    }

    private void printRequest(BattleSnakeRequest battleSnakeRequest) {
        System.out.printf("%s-%s: move=%s%n", battleSnakeRequest.getTurn(), battleSnakeRequest.getYou().getName(),
            strategyBasedMoveService.move(battleSnakeRequest).getMove());
    }
}