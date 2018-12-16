package com.credit.tim.logprocessor.services;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskHelperTest {

    @Test
    public void shouldGetDuration() {
        //Given
        long startTime = 1491377495210l;
        long endTime = 1491377495218l;

        //When
        Long duration = TaskHelper.calculateDuration(startTime, endTime);

        //Then
        assertThat(duration).isEqualTo(8);
    }

}