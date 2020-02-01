package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.Log;

import java.util.List;

public interface LogService {
    void insertLog(Log log);

    List<Log> getNewLog(Integer limit);
}
