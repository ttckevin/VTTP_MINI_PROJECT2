package springboot.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.backend.Models.COL;
import springboot.backend.Repositories.COLRepository;

@Service
public class COLService {
    
    @Autowired
    private COLRepository colRepo;

    public COL getCOL (String city){
        return colRepo.getCOL(city);
    }

}
