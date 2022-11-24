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

    /*public void  doSum()
    {
        XML w = XMLtoJSON.getNBA();
        for (XMLConference publisher : w.getConferences())
        {
            int y = 2;
        }
    }*/

}
