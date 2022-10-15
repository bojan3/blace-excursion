package com.blace.excursion.emailContexts;

import java.util.Date;

public class LocationRequestEmail extends EmailContext {
    @Override
    public <T> void init(T context) {
        String email = (String) context;
        setTemplateLocation("location-request");
        setSubject("Location Request");
        setFrom("potuc3@gmail.com");
        setTo(email);
    }

    public void setExcursionInfo(Date date, Integer minPersons, Integer maxPersons, String responseUrl) {
        put("date", date);
        put("min", minPersons);
        put("max", maxPersons);
        put("approveUrl", responseUrl + "/true");
        put("disapproveUrl", responseUrl + "/false");
    }
}
