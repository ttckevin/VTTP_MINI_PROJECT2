package springboot.backend.Models.Flight;

import java.util.List;

public class Itinerary {
    private String total_duration;
    private List<Segment> segment;
    
    public String getTotal_duration() {return total_duration;}
    public void setTotal_duration(String total_duration) {this.total_duration = total_duration;}
    public List<Segment> getSegment() {return segment;}
    public void setSegment(List<Segment> segment) {this.segment = segment;}

}
