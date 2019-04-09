package paulkane.battlesnake.move;

import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.domain.Body;
import paulkane.battlesnake.model.domain.Food;
import paulkane.battlesnake.model.domain.MOVE;

public class NearestFoodStrategy implements MoveStrategy {

    private final MoveStrategyFactory moveStrategyFactory;
    private final String moveFailedStrategy;

    public NearestFoodStrategy(MoveStrategyFactory moveStrategyFactory, String moveFailedStrategy) {
        this.moveStrategyFactory = moveStrategyFactory;
        this.moveFailedStrategy = moveFailedStrategy;
    }

    @Override
    public String getName() {
        return "food";
    }

    @Override
    public MOVE move(BattleSnakeRequest moveRequest) {

        Body head = moveRequest.getYou().getBody().get(0);

        MOVE moveTo = null;
        Food nearestFood = null;
        int shortestDistance = Integer.MAX_VALUE;
        if (moveRequest.getBoard().getFood() != null) {
            for (Food food : moveRequest.getBoard().getFood()) {

                int distanceFromFood = distanceFromFood(head, food).total;

                if (distanceFromFood < shortestDistance) {
                    shortestDistance = distanceFromFood;
                    nearestFood = food;
                }
            }
        }

        if (nearestFood != null) {
            moveTo = moveTo(head, nearestFood);
        }

        if (moveTo == null) {
            moveTo = moveStrategyFactory.moveStrategy(moveFailedStrategy).move(moveRequest);
        }

        return moveTo;
    }

    private MOVE moveTo(Body head, Food nearestFood) {

        Distance distance = distanceFromFood(head, nearestFood);

        if (distance.x == 0) {
            //only up or down
            if (head.getY() < nearestFood.getY()) {
                return MOVE.DOWN;
            }
            return MOVE.UP;
        } else if (distance.y == 0) {
            //only left or right
            if (head.getX() < nearestFood.getX()) {
                return MOVE.RIGHT;
            }
            return MOVE.LEFT;
        } else if (distance.y < distance.x) {
            //only up or down
            if (head.getY() < nearestFood.getY()) {
                return MOVE.DOWN;
            }
            return MOVE.UP;
        } else if (head.getX() < nearestFood.getX()) {
            return MOVE.RIGHT;
        }
        return MOVE.LEFT;
    }

    private Distance distanceFromFood(Body head, Food food) {

        int x = Math.abs(head.getX() - food.getX());
        int y = Math.abs(head.getY() - food.getY());

        return new Distance(x, y);
    }

    static class Distance {
        final int x;
        final int y;
        final int total;

        Distance(int x, int y) {
            this.x = x;
            this.y = y;
            this.total = x + y;
        }
    }
}
