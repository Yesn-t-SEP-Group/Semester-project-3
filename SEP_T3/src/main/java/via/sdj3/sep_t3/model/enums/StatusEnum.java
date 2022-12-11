package via.sdj3.sep_t3.model.enums;

import lombok.Getter;

/**
 * Status Enum for tracking the status of a post
 */
@Getter
public enum StatusEnum
{
    /**
     * Undefined status enum.
     */
    Undefined(0),
    /**
     * Active status, seller is looking for buyer
     */
    Active(1),
    /**
     * Sold status, seller sold the item
     */
    Sold(2),
    /**
     * Frozen status, seller found a buyer
     */
    Frozen(3);

    private final int number;
    StatusEnum(int number)
    {
        this.number=number;
    }
}
