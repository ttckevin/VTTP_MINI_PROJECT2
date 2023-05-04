package springboot.backend.Repositories;

import static springboot.backend.Repositories.Queries.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import springboot.backend.Models.COL;


@Repository
public class COLRepository {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    public COL getCOL(String city){
        SqlRowSet srs = jdbc.queryForRowSet(SQL_GET_COL, city);
        COL searchedCity = new COL();
        if(srs.next()){
            searchedCity.setCity(city);
            searchedCity.setRanking(srs.getInt("ranking"));
            searchedCity.setCol_idx(srs.getDouble("col_idx"));
            searchedCity.setRent_idx(srs.getDouble("rent_idx"));
            searchedCity.setCol_rent_idx(srs.getDouble("col_rent_idx"));
            searchedCity.setGroceries_idx(srs.getDouble("groceries_idx"));
            searchedCity.setRestaurant_idx(srs.getDouble("restaurant_idx"));
            searchedCity.setLocalPP_idx(srs.getDouble("localpp_idx"));
        }
        return searchedCity;
    }

}
