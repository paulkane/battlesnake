package paulkane.battlesnake.model.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TAIL_TYPE {
    REGULAR("regular"),
    BLOCK_BUM("block-bum"),
    BOLT("bolt"),
    CURLED("curled"),
    FAT_RATTLE("fat-rattle"),
    FRECKLED("freckled"),
    HOOK("hook"),
    PIXEL("pixel"),
    ROUND_BUM("round-bum"),
    SHARP("sharp"),
    SKINNY("skinny"),
    SMALL_RATTLE("small-rattle");

    String value;

    TAIL_TYPE(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
