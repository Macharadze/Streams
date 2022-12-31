package pgdp.streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReading {



  private static List<PenguinData> instance =null;
  private static final DateTimeFormatter formatter = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss");

  public static Stream<PenguinData> processInputFile() {
    try {
      File file=new File("../data/OC_LPhillips_LittlePenguin_GPS_tracks_DATA.csv");
      instance=Files.lines(file.toPath()).skip(1)
              .map(mapToPenguinData)
              .filter(i->i!=null)
              .collect(Collectors.toList());
      return instance.stream();
    }catch (IOException e){
      System.out.println("Data Path seems to be wrong");
    }
    return null;
  }

  /*
  Implement within the class CSVReading the method public static Stream<PenguinData>
   processInputFile() which reads the data from the OC_LPhillips_LittlePenguin_GPS_tracks_DATA.csv file
    (please, find the file in your clone or in online editor and note that you need to skip the first line)
     and returns a Stream<PenguinData>. In case the IOException ocures there, you must print: "Data Path seems to be wrong".
   */

  private static Function<String, PenguinData> mapToPenguinData = (line) -> {
    String[] p = line.split(","); // a CSV has comma separated lines
    LocalDateTime dateTime = LocalDateTime.parse(p[2], formatter);
    return new PenguinData(p[0], Integer.parseInt(p[1]), dateTime, Double.parseDouble(p[3]),
        Double.parseDouble(p[4]), p[5], p[6], p[7], new Geo(p[8]));
  };
}
