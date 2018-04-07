package data.model;

import java.util.List;

/**
 * Created by sumon.chatterjee on 04/04/18.
 */

public class ProfileResponse {
    private String birthdate;
    private String country;
    private String display_name;
    private String email;
    private List<AlbumImages> images;

    public String getBirthdate() {
        return birthdate;
    }

    public String getCountry() {
        return country;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getEmail() {
        return email;
    }

    public List<AlbumImages> getImages() {
        return images;
    }


}
