package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.enums.ErrorEnum;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Log;
import github.akanemiku.akaneblog.repository.LogRepository;
import github.akanemiku.akaneblog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    @Transactional
    public void insertLog(Log log) {
        if(log==null)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        logRepository.save(log);
    }

    @Override
    public List<Log> getNewLog(Integer limit) {
        if(limit==null)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        return logRepository.findAllByLimit(limit);
    }
}
