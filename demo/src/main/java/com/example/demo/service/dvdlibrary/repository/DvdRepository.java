package com.example.demo.service.dvdlibrary.repository;

import com.example.demo.service.dvdlibrary.model.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DvdRepository extends JpaRepository<Dvd, Integer> {

}
