package paulkane.battlesnake.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomStrategy {

    private final Random random = new Random();

    public int getNextInt(int bound) {
        return random.nextInt(bound);
    }
}
