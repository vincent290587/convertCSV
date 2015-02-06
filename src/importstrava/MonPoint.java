package importstrava;

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
    private final long secjour_;

    
    MonPoint(float lati, float longi, float elev, long sec_jour) {
        lat_ = lati;
        lon_ = longi;
        ele_ = elev;
        secjour_ = sec_jour;
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

    long getTime() {
        return secjour_;
    }
    
    
}
