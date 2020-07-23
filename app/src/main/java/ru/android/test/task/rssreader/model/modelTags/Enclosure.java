package ru.android.test.task.rssreader.model.modelTags;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "enclosure", strict = false)
public class Enclosure {
    @Attribute
    private String length;
    @Attribute
    private String type;
    @Attribute
    private String url;

    public String getLength() {
        return length;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
