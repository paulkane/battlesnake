package paulkane.battlesnake.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Snake {
    private String id;
    private String name;
    private int health;
    private List<Body> body;

    @JsonIgnore
    public Body getHead() {
        return this.body.get(0);
    }

    @JsonIgnore
    public Body getTail() {
        return this.body.get(this.body.size() - 1);
    }
}
