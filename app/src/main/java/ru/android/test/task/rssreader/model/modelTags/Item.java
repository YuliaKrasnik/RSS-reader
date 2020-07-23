package ru.android.test.task.rssreader.model.modelTags;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Item {
    @Element(required = false)
    private Enclosure enclosure;
    @Element(required = false)
    private String link;
    @Element(required = false)
    private String description;
    @Element(required = false)
    private String title;
    @Element(required = false)
    private String pubDate;

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }
}