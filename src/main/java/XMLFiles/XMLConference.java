package XMLFiles;


import java.util.ArrayList;

public class XMLConference
{
    private String name;
    private ArrayList<XMLClub> Clubs;

    public XMLConference(String name)
    {
        this.name = name;
        this.Clubs = new ArrayList<XMLClub>();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void addClub(String name)
    {
        Clubs.add(new XMLClub(name));
    }

    public ArrayList<XMLClub> getClubs()
    {
        return Clubs;
    }

    public int returnLength()
    {
        return Clubs.size();
    }

}
