namespace Domain.DTOs;

public class SearchPostParametersDto
{
    public int? category { get;}
    public string? TitleContains { get;}
    
    public int? maxPrice { get; }
    //public string? BodyContains { get;}

    public SearchPostParametersDto(int? category, string? titleContains, int? maxPrice /*, string? */)
    {
        this.category = category;
        //BodyContains = bodyContains;
        TitleContains = titleContains;
        this.maxPrice = maxPrice;
    }
}