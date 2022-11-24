package XMLtoJSON;

import JSONFiles.JSON;
import JSONFiles.JSONPlayer;
import XMLFiles.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLtoJSON extends DefaultHandler
{
    private static XML NBA = new XML();

    public static XML ParserXML(String path_to_file) throws ParserConfigurationException, SAXException, IOException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        File f = new File(path_to_file);
        parser.parse(f, handler);

        return NBA;
    }

    private static class XMLHandler extends DefaultHandler
    {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            if (qName.equals("Conference"))
            {
                String name = attributes.getValue("name");
                NBA.addConference(name);
            }
            else if (qName.equals("Club"))
            {
                String name = attributes.getValue("name");
                NBA.getConferences().get(NBA.returnLength() - 1).addClub(name);
            }
            else if (qName.equals("Position"))
            {
                String Position = attributes.getValue("Position");
                NBA.getConferences().get(NBA.returnLength() - 1).getClubs()
                        .get(NBA.getConferences().get(NBA.returnLength() - 1).returnLength() - 1).addPosition(Position);
            }
            else if (qName.equals("Player"))
            {
                String name = attributes.getValue("name");
                Integer Count_of_games = Integer.parseInt(attributes.getValue("Count_of_games"));
                Float Average_Points = Float.parseFloat(attributes.getValue("Average_Points"));
                NBA.getConferences().get(NBA.returnLength() - 1).getClubs()
                        .get(NBA.getConferences().get(NBA.returnLength() - 1).returnLength() - 1).getPositions()
                        .get(NBA.getConferences().get(NBA.returnLength() - 1).getClubs()
                                .get(NBA.getConferences().get(NBA.returnLength() - 1).returnLength() - 1).returnLength() - 1)
                        .addPlayer( name, Count_of_games,  Average_Points);
            }
        }
    }

    private static JSONPlayer getCurrentPlayer(String name, ArrayList<JSONPlayer> arrayList)
    {
        JSONPlayer Player = null;
        for (JSONPlayer Player1 : arrayList)
        {
            if (Player1.getName().equals(name)) Player = Player1;
        }
        return Player;
    }

    public static JSON ConvetToJSON()
    {
        JSON jPlayers = new JSON();

        for (int i = 0; i < NBA.returnLength(); i++)
        {
            XMLConference conference = NBA.getConferences().get(i);
            for (int j = 0; j < conference.returnLength(); j++)
            {
                XMLClub Club = conference.getClubs().get(j);
                for (int k = 0; k < Club.returnLength(); k++)
                {
                    XMLPosition Position = Club.getPositions().get(k);
                    for (int m = 0; m < Position.returnLength(); m++)
                    {
                        XMLPlayer Player = Position.getPlayers().get(m);
                        JSONPlayer checker = getCurrentPlayer(Player.getName(), jPlayers.getPlayers());
                        if (checker == null)
                        {
                            jPlayers.addPlayer(Player.getName(), Player.getCount_of_games(), Player.getAverage_Points(), conference.getName());
                            JSONPlayer JSONPlayer = jPlayers.getPlayers().get(jPlayers.returnLength() - 1);

                            JSONPlayer.addClub(Club.getName());
                            JSONPlayer.addPosition(Position.getName());
                        } else
                        {
                            checker.addClub(Club.getName());
                            checker.addPosition(Position.getName());
                        }
                    }
                }
            }

        }
        return jPlayers;
    }


    public static void createFile(JSON json, String path) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), json);
    }

    public static XML getNBA()
    {
        return NBA;
    }
}
