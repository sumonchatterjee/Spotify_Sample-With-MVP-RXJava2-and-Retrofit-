package data.model;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class Album {

    private String name;
    private List<AlbumImages> images;
    private String id;

    public String getName() {
        return name;
    }

    public List<AlbumImages> getImages(){
        return images;
    }

    public String getId() {
        return id;
    }
}
