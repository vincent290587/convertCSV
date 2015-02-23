package convertCSV;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.TimeZone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vincent
 */
class MonPoint {

    private final float lat_;
    private final float lon_;
    private final float ele_;
    private final float secjour_;
    private final int bpm_;

    MonPoint(float lati, float longi, float elev, float sec_jour) {
        lat_ = lati;
        lon_ = longi;
        ele_ = elev;
        secjour_ = sec_jour;
        bpm_ = -1;
    }

    MonPoint(float lati, float longi, float elev, float sec_jour, int bpm) {
        lat_ = lati;
        lon_ = longi;
        ele_ = elev;
        secjour_ = sec_jour;
        bpm_ = bpm;
    }

    float getLatitude() {
        return lat_;
    }

    float getLongitude() {
        return lon_;
    }

    float getElevation() {
        return ele_;
    }

    float getTime() {
        return secjour_;
    }
    
    public String getFormattedTime() {
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);
        long millis_a0 = calendar.getTimeInMillis();
        
        long tempsPoint = (long) this.getTime();
        String retour_ = df.format(new Date(tempsPoint * 1000 + millis_a0));

        return retour_;
    }

    public String toString() {

        String retour_ = "<trkpt lat=\"" + this.getLatitude() + "\" lon=\"" + this.getLongitude() + "\">\n";
        retour_ += "<time>" + this.getFormattedTime() + "</time>\n";
        retour_ += "<ele>" + this.getElevation() + "</ele>\n";

        if (this.bpm_ > 0) {
            retour_ += "<extensions>\n"
                    + "<gpxtpx:TrackPointExtension>\n"
                    + "<gpxtpx:hr>" + this.bpm_ + "</gpxtpx:hr>"
                    + "</gpxtpx:TrackPointExtension>\n"
                    + "</extensions>\n";
        }
        
        retour_ += "</trkpt>\n";

        return retour_;
    }

}
