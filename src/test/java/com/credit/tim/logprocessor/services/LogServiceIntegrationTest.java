package com.credit.tim.logprocessor.services;

import com.credit.tim.logprocessor.domain.LogStatus;
import com.credit.tim.logprocessor.helper.LogRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.credit.tim.logprocessor"})
public class LogServiceIntegrationTest {

    @Autowired
    private LogService logService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate.execute("Delete from log_status");
    }

    @Test
    public void shouldSaveLog() {

        //Given
        LogStatus logStatus = new LogStatus();
        logStatus.setEventId("test1");
        logStatus.setAlert(true);
        logStatus.setDuration(4l);

        //When
        logService.save(logStatus);

        //Then
        LogStatus result = jdbcTemplate.queryForObject("select * from LOG_STATUS", new LogRowMapper());
        assertThat(result).isEqualTo(logStatus);
    }
}