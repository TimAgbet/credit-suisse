package com.credit.tim.logprocessor;

import com.credit.tim.logprocessor.services.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import java.io.File;

@SpringBootApplication
@Import(value = AppConfig.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.credit.tim.logprocessor.services"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
        LogProcessorApplication.class})})
public class LogProcessorApplication implements CommandLineRunner {

    private Logger LOGGER = LoggerFactory.getLogger(LogProcessorApplication.class);

    @Autowired
    private Reader fileReader;

    public static void main(String[] args) {
        SpringApplication.run(LogProcessorApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (strings.length > 0) {
            LOGGER.debug("Length of parameters is: " + strings[0]);
            LOGGER.debug("About to process file run!!!");
            File file = new File(strings[0]);
            fileReader.setFile(file);
            LOGGER.debug("Total size of file" + file.getTotalSpace());
            fileReader.processStream();
        }
    }
}
