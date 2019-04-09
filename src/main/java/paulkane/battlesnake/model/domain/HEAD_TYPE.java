package paulkane.battlesnake.model.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HEAD_TYPE {
    REGULAR("regular"),
    BELUGA("beluga"),
    BENDR("bendr"),
    DEAD("dead"),
    EVIL("evil"),
    FANG("fang"),
    PIXEL("pixel"),
    SAFE("safe"),
    SAND_WORM("sand-worm"),
    SHADES("shades"),
    SILLY("silly"),
    SMILE("smile"),
    TONGUE("tongue");

    private final String value;

    HEAD_TYPE(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

}
