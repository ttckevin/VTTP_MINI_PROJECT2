package springboot.backend.Models.Flight;

public class Departure {
    private String iataCode;
    private String terminal;
    private String time;

    public String getIataCode() {return iataCode;}
    public void setIataCode(String iataCode) {this.iataCode = iataCode;}
    public String getTerminal() {return terminal;}
    public void setTerminal(String terminal) {this.terminal = terminal;}
    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}
    
}
