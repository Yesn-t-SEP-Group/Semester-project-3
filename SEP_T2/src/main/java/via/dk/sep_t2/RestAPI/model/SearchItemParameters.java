package via.dk.sep_t2.RestAPI.model;

import org.springframework.lang.Nullable;

public class SearchItemParameters
{
    @Nullable
    private String condition;
    @Nullable
    private String type;

    public SearchItemParameters(@Nullable String condition, @Nullable String type) {
        this.condition = condition;
        this.type = type;
    }

    @Nullable
    public String getCondition() {
        return condition;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public boolean isEmpty()
    {
        return condition == null && type == null;
    }
}
