package via.sdj3.sep_t3.model.enums;

import lombok.Getter;

@Getter
public enum StatusEnum
{
    Undefined(0),
    Active(1),
    Sold(2),
    Frozen(3);

    private final int number;
    StatusEnum(int number)
    {
        this.number=number;
    }
}
