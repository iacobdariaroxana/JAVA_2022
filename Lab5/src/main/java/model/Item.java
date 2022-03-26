package model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "book"),
        @JsonSubTypes.Type(value = Article.class, name = "article"),
        @JsonSubTypes.Type(value = Resource.class, name = "resource")
})

public abstract class Item implements Serializable {
    protected String id;
    protected String title;
    protected String location;
    protected LocationType locationType;
    protected Map<String, Object> tags = new HashMap<>();

    public Item() {

    }

    public Item(String id, String title, String location, LocationType locationType) {
        this.setId(id);
        this.setTitle(title);
        this.setLocation(location);
        this.setLocationType(locationType);
    }

    @JsonAnySetter
    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType type) {
        this.locationType = type;
    }

    @JsonAnyGetter
    public Map<String, Object> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Item{" + "id='").append(getId()).append('\'').append(", title='").append(getTitle()).append('\'').append(", location='").append(getLocation()).append("', ");
        for (String tag : tags.keySet()) {
            str.append(tag).append("='").append(tags.get(tag)).append("', ");
        }
        str.append("locationType='").append(locationType).append("'}");
        return str.toString();
    }
}
