package paulkane.battlesnake.model.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MOVE {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right");

    private final String value;

    MOVE(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

}
