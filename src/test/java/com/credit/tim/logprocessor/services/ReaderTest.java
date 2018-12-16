package com.credit.tim.logprocessor.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReaderTest {

    @Mock
    private LogService logService;

    @InjectMocks
    private ReaderImpl readerImpl = new ReaderImpl(new File("sampleFile.json"));

    @Test
    public void shouldSaveLines() {
        //When
        readerImpl.processStream();

        //Then
        verify(logService).save(any(List.class));
    }

    @Test
    public void shouldAlertIfGreaterThan4MilliSeconds() {

        //When
        readerImpl.processStream();

        //Then
        verify(logService).save(any(List.class));
    }
}