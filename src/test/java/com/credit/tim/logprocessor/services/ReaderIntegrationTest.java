package com.credit.tim.logprocessor.services;

import com.credit.tim.logprocessor.domain.LogStatus;
import com.credit.tim.logprocessor.helper.LogRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderIntegrationTest {

    @Autowired
    private Reader reader;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate.execute("Delete from log_status");
    }

    @Test
    public void shouldProcessFile() {

        //Given
        reader.setFile(new File("sampleFile.json"));

        //When
        reader.processStream();

        //Then
        LogStatus result = jdbcTemplate.queryForObject("select * from LOG_STATUS", new LogRowMapper());
        assertThat(result).isNotNull();
    }
}