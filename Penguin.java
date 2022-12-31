package pgdp.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Penguin {
  private List<Geo> locations;
  private String trackID;

  public Penguin(List<Geo> locations, String trackID) {
    this.locations = locations;
    this.trackID = trackID;
  }

  @Override
  public String toString() {
    return "Penguin{" +
        "locations=" + locations +
        ", trackID='" + trackID + '\'' +
        '}';
  }

  public List<Geo> getLocations() {
    return locations;
  }

  public String getTrackID() {
    return trackID;
  }

  public String toStringUsingStreams() {
    // TODO
//   // Map<Double,Double> sorted=locations.stream().sorted(Comparator.comparing(Geo::getLatitude))
//     //       .collect(Collectors.toMap(e->e.))
//    //List<Geo> loc=locations.stream().map(i->i.latitude).sorted()
//  final List<Double> loc=new ArrayList<>();
//  Comparator<Geo> comp=Comparator.comparing(Geo::getLatitude);
//    Comparator<Geo> comp2=Comparator.comparing(Geo::getLatitude);
//    Map<Double,Double> map;

    return "Penguin{" +
            "locations=" +
            locations.stream().sorted(Comparator.comparing(Geo::getLatitude)
                            .thenComparing(Geo::getLongitude).reversed())
                    .toList()
            +", trackID='" + trackID + '\'' +
            '}';
  }
}
