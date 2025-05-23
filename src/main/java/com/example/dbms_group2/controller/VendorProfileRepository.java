package com.example.dbms_group2.controller;

import com.example.dbms_group2.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eView/vendor")
public class VendorProfileRepository {

    @Autowired
    private VendorService vendorService;

}
