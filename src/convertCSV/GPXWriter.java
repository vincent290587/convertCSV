package convertCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.alternativevision.gpx.beans.GPX;

public class GPXWriter {

    private static final String TAG = GPX.class.getName();

    /**
     *
     * @param file
     * @param n
     * @param points
     */
    public void writePath(String file, String n, List<MonPoint> points) {

        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        header += "<gpx creator=\"Stravaminator\" version=\"1.1\" xmlns=\"http://www.topografix.com/GPX/1/1\" xmlns:xsi=\"http://www.w3.org/";
        header += "2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd http://www.garmin.com";
        header += "/xmlschemas/GpxExtensions/v3 http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd http://www.garmin.com/xmlschemas/TrackPointExtension/v1 http://";
        header += "www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd http://www.garmin.com/xmlschemas/GpxExtensions/v3 http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd";
        header += " http://www.garmin.com/xmlschemas/TrackPointExtension/v1 http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd http://www.garmin.com/xmlschemas/GpxExtensions/v3";
        header += " http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd http://www.garmin.com/xmlschemas/TrackPointExtension/v1";
        header += " http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd\" xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\"";
        header += " xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\">\n";

        String meta = "<metadata>\n";
        meta += "<name>" + n + "</name>\n";
        meta += "<time>" + points.get(0).getFormattedTime() + "</time>\n";
        meta += "</metadata>\n<trk>\n";
        
        String name = "<name>";
        name += n;
        name += "</name>\n";

        String segments = "<trkseg>\n";
        for (MonPoint l : points) {
            segments += l.toString();
        }

        String footer = "</trkseg>\n</trk>\n</gpx>\n";

        try {
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.append(header);
                writer.append(meta);
                writer.append(name);
                writer.append(segments);
                writer.append(footer);
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
