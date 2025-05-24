package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.FilterVendorDTO;
import com.example.dbms_group2.repository.AssignmentPointRepository;
import com.example.dbms_group2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class OtherService {


    @Autowired
    private AssignmentPointRepository assignmentPointRepository;

    public OtherService() {
    }

    public boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
    }

//    public List<FilterVendorDTO> sameMethodName(String email, int marketId, List<String> selectedCategories, int length){
//
//
//        List<FilterVendorDTO2> outside = assignmentPointRepository.findOutside(email, marketId, selectedCategories, length);
//        List<FilterVendorDTO> result = new ArrayList<>();
//
//
//        for(int i = 0 ; i < outside.size() ; i++){
//            FilterVendorDTO x = new FilterVendorDTO(outside.get(i).getCode,
//                    outdise.getVendorId,
//                    outside.getName(),
//                    assignmentPointRepository.findInside(email, marketId, selectedCategories, length, outside.get(i).getCode()))
//            result.add(x);
//        }
//
//
//
//    }

}
