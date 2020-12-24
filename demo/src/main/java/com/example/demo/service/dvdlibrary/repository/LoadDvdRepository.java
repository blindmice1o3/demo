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

            addDvdsToDatabase(repository);
        };
    }

    private void addDvdsToDatabase(DvdRepository repository) {
        Dvd[] dvds = generateDvdsFromCVSFile(PATH_DATA_SOURCE);

        for (int index = 0; index < dvds.length; index++) {
            log.info(
                    String.format("Preloading %3d: " + repository.save(dvds[index]), index+1)
            );
        }
    }

    private Dvd[] generateDvdsFromCVSFile(String filePath) {
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

        String[] dvdsAsString = stringBuilder.toString().split(",");
        boolean availableDefault = true;

        Dvd[] dvds = new Dvd[dvdsAsString.length];
        for (int i = 0; i < dvds.length; i++) {
            dvdsAsString[i] = dvdsAsString[i].trim();

            dvds[i] = new Dvd(dvdsAsString[i], availableDefault);
        }

        return dvds;
    }

}
