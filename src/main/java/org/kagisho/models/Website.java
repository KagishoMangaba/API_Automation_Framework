package org.kagisho.models;

public class Website {

    private String website;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Website{" +
                "website='" + website + '\'' +
                '}';
    }
}
