package paulkane.battlesnake.service;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class HexColour {
    private final List<Color> AVAILABLE_COLOURS;
    private final RandomStrategy randomStrategy;

    public HexColour(RandomStrategy randomStrategy) {
        AVAILABLE_COLOURS = new ArrayList<>(List.of(
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.DARK_GRAY,
            Color.GRAY,
            Color.GREEN,
            Color.LIGHT_GRAY,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.RED,
            Color.YELLOW
        ));
        this.randomStrategy = randomStrategy;
    }

    String getColour() {
        Color color = AVAILABLE_COLOURS.get(randomStrategy.getNextInt(AVAILABLE_COLOURS.size()));
        return "#" + Integer.toHexString(color.getRGB()).substring(2);
    }
}
