package blog.search.blogsearch.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum SortEnum {
    ACCURACY("accuracy"),
    RECENCY("recency");

    @Getter
    private final String value;

    SortEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SortEnum from(String value) {
        for (SortEnum status : SortEnum.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return ACCURACY;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
