package com.wgu.logging.rest;

import com.wgu.entity.Logs;
import com.wgu.global.Result;
import com.wgu.logging.service.query.LogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author w
 * @date 2018-11-24
 */
@RestController
@RequestMapping("api/logs")
public class LogController {

    private final LogQueryService logQueryService;

    @Autowired
    public LogController(LogQueryService logQueryService) {
        this.logQueryService = logQueryService;
    }

    @GetMapping(value = "/info")
    public Result getLogs(Logs log, Pageable pageable){
        log.setLogType("INFO");
        return Result.ok(logQueryService.queryAll(log,pageable));
    }

    @GetMapping(value = "/error")
    public Result getErrorLogs(Logs log, Pageable pageable){
        log.setLogType("ERROR");
        return Result.ok(logQueryService.queryAll(log,pageable));
    }
}
