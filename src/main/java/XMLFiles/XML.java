package XMLFiles;
import java.util.ArrayList;
public class XML
{
    private ArrayList<XMLConference> conferences;

    public XML()
    {
        conferences = new ArrayList<XMLConference>();
    }

    public void addConference(String name)
    {
        conferences.add(new XMLConference(name));
    }

    public ArrayList<XMLConference> getConferences()
    {
        return conferences;
    }

    public int returnLength()
    {
        return conferences.size();
    }

}
