package data.model;

import java.util.List;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class AlbumResponse {

    private String href;
    private List<Items> items;
    private int limit;
    private String next;
    private int total;

    public String getHref() {
        return href;
    }

    public List<Items> getItems() {
        return items;
    }

    public int getLimit() {
        return limit;
    }

    public String getNext() {
        return next;
    }

    public int getTotal() {
        return total;
    }
}
