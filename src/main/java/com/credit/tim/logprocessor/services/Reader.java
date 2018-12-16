package com.credit.tim.logprocessor.services;

import java.io.File;

public interface Reader {
    void processStream();
    void setFile(File file);
}
