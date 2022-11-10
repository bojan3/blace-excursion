package com.blace.excursion.emailContexts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcursionCancelNotification extends EmailContext {

    @Override
    public <T> void init(T context) {
        String email = (String) context;
        setTemplateLocation("excursion-cancel-notification");
        setSubject("Obaveštenje o otkazivanju izleta");
        setFrom("potuc3@gmail.com");
        setTo(email);
    }

    public void setNotificationInfo(Date date, String reason) {
        DateFormat dateFormat = new SimpleDateFormat("dd.12.yyyy");
        String dateString = dateFormat.format(date);

        put("date", dateString);
        put("reason", reason);
    }

}
