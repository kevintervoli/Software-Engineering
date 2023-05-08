package com.patagonia.web.util;


import com.patagonia.web.entity.LogModel;
import com.patagonia.web.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogUtil {

    private final LogRepository logRepository;
    public LogUtil(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void error(String message, String type) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("ERROR");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logModel.setEventType(type);
        logRepository.save(logModel);
    }

    public void error(String message) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("ERROR");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logRepository.save(logModel);
    }

    public void info(String message, String type) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("INFO");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logModel.setEventType(type);
        logRepository.save(logModel);
    }

    public void info(String message) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("INFO");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logRepository.save(logModel);
    }

    public void debug(String message) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("DEBUG");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logRepository.save(logModel);
    }

    public void debug(String message, String type) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("DEBUG");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logModel.setEventType(type);
        logRepository.save(logModel);
    }

    public void warn(String message) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("WARN");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logRepository.save(logModel);
    }

    public void warn(String message, String type) {
        LogModel logModel = new LogModel();
        logModel.setMessage(message);
        logModel.setLogLevel("WARN");
        logModel.setCreatedAt(DateUtil.getCurrentTimeFilterFormat());
        logModel.setEventType(type);
        logRepository.save(logModel);
    }

}
