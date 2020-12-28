package com.example.demo.service.dvdlibrary.repository;

import com.example.demo.service.dvdlibrary.model.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DvdRepository extends JpaRepository<Dvd, Integer> {
    List<Dvd> findAllByAvailable(boolean isAvailable);
}
