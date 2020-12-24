package com.example.demo.service.dvdlibrary.controller;

import com.example.demo.service.dvdlibrary.model.Dvd;
import com.example.demo.service.dvdlibrary.repository.DvdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DvdController {
    private DvdRepository repository;

    @Autowired
    public DvdController(DvdRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path="/dvds", consumes="application/json")
    public void postDvd(@RequestBody Dvd dvd) {
        System.out.println("Adding to DvdDB: " + repository.save(dvd));
    }

    @GetMapping(path="/dvds/{id}")
    public Dvd getDvd(@PathVariable int id) {
        Optional<Dvd> dvdOptional = repository.findById(id);

        if (dvdOptional.isPresent()) {
            System.out.println("Getting from DvdDB: " + dvdOptional.get());
            return dvdOptional.get();
        } else {
            System.out.println("Get request failed (no dvd with this \"id\"): " + id);
            return null;
        }
    }
}
