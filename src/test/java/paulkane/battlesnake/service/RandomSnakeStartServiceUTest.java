package paulkane.battlesnake.service;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class RandomSnakeStartServiceUTest {

    @Test
    public void colours() {
        Color your_color = Color.RED;

        String hex = "#"+Integer.toHexString(your_color.getRGB()).substring(2);
        System.out.println(hex);
    }
}