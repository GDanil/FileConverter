import Converter.Converter;

import static Converter.Converter.convertToJSON;

public class Main
{
    public static void main(String[] args)
    {
        Converter Conv = new Converter();
        convertToJSON("D:/practice java/Converter/Basketball.xml" ,"D:/practice java/Converter/Basketball2.json");
    }
}
