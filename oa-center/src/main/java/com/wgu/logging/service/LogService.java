package com.wgu.logging.service;

import com.wgu.entity.Logs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by w on 2019/4/25.
 */
public interface LogService {
    /**
     * 新增日志
     * @param joinPoint
     * @param log
     */
    @Async
    void save(ProceedingJoinPoint joinPoint, Logs log);
}
