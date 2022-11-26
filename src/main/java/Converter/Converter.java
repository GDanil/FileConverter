package Converter;

import JSONtoXML.JSONtoXML;
import XMLtoJSON.XMLtoJSON;

public class Converter
{

    public static void convertToJSON(String pathToXML, String pathToNewFile)
    {
        try
        {
            XMLtoJSON.ParserXML(pathToXML);
            XMLtoJSON.createFile(XMLtoJSON.ConvetToJSON(), pathToNewFile);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



    public static void convertToXML(String pathToJSON, String pathToNewFile)
    {
        try
        {
            JSONtoXML.parseJSON(pathToJSON);
            JSONtoXML.createXML(JSONtoXML.ConvertToXML(), pathToNewFile);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /*public List<String> testFilter(String pathToJSON)
    {
        try
        {
            JSONtoXML.parseJSON(pathToJSON);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        JSON Wq = JSONtoXML.getPlayers();

        List<JSONPlayer> list = Wq.getPlayers();

       // List<String> list3 = list.stream().filter(e->e.getAverage_Points().contains(12)).map(e->e.getName()).collect(Collectors.toList());
        return list3;
    }*/
}
