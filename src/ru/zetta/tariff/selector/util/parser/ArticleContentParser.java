package ru.zetta.tariff.selector.util.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.zetta.tariff.selector.dto.ArticleContent;

public class ArticleContentParser extends DefaultHandler {


    private ArticleContent articleContent;
    private String currentElement;
    private boolean inElementTag;
    private String currentTagNameValue;

    public ArticleContent getArticleContent() {
        return articleContent;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(XMLTags.ArticleContent.ELEMENT)) {
            inElementTag = true;
            currentTagNameValue = attributes.getValue(XMLTags.ArticleContent.NAME);
        }
        if (qName.equals(XMLTags.ArticleContent.CONTENT)) {
            currentElement = qName;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (inElementTag && currentElement.equals(XMLTags.ArticleContent.CONTENT)) {
            String value = new String(ch, start, length);
            if (currentTagNameValue.equals(XMLTags.ArticleContent.RATE)) {
                try {
                    int rate = Integer.parseInt(value);
                    this.articleContent.setRate(rate);
                } catch (NumberFormatException ex) {

                    throw new SAXException(ex);
                }
            } else if (currentTagNameValue.equals(XMLTags.ArticleContent.PRICE)) {
                try {
                    double price = Double.parseDouble(value);
                    this.articleContent.setPrice(price);
                } catch (NumberFormatException ex) {

                    throw new SAXException(ex);
                }
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(XMLTags.ArticleContent.ELEMENT)) {
            inElementTag = false;
            currentTagNameValue = "";
        }
        currentElement = "";
    }

    @Override
    public void startDocument() throws SAXException {
        this.articleContent = new ArticleContent();
        currentElement = "";
        currentTagNameValue = "";

        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

    }
}
