package XMLFiles;
import java.util.ArrayList;
public class XMLClub
{
    private String name;
    private ArrayList<XMLPosition> Positions;

    public XMLClub(String name)
    {
        this.name = name;
        this.Positions = new ArrayList<>();
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<XMLPosition> getPositions()
    {
        return Positions;
    }

    public void addPosition(String name)
    {
        Positions.add(new XMLPosition(name));
    }

    public int returnLength(){
        return Positions.size();
    }

}
