package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.Log;

import java.util.List;

public interface LogService {
    /**
     * 新增日志
     * @param log
     */
    void insertLog(Log log);

    /**
     * 获取最新limit条记录
     * @param limit
     * @return
     */
    List<Log> getNewLog(Integer limit);
}
