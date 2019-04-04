package paulkane.battlesnake.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import paulkane.battlesnake.model.domain.HEAD_TYPE;
import paulkane.battlesnake.model.domain.TAIL_TYPE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartResponse {
    //#ff0000
    private String color;
    private HEAD_TYPE headType;
    private TAIL_TYPE tailType;
}
