package paulkane.battlesnake.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import paulkane.battlesnake.model.domain.MOVE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveResponse {
    private MOVE move;
}
