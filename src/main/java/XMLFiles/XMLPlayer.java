package XMLFiles;

public class XMLPlayer
{
    private String name;

    private int Count_of_games;

    private float Average_Points;

    public XMLPlayer(String name, int Count_of_games, float Average_Points)
    {
        this.name = name;
        this.Count_of_games = Count_of_games;
        this.Average_Points = Average_Points;
    }

    public String getName()
    {
        return name;
    }
    public int getCount_of_games()
    {
        return Count_of_games;
    }
    public float getAverage_Points()
    {
        return Average_Points;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setCount_of_games(int Count_of_games)
    {
        this.Count_of_games = Count_of_games;
    }
    public void setAverage_Points(float Average_Points)
    {
        this.Average_Points = Average_Points;
    }
}
