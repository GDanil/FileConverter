package JSONFiles;

import java.util.ArrayList;

public class JSONPlayer
{
    private String name;

    private int Count_of_games;

    private float Average_Points;

    private String Conference;

    private ArrayList<JSONClub> Clubs;
    private ArrayList<JSONPosition> Positions;

    public JSONPlayer(String name, int Count_of_games, float Average_Points,  String Conference)
    {
        this.name = name;
        this.Count_of_games = Count_of_games;
        this.Average_Points = Average_Points;
        this.Conference = Conference;
        this.Clubs=new ArrayList<JSONClub>();
        this.Positions=new ArrayList<JSONPosition>();
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

    public String getConference()
    {
        return Conference;
    }

    public ArrayList<JSONClub> getClubs()
    {
        return Clubs;
    }

    public ArrayList<JSONPosition> getPosition()
    {
        return Positions;
    }


    public void setName(String name)
    {
        this.name = name;
    }
    public void setCount_of_games (int Count_of_games)
    {
        this.Count_of_games = Count_of_games;
    }

    public void setAverage_Points (int Average_Points)
    {
        this.Average_Points = Average_Points;
    }

    public void setConference (String Conference)
    {
        this.Conference = Conference;
    }
    public void addClub (String name)
    {
        Clubs.add(new JSONClub(name));
    }
    public void addPosition(String name)
    {
        Positions.add(new JSONPosition(name));
    }
}
