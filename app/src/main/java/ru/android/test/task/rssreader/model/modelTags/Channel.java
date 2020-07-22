package ru.android.test.task.rssreader.model.modelTags;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class Channel {
    @ElementList(inline = true, required = false)
    private List<Item> item;
    @Element
    private String title;

    public List<Item> getItem() {
        return item;
    }

    public String getTitle() {
        return title;
    }
}
