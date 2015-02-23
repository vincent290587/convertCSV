/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertCSV;

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
public class ConvertCSV {

    /**
     * @param fichier_
     * @throws java.io.IOException
     */
    public void importer(String fichier_) throws IOException {
        // TODO code application logic here

        float lat, lon, ele, secjour;
        int bpm;
        List<MonPoint> points = new ArrayList<>();
        Reader in = null;
        CSVParser parser;
        List<CSVRecord> list;
        GPXWriter monGPX = new GPXWriter();

        // lecture du CSV
        try {

            System.out.println("Lecture de " + fichier_);
            in = new FileReader(fichier_);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConvertCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        parser = new CSVParser(in, CSVFormat.EXCEL);
        list = parser.getRecords();
        list.remove(0);

        // remplissage de la liste de point GPX
        if (in != null) {
            for (CSVRecord elem : list) {

                try {

                    // on recupere les donnees dans le CSV
                    lat = Float.parseFloat(elem.get(0));
                    lon = Float.parseFloat(elem.get(1));
                    ele = Float.parseFloat(elem.get(2));
                    secjour = Float.parseFloat(elem.get(3));
                    if (elem.size() > 4) {
                        bpm = Integer.parseInt(elem.get(4));
                        points.add(new MonPoint(lat, lon, ele, secjour, bpm));
                    } else {
                        points.add(new MonPoint(lat, lon, ele, secjour));
                    }

                } catch (NumberFormatException ex) {
                    System.out.println(elem.toString());
                }

            }

            // ecriture du GPX
            monGPX.writePath("C:\\Users\\vincent\\Desktop\\today.gpx", "Training", points);
            in.close();

        }

        System.exit(0);
    }

}
