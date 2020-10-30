package com.crudapplication.crudapp.utility.logs;

import java.util.List;

import lombok.Data;

@Data
public class LogData<T> {
    private List<T> logData;

    public LogData() {
    }

    public LogData(List<T> logData) {
        this.logData = logData;
    }

   

}
