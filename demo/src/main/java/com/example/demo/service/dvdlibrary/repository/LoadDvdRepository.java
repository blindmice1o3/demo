package com.example.demo.service.dvdlibrary.repository;

import com.example.demo.service.dvdlibrary.model.Dvd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class LoadDvdRepository {

    private static final String PATH_DATA_SOURCE = "C:\\Users\\James\\IdeaProjects\\spring-boot\\demo\\src\\main\\resources\\dvd_library_collection_unsorted_csv.txt";
    private static final Logger log = LoggerFactory.getLogger(LoadDvdRepository.class);

    @Bean
    CommandLineRunner initDatabase(DvdRepository repository) {
        return args -> {
//            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
//            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));

            String[] dvdsAsString = loadAndTrimCSV(PATH_DATA_SOURCE);
            Dvd[] dvds = instantiateDvds(dvdsAsString);

            for (int index = 0; index < dvds.length; index++) {
                log.info(
                        String.format("Preloading %3d: " + repository.save(dvds[index]), index+1)
                );
            }
        };
    }

    private String[] loadAndTrimCSV(String filePath) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            File dvdsUnsortedCSV = new File(filePath);
            bufferedReader = new BufferedReader(new FileReader(dvdsUnsortedCSV));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] dvds = stringBuilder.toString().split(",");

        for (int i = 0; i < dvds.length; i++) {
            dvds[i] = dvds[i].trim();
        }

        return dvds;
    }

    private Dvd[] instantiateDvds(String[] dvdsAsString) {
        Dvd[] dvds = new Dvd[dvdsAsString.length];
        boolean availableDefault = true;

        for (int i = 0; i < dvdsAsString.length; i++) {
            dvds[i] = new Dvd(dvdsAsString[i], availableDefault);
        }

        return dvds;
    }

}
