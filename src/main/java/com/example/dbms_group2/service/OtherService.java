package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.ZoneDTO;
import com.example.dbms_group2.model.DTO.ZoneVendorDTO;
import com.example.dbms_group2.model.DTO.ZonezoneDTO;
import com.example.dbms_group2.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OtherService {
    private ZoneRepository zoneRepository;

    public OtherService() {
    }

    public boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
    }

    public List<FilterVendorDTO> findFilterVendor(String email, int marketId, List<String> selectedCategories, int length){


        List<FilterVendorDTO2> outside = assignmentPointRepository.findOutside(email, marketId, selectedCategories, length);
        List<FilterVendorDTO> result = new ArrayList<>();


        for(int i = 0 ; i < outside.size() ; i++){
            FilterVendorDTO x = new FilterVendorDTO(outside.get(i).getCode,
                    outdise.getVendorId,
                    outside.getName(),
                    assignmentPointRepository.findInside(email, marketId, selectedCategories, length, outside.get(i).getCode()))
            result.add(x);
        }



    }

}
