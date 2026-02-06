package org.kagisho.data;

import org.kagisho.models.Location;
import org.kagisho.models.Place;
import org.kagisho.utilities.JsonReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public Place addPlacePayLoad() throws IOException {

        // Read JSON payload into Place object
        Place place = JsonReaderUtil.readJson("data/AddPlace.json");

        // Set fields dynamically
        place.setAccuracy(50);
        place.setPhone_number("060 1234567");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setAddress("161 Maude St, Sandown, Sandton, 2196");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        place.setTypes(myList);

        Location location = new Location();
        location.setLat(232.11);
        location.setLng(12.332);
        place.setLocation(location);
        return place;
    }
}
