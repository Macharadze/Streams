package pgdp.streams;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
  public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
    // TODO
      Map<String, List<Geo>> result ;
    result=stream.collect(Collectors.groupingBy(PenguinData::getTrackID,Collectors.mapping((PenguinData p)->p.geom,Collectors.toList())));
      List<Penguin> penguins=result.entrySet().stream().map(e1->new Penguin(e1.getValue() ,e1.getKey())).toList();
     return penguins.stream();
  }
  /*
  Implement within the class DataScience the method public static Stream<Penguin>
 getDataByTrackId(Stream<PenguinData> stream) which returns a stream of penguins.
  Thereby, all locations which agree in their TrackID should be combined into a single
   penguin. This penguin thus has a List<Geo> consisting of all these locations.
   */

  public static void outputPenguinStream() {
long counter= getDataByTrackId(Objects.requireNonNull(CSVReading.processInputFile())).map(Penguin::getTrackID).count();//Objects.requireNonNull(CSVReading.processInputFile()).map(PenguinData::getTrackID).count();
      System.out.println(counter);
Stream<PenguinData> dataStream=CSVReading.processInputFile();
      assert dataStream != null;
      getDataByTrackId(dataStream).forEach(e1-> System.out.println(e1.toStringUsingStreams()));
      System.out.println();
      System.out.println();
    // TODO
  }
  /*
  Implement a method public static void outputPenguinStream() which first outputs the number
   of penguins in the data set, and subsequenty each penguin in a separate line. Thereby,
   pengiuns are sorted according to their TrackID and should be output by means of the method
    toStringUsingStream(). If that method has not been implemented, the ordinary method toString
    can be used instead where, however, still the sorting should be according to the specification.
   */
  public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
    // TODO
      Stream<Penguin>data=getDataByTrackId(stream);
      data.forEach(e1->{
          String str=e1.getTrackID();
          Double minLong=e1.getLocations().stream().mapToDouble(Geo::getLongitude).min().getAsDouble();
          Double maxLong=e1.getLocations().stream().mapToDouble(Geo::getLongitude).max().getAsDouble();
          Double avgLong=e1.getLocations().stream().mapToDouble(Geo::getLongitude).average().getAsDouble();
          Double minlat=e1.getLocations().stream().mapToDouble(Geo::getLatitude).min().getAsDouble();
          Double maxlat=e1.getLocations().stream().mapToDouble(Geo::getLatitude).max().getAsDouble();
          Double avglat=e1.getLocations().stream().mapToDouble(Geo::getLatitude).average().getAsDouble();

          System.out.println(str+"\n"+"Min Longitude: "+minLong+" Max longitude: "
          +maxLong+" Avg Longitude: "+avgLong+" Min Latitude: "+minlat+" Max Latitude: "
          +maxlat+" Avg Latitude: "+avglat);
      });


  }
  /*
  Implement a method public static void outputLocationRangePerTrackid(Stream<PenguinData> stream)
  which prints the respective minimum, maximum and average of longitude and latitude per TrackId in
  the following format. Again, the output is expected to be sorted according to the TrackIds.
   */

  public static void main(String[] args) throws IOException {
    // TODO Test your implementation yourself
      // outputPenguinStream();
      Stream<PenguinData> dataStream=CSVReading.processInputFile();
      outputPenguinStream();

  }
}