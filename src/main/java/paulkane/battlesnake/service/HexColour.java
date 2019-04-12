package paulkane.battlesnake.service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HexColour {
    private static final List<Color> AVAILABLE_COLOURS;
    private static final Random RANDOM = new Random();

    static {
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
            Color.WHITE,
            Color.YELLOW,
            Color.WHITE
        ));
    }

    public static String getColour() {
        Color color = AVAILABLE_COLOURS.remove(RANDOM.nextInt(AVAILABLE_COLOURS.size()));
        return "#" + Integer.toHexString(color.getRGB()).substring(2);
    }
}
