package com.adobe.aem.guides.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RSSFeedModel {

    private static final Logger LOG = LoggerFactory.getLogger(RSSFeedModel.class);

    @Inject
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String[] feedUrls;

    @ValueMapValue
    private String numberOfItems;

    private final List<RSSItem> items = new ArrayList<>();

    private boolean isError = false;

    @PostConstruct
    protected void init() {
        if (feedUrls == null) {
            return;
        }

        for (String feedUrl : feedUrls) {
            if (isValidFeedUrl(feedUrl)) {
                processFeed(feedUrl);
            }
        }
    }

    private boolean isValidFeedUrl(String feedUrl) {
        return feedUrl != null && !feedUrl.isEmpty() && isConnectionSuccessful(feedUrl);
    }

    private void processFeed(String feedUrl) {
        try {
            URL url = new URL(feedUrl);
            InputStream is = url.openConnection().getInputStream();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Disable XML external entities
            dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            dbFactory.setXIncludeAware(false);
            dbFactory.setExpandEntityReferences(false);

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    RSSItem item = createRSSItem(eElement);
                    items.add(item);
                }
            }
        } catch (UnknownHostException e) {
            LOG.error("Unknown host exception while processing feed: {}", feedUrl, e);
            isError = true;
        } catch (Exception e) {
            LOG.error("Error processing feed: {}", feedUrl, e);
            isError = true;
        }
    }

    private RSSItem createRSSItem(Element eElement) {
        RSSItem item = new RSSItem();
        item.setTitle(getElementTextContent(eElement, "title"));
        item.setLink(getElementTextContent(eElement, "link"));
        item.setDescription(getElementTextContent(eElement, "description"));
        item.setContent(getElementTextContent(eElement, "content:encoded"));
        item.setPubDate(formatPubDate(getElementTextContent(eElement, "pubDate")));
        item.setSource(getElementTextContent(eElement, "dc:source"));
        item.setCreators(getCreatorList(eElement.getElementsByTagName("dc:creator")));
        return item;
    }

    private String getElementTextContent(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        return nodeList.getLength() > 0 ? nodeList.item(0).getTextContent() : "";
    }

    private String formatPubDate(String pubDate) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.RFC_1123_DATE_TIME;
            ZonedDateTime parsedDate = ZonedDateTime.parse(pubDate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            return outputFormatter.format(parsedDate);
        } catch (Exception e) {
            LOG.error("Error formatting publication date: {}", pubDate, e);
            return pubDate;
        }
    }

    private List<String> getCreatorList(NodeList creatorNodes) {
        List<String> creators = new ArrayList<>();
        for (int i = 0; i < creatorNodes.getLength(); i++) {
            creators.add(creatorNodes.item(i).getTextContent());
        }
        return creators;
    }

    public String getNumberOfItems() {
        return numberOfItems;
    }

    public List<RSSItem> getItems() {
        return items;
    }

    public boolean isError() {
        return isError;
    }

    private boolean isConnectionSuccessful(String feedUrl) {
        try {
            URL url = new URL(feedUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (UnknownHostException e) {
            LOG.error("Unknown host exception while checking connection to feed: {}", feedUrl, e);
            isError = true;
            return false;
        } catch (Exception e) {
            LOG.error("Error checking connection to feed: {}", feedUrl, e);
            isError = true;
            return false;
        }
    }


    public static class RSSItem {
        private String title;
        private String link;
        private String description;
        private String content;
        private String pubDate;
        private String source;
        private List<String> creators;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public List<String> getCreators() {
            return creators;
        }

        public void setCreators(List<String> creators) {
            this.creators = creators;
        }
    }
}
