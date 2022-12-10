using System.ComponentModel;

namespace Domain.Models.enums;
public enum StatusEnum
{
    [Description("If the status is undefined")]
    Undefined = 0,
    
    [Description("If the post is active")]
    Active = 1,
    
    [Description("If the post is inactive")]
    Sold = 2,
    
    [Description("If the post is frozen for a potential seller")]
    Frozen = 3
}
