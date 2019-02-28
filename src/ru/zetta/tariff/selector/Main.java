package ru.zetta.tariff.selector;

import org.xml.sax.SAXException;
import ru.zetta.tariff.selector.dto.ArticleContent;
import ru.zetta.tariff.selector.exception.UnmarshalException;
import ru.zetta.tariff.selector.util.XmlUnmarshaller;
import ru.zetta.tariff.selector.util.parser.ArticleContentParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public  static void main(String[] args) throws ParserConfigurationException, SAXException {
        XmlUnmarshaller xmlUnmarshaller=new XmlUnmarshaller(
                SAXParserFactory.newInstance().newSAXParser(),
                new ArticleContentParser());
        try {
           ArticleContent articleContent= xmlUnmarshaller.unmarshal("<?xml version=\"1.0\"?>\n" +
                    "\n" +
                    "<root available-locales=\"ru_RU\" default-locale=\"ru_RU\">\n" +
                    "\t<dynamic-element name=\"rate\" type=\"ddm-integer\" index-type=\"keyword\" index=\"0\" instance-id=\"exwx\">\n" +
                    "\t\t<dynamic-content language-id=\"ru_RU\"><![CDATA[2]]></dynamic-content>\n" +
                    "\t</dynamic-element>\n" +
                    "\t<dynamic-element name=\"price\" type=\"ddm-number\" index-type=\"keyword\" index=\"0\" instance-id=\"pmkf\">\n" +
                    "\t\t<dynamic-content language-id=\"ru_RU\"><![CDATA[200]]></dynamic-content>\n" +
                    "\t</dynamic-element>\n" +
                    "</root>");
            System.out.println(articleContent.getRate()+" "+ articleContent.getPrice());
        } catch (UnmarshalException e) {
            System.out.println("here");
            e.printStackTrace();
        }
    }
}
