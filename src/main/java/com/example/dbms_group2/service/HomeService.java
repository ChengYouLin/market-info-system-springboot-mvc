package com.example.dbms_group2.service;

import com.example.dbms_group2.model.DTO.MarketDTO;
import com.example.dbms_group2.model.entity.Organizer;
import com.example.dbms_group2.model.entity.User;
import com.example.dbms_group2.model.entity.Vendor;
import com.example.dbms_group2.repository.HomeQueryRepository;
import com.example.dbms_group2.repository.OrganizerRepository;
import com.example.dbms_group2.repository.UserRepository;
import com.example.dbms_group2.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeQueryRepository homeQueryRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MarketDTO> activeMarkets() {
        return null;
        //return homeQueryRepository.findActivityMarket();
    }

    public List<MarketDTO> recruitingMarkets() {
        return null;
        //return homeQueryRepository.findRecruitingMarkets();
    }

    public boolean validateUser(String email, String password, String role) {

        OtherService otherService = new OtherService();

        if (otherService.isValidPassword(password) != true) {
            return false;
        }
        switch (role) {
            case "organizer":
                List<Organizer> organizers = organizerRepository.findByGmail(email);
                if (organizers.isEmpty()) return false;

                Organizer o = organizers.get(0);
                return encoder.matches(password, o.getPassword());

            case "vendor":
                List<Vendor> vendors = vendorRepository.findByGmail(email);
                if (vendors.isEmpty()) return false;

                Vendor v = vendors.get(0);
                return encoder.matches(password, v.getPassword());

            case "user":
                List<User> users = userRepository.findByGmail(email);
                if (users.isEmpty()) return false;

                User u = users.get(0);
                return encoder.matches(password, u.getPassword());
        }


        return false;
    }
}
