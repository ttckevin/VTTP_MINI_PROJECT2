package springboot.backend.Models.Flight;

import java.util.List;

public class Segment {
    private List<Stop> stops;
    
    public List<Stop> getStops() {return stops;}
    public void setStops(List<Stop> stops) {this.stops = stops;}
}
