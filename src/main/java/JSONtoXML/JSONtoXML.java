package JSONtoXML;

import JSONFiles.JSON;
import JSONFiles.JSONClub;
import JSONFiles.JSONPlayer;
import JSONFiles.JSONPosition;
import XMLFiles.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class JSONtoXML
{
    private static JSON Players = new JSON();

    public static JSON parseJSON(String path) throws IOException
    {
        File f = new File(path);
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(f);

        parser.nextToken();
        parser.nextToken();

        if (parser.nextToken() == JsonToken.START_ARRAY)
        {
            //loop until token equal to "]"
            while (parser.nextToken() != JsonToken.END_ARRAY)
            {
                if (parser.getCurrentName() == null)
                    continue;
                String cur = parser.getCurrentName();

                if(cur.equals("name"))
                {
                    Players.addPlayer(" ",0,0,"" );
                    parser.nextToken();
                    Players.returnLastPlayer().setName(parser.getText());
                }
                else if(cur.equals("Count_of_games"))
                {
                    parser.nextToken();
                    Players.returnLastPlayer().setCount_of_games(Integer.parseInt(parser.getText()));
                }
                else if(cur.equals("Average_Point"))
                {
                    parser.nextToken();
                    Players.returnLastPlayer().setAverage_Points(Integer.parseInt(parser.getText()));
                }
                else if(cur.equals("Conference"))
                {
                    parser.nextToken();
                    Players.returnLastPlayer().setName(parser.getText()); // Было setPlayer
                }
                else if (cur.equals("Clubs"))
                {
                    parser.nextToken();
                    parser.nextToken();

                    //loop until end of devStudios
                    while (parser.nextToken() != JsonToken.END_ARRAY)
                    {

                        //checker if we're looking at "{" / "}"
                        if (parser.getCurrentName() == null)
                            continue;
                        if (parser.getCurrentName().equals("name"))
                        {
                            parser.nextToken();
                            Players.returnLastPlayer().addClub(parser.getText());
                        }
                    }
                }
                else if (cur.equals("Position"))
                {
                    parser.nextToken();
                    parser.nextToken();

                    //loop until end of devStudios
                    while (parser.nextToken() != JsonToken.END_ARRAY)
                    {

                        //checker if we're looking at "{" / "}"
                        if (parser.getCurrentName() == null)
                            continue;
                        if (parser.getCurrentName().equals("name"))
                        {
                            parser.nextToken();
                            Players.returnLastPlayer().addPosition(parser.getText());
                        }
                    }
                }
            }
        }
        else return null;

        return Players;
    }

    private static boolean checkVil(String name, ArrayList<XMLConference> vil)
    {
        for(XMLConference v:vil)
        {
            if(v.getName().equals(name)) return true;
        }
        return false;
    }

    private static XMLConference findVil(String name, ArrayList<XMLConference> vil)
    {
        for (int i=vil.size()-1;i>=0;i--)
        {
            if (vil.get(i).getName().equals(name))
                return vil.get(i);
        }
        return null;
    }

    private static boolean checkClub(String name, ArrayList<XMLClub> Club)
    {
        for (XMLClub c : Club)
        {
            if(c.getName().equals(name))
                return true;
        }
        return false;
    }
    private static XMLClub findClub(String name, ArrayList<XMLClub> Club)
    {
        for (int i=Club.size()-1;i>=0;i--)
        {
            if (Club.get(i).getName().equals(name))
                return Club.get(i);
        }
        return null;
    }

    private static boolean checkPosition(String name, ArrayList<XMLPosition> Position)
    {
        for (XMLPosition r : Position)
        {
            if(r.getName().equals(name))
                return true;
        }
        return false;
    }
    private static XMLPosition findPosition(String name, ArrayList<XMLPosition> Position)
    {
        for (int i=Position.size()-1;i>=0;i--)
        {
            if (Position.get(i).getName().equals(name))
                return Position.get(i);
        }
        return null;
    }

    public static XML ConvertToXML()
    {
        XML NBA = new XML();

        NBA.addConference(Players.getPlayers().get(0).getConference());

        for(int i=0;i<Players.returnLength();i++)
        {
            JSONPlayer JSONPlayer =Players.getPlayers().get(i);

            if (!checkVil(JSONPlayer.getConference(),NBA.getConferences()))
                NBA.addConference(JSONPlayer.getConference());

            XMLConference XMLConference = findVil(JSONPlayer.getConference(),NBA.getConferences());



            for (int j=0;j<JSONPlayer.getClubs().size();j++)
            {
                JSONClub JSONClub = JSONPlayer.getClubs().get(j);

                XMLClub XMLClub;
                //check if we need to create new
                if (!checkClub(JSONClub.getName(), XMLConference.getClubs()))
                {
                    //XMLvillage.setName(jsonNinja.getName());

                    //create new dev
                    XMLConference.addClub(JSONClub.getName());
                }
                XMLClub = findClub(JSONClub.getName(), XMLConference.getClubs());

                //add ranks
                for (int k = 0; k< JSONPlayer.getPosition().size(); k++)
                {
                    JSONPosition JSONPosition=JSONPlayer.getPosition().get(k);

                    XMLPosition XMLPosition;
                    if(!checkPosition(JSONPosition.getName(),XMLClub.getPositions()))
                    {
                        //XMLclan.setName(jsonNinja.getName());

                        XMLConference.addClub(JSONPosition.getName());
                    }
                    XMLPosition=findPosition(JSONPosition.getName(),XMLClub.getPositions());

                    XMLPosition.addPlayer(JSONPlayer.getName(), JSONPlayer.getCount_of_games(),
                            JSONPlayer.getAverage_Points());
                }
            }
        }

        return NBA;
    }

    private static void writeXml(OutputStream out, XML xmlClass) throws XMLStreamException
    {
        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        writer.writeStartDocument("utf-8", "1.0");

        //header
        writer.writeStartElement("NBA");


        writer.writeStartElement("Conferences");


        // insides

        for (int i = 0; i < xmlClass.returnLength(); i++)
        {
            writer.writeStartElement("Conference");
            writer.writeAttribute("name", xmlClass.getConferences().get(i).getName());

            writer.writeStartElement("Clubs");


            for (int j = 0; j < xmlClass.getConferences().get(i).getClubs().size(); j++)
            {
                XMLClub Club = xmlClass.getConferences().get(i).getClubs().get(j);

                writer.writeStartElement("Club");
                writer.writeAttribute("name", Club.getName());

                writer.writeStartElement("Position");

                for (int k = 0; k < Club.getPositions().size(); k++)
                {
                    XMLPosition Position = Club.getPositions().get(k);

                    writer.writeStartElement("Position");
                    writer.writeAttribute("Position", Position.getName());

                    writer.writeStartElement("Ninjas");

                    for (int l = 0; l < Position.getPlayers().size(); l++)
                    {
                        XMLPlayer Player = Position.getPlayers().get(l);

                        writer.writeStartElement("Player");
                        writer.writeAttribute("name", Player.getName());
                        writer.writeAttribute("Count_of_games", ((Integer)Player.getCount_of_games()).toString());
                        writer.writeAttribute("Average_Points", ((Float)Player.getAverage_Points()).toString());
                        writer.writeEndElement(); //end Player

                    }
                    writer.writeEndElement();//end Players
                    writer.writeEndElement();//end Position

                }
                writer.writeEndElement();//end Position
                writer.writeEndElement();//end Club
            }
            writer.writeEndElement();//end Clubs
            writer.writeEndElement();//end Conference

        }

        writer.writeEndElement();//end Conferences
        writer.writeEndElement();//end NBA



        writer.flush();

        writer.close();
    }

    public static void createXML(XML xmlClass, String path)
    {
        try(FileOutputStream out = new FileOutputStream(path))
        {
            writeXml(out, xmlClass);
        } catch (IOException | XMLStreamException e)
        {
            e.printStackTrace();
        }
    }
}
