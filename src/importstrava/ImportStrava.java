/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importstrava;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author vincent
 */
public class ImportStrava {

    /**
     * @param fichier_
     * @throws java.io.IOException
     */
    public void importer(String fichier_) throws IOException {
        // TODO code application logic here

        float lat, lon, ele;
        long secjour;
        List<MonPoint> points = new ArrayList<>();
        Reader in = null;
        CSVParser parser;
        List<CSVRecord> list;
        GPXWriter monGPX = new GPXWriter();
        
        // lecture du CSV
        try {
            
            System.out.println("Lecture de "+fichier_);
            in = new FileReader(fichier_);
            //reader.read(null);
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImportStrava.class.getName()).log(Level.SEVERE, null, ex);
        }

        parser = new CSVParser(in, CSVFormat.EXCEL);
        //parser.parse(new File("/home/vincent/in.gpx"), Charset.forName("UTF-8"), CSVFormat.EXCEL);
        list = parser.getRecords();
        list.remove(0);

        lat = lon = ele = (float) 0.;
        secjour = 0;
        // remplissage de la liste de point GPX
        if (list!=null) {
            for (CSVRecord elem : list) {

                try {
                    lat = Float.parseFloat(elem.get(0));
                    lon = Float.parseFloat(elem.get(1));
                    ele = Float.parseFloat(elem.get(2));
                    //secjour = Long.parseLong(elem.get(3));
                    secjour = elem.getRecordNumber();
                } catch (NumberFormatException ex) {
                    System.out.println(elem.toString());
                    //monGPX.writePath("C:\\Users\\vincent\\Desktop\\today.gpx", "stravaminator", points);
                    //in.close();
                    //System.exit(1);
                }
                
                points.add(new MonPoint(lat, lon, ele, secjour));

            }
        }
        
        // ecriture du GPX
        monGPX.writePath("C:\\Users\\vincent\\Desktop\\today.gpx", "stravaminator", points);
        in.close();
        System.exit(0);
    }

}
