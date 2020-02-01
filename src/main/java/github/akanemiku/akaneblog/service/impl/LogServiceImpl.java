package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.model.Log;
import github.akanemiku.akaneblog.repository.LogRepository;
import github.akanemiku.akaneblog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public void insertLog(Log log) {
        logRepository.save(log);
    }

    @Override
    public List<Log> getNewLog(Integer limit) {
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        return logRepository.findAllByLimit(limit);
    }
}
