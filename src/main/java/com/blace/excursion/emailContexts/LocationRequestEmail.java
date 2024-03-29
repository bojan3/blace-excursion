package com.blace.excursion.emailContexts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationRequestEmail extends EmailContext {
    @Override
    public <T> void init(T context) {
        String email = (String) context;
        setTemplateLocation("location-request");
        setSubject("Zahtev za održavanje izleta");
        setFrom("potuc3@gmail.com");
        setTo(email);
    }

    public void setExcursionInfo(Date date, Integer minPersons, Integer maxPersons, String responseUrl) {
        DateFormat dateFormat = new SimpleDateFormat("dd.12.yyyy");
        String dateString = dateFormat.format(date);

        put("date", dateString);
        put("min", minPersons);
        put("max", maxPersons);
        put("approveUrl", responseUrl + "/true");
        put("disapproveUrl", responseUrl + "/false");
    }
}
