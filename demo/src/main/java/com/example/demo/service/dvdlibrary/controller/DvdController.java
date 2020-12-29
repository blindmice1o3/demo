package com.example.demo.service.dvdlibrary.controller;

import com.example.demo.service.dvdlibrary.model.Dvd;
import com.example.demo.service.dvdlibrary.repository.DvdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DvdController {
    private DvdRepository repository;

    @Autowired
    public DvdController(DvdRepository repository) {
        this.repository = repository;
    }

    // Search/query (single query parameter)

    @GetMapping(path="/foo", params = "available")
    public Dvd[] getAllByAvailable(@RequestParam boolean available) {
        System.out.println("Getting Dvd instances from DvdDB, by available=" + available);
        List<Dvd> dvds = repository.findAllByAvailable(available);
        return dvds.toArray(new Dvd[0]);
    }

    @GetMapping(path="/foo", params = "searchText")
    public Dvd[] getAllByTitleContainingIgnoreCase(@RequestParam String searchText) {
        List<Dvd> dvds = repository.findByTitleContainingIgnoreCase(searchText);
        return dvds.toArray(new Dvd[0]);
    }

    // Aggregate root

    @GetMapping(path="/dvds")
    public Dvd[] getAll() {
        System.out.println("Getting all Dvd instances from DvdDB");
        List<Dvd> dvds = repository.findAll();
        return dvds.toArray(new Dvd[0]);
    }

    @PostMapping(path="/dvds", consumes="application/json")
    public Dvd postDvd(@RequestBody Dvd newDvd) {
        System.out.println(String.format("Posting %s to DvdDB", newDvd));

        return repository.save(newDvd);
    }

    // Single item

    @GetMapping(path="/dvds/{id}")
    public Dvd getDvd(@PathVariable int id) {
        Optional<Dvd> dvdOptional = repository.findById(id);

        if (dvdOptional.isPresent()) {
            System.out.println(String.format("Getting Dvd with \"id\":%d from DvdDB", id));
            return dvdOptional.get();
        } else {
            System.out.println(String.format("Get request else-clause (no dvd with \"id\":%d)", id));
            // TODO: Create an ExceptionHandler class annotated with @RestControllerAdvice
            return null;
        }
    }

    @PutMapping(path="/dvds/{id}")
    public Dvd replaceDvd(@RequestBody Dvd newDvd, @PathVariable int id) {
        Optional<Dvd> dvdOptional = repository.findById(id);

        if (dvdOptional.isPresent()) {
            Dvd dvd = dvdOptional.get();

            System.out.println(String.format("Replacing %s from DvdDB with %s", dvd, newDvd));

            dvd.setTitle(newDvd.getTitle());
            dvd.setAvailable(newDvd.isAvailable());

            return repository.save(dvd);
        } else {
            newDvd.setId(id);

            System.out.println(String.format("Put request else-clause (no dvd with \"id\":%d) (not replacing, just simply posting %s to DvdDB using the next available id)", id, newDvd));
            return repository.save(newDvd);
        }
    }

    @DeleteMapping(path="/dvds/{id}")
    public void deleteDvd(@PathVariable int id) {
        System.out.println("Deleting Dvd with \"id\":" + id + " from DvdDB");
        repository.deleteById(id);
    }
}
