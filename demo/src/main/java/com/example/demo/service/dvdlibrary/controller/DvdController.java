package com.example.demo.service.dvdlibrary.controller;

import com.example.demo.service.dvdlibrary.model.Dvd;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DvdController {
    private List<Dvd> dvds = new ArrayList<>();

    public DvdController() {}

    @PostMapping(path="/dvd")
    public void addDvd(@RequestBody Dvd dvd) {
        dvds.add(dvd);
    }

    @GetMapping("/dvds")
    public List<Dvd> displayDvds() {
        return dvds;
    }
}
