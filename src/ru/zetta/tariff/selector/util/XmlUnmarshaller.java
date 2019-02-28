package ru.zetta.tariff.selector.util;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.zetta.tariff.selector.dto.ArticleContent;
import ru.zetta.tariff.selector.exception.UnmarshalException;
import ru.zetta.tariff.selector.util.parser.ArticleContentParser;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.io.StringReader;

public class XmlUnmarshaller {

    private SAXParser saxParser;
    private ArticleContentParser articleContentParser;

   public XmlUnmarshaller(SAXParser saxParser, ArticleContentParser articleContentParser) {
        this.saxParser = saxParser;
        this.articleContentParser = articleContentParser;
    }

    public ArticleContent unmarshal(final String text) throws UnmarshalException {
        if (text == null) {
            return null;
        }
        try (StringReader reader = new StringReader(text)) {
            saxParser.parse(new InputSource(reader), articleContentParser);
            return  articleContentParser.getArticleContent();
        } catch (SAXException | IOException e) {

            throw new UnmarshalException(e);
        }
    }
}
