package springboot.backend.Models.Flight;

public class Stop {
    private Departure departure;
    private Arrival arrival;
    private String carrierCode;
    private String carrierNum;
    private String duration;
    
    public Departure getDeparture() {return departure;}
    public void setDeparture(Departure departure) {this.departure = departure;}
    public Arrival getArrival() {return arrival;}
    public void setArrival(Arrival arrival) {this.arrival = arrival;}
    public String getCarrierCode() {return carrierCode;}
    public void setCarrierCode(String carrierCode) {this.carrierCode = carrierCode;}
    public String getCarrierNum() {return carrierNum;}
    public void setCarrierNum(String carrierNum) {this.carrierNum = carrierNum;}
    public String getDuration() {return duration;}
    public void setDuration(String duration) {this.duration = duration;}
    
}
