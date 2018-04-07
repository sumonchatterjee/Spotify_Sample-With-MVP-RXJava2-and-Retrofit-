package data.model;

import java.util.List;

/**
 * Created by sumon.chatterjee on 05/04/18.
 */

public class TrackResponse {
private String href;
private List<Items>items;
private int total;
private String next;

    public String getHref() {
        return href;
    }

    public List<Items> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    public String getNext() {
        return next;
    }
}
