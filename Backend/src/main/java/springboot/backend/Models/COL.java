package springboot.backend.Models;

import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="col")
public class COL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ranking;
    private String city;
    private double col_idx;
    private double rent_idx;
    private double col_rent_idx;
    private double groceries_idx;
    private double restaurant_idx;
    private double localPP_idx;

    public int getRanking() {return ranking;}
    public void setRanking(int ranking) {this.ranking = ranking;}
    
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    
    public double getCol_idx() {return col_idx;}
    public void setCol_idx(double col_idx) {this.col_idx = col_idx;}
    
    public double getRent_idx() {return rent_idx;}
    public void setRent_idx(double rent_idx) {this.rent_idx = rent_idx;}
    
    public double getCol_rent_idx() {return col_rent_idx;}
    public void setCol_rent_idx(double col_rent_idx) {this.col_rent_idx = col_rent_idx;}
    
    public double getGroceries_idx() {return groceries_idx;}
    public void setGroceries_idx(double groceries_idx) {this.groceries_idx = groceries_idx;}
    
    public double getRestaurant_idx() {return restaurant_idx;}
    public void setRestaurant_idx(double restaurant_idx) {this.restaurant_idx = restaurant_idx;}

    public double getLocalPP_idx() {return localPP_idx;}
    public void setLocalPP_idx(double localPP_idx) {this.localPP_idx = localPP_idx;}
    
    public JsonObject toJson(List<String> images){
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for(int i = 0; i < images.size(); i++){
            arr.add(images.get(i));
        }
        return Json.createObjectBuilder()
            .add("Ranking", defaultValue(ranking, 0))
            .add("City", defaultValue(city, "NA"))
            .add("Col_idx", defaultValue(col_idx, 0.0))
            .add("Rent_idx", defaultValue(rent_idx, 0.0))
            .add("Col_Rent_idx", defaultValue(col_rent_idx, 0.0))
            .add("Groceries_idx", defaultValue(groceries_idx,0.0))
            .add("RestaurantPrice_idx", defaultValue(restaurant_idx, 0.0))
            .add("LocalPP_idx", defaultValue(localPP_idx,0.0))
            .add("Images", defaultValue(arr.build().toString(), "NA"))
            .build();
    }

    private <T> T defaultValue(T value, T defValue) {
		if (null != value)
			return value;
		return  defValue;
	}
    
}
