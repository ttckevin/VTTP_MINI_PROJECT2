package springboot.backend.Services;

import org.springframework.stereotype.Service;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;

@Service
public class FlightService {
    
    public String test(String origin, String destination, String dDay, int noOfCarrier, int noOfPax) throws ResponseException{
            
        Amadeus amadeus = Amadeus
            .builder("P82TzP7ZKZjac62boQORbmGjVKf3zfDW", "p5trq1vINM5QHmiM")
            .build();

        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
            Params.with("originLocationCode", origin)
                    .and("destinationLocationCode", destination)
                    .and("departureDate", dDay)
                    .and("adults", noOfPax)
                    .and("max", noOfCarrier));
                    
        return flightOffersSearches[0].getResponse().getResult().toString();


    }

}
