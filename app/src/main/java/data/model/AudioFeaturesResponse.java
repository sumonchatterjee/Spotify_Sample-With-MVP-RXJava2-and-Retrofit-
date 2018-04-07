package data.model;

public class AudioFeaturesResponse  {
    private float danceability;
    private float energy;
    private int key;
    private float loudness;
    private float speechiness;
    private float acousticness;
    private String uri;



    public float getDanceability() {
        return danceability;
    }

    public float getEnergy() {
        return energy;
    }

    public int getKey() {
        return key;
    }

    public float getLoudness() {
        return loudness;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public float getAcousticness() {
        return acousticness;
    }

    public String getUri() {
        return uri;
    }
}
