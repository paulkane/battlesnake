package paulkane.battlesnake.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int height;
    private int width;
    private List<Food> food;
    private List<Snake> snakes;
}
