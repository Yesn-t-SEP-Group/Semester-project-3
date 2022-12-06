namespace Domain.DTOs;

public class SearchPostParametersDto
{
    public int? category { get;}
    public string? TitleContains { get;}
    //public string? BodyContains { get;}

    public SearchPostParametersDto(int? category, string? titleContains/*, string? */)
    {
        this.category = category;
        //BodyContains = bodyContains;
        TitleContains = titleContains;
    }
}