package XMLFiles;
import java.util.ArrayList;
public class XMLPosition
{
    private String name;
    private ArrayList<XMLPlayer> Players;

    public XMLPosition(String name)
    {
        this.name = name;
        this.Players = new ArrayList<>();
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<XMLPlayer> getPlayers()
    {
        return Players;
    }

    public void addPlayer(String name, int Count_of_games, float Average_Points)
    {
        Players.add(new XMLPlayer(name, Count_of_games, Average_Points));
    }

    public int returnLength()
    {
        return Players.size();
    }
}
