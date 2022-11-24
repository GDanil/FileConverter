package JSONFiles;

import java.util.ArrayList;

public class JSON
{
    private ArrayList<JSONPlayer> Players;

    public JSON()
    {
        this.Players = new ArrayList<>();
    }

    public ArrayList<JSONPlayer> getPlayers()
    {
        return Players;
    }

    public void addPlayer(String name, int Count_of_games, float Average_Points, String Conference)
    {
        Players.add(new JSONPlayer(name, Count_of_games, Average_Points, Conference));
    }

    public int returnLength()
    {
        return Players.size();
    }

    public JSONPlayer returnLastPlayer()
    {
        if (Players.size() > 0)
            return Players.get(Players.size() - 1);
        else
            return null;
    }

}
