package com.wgu.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * Created by w on 2019/3/18.
 */
@Configuration
public class ThreadPoolUtil {

    /**
     * 线程池
     * @return
     */
    @Bean
    public ExecutorService executorService(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        int size = 2;
        ExecutorService executorService = new ThreadPoolExecutor(size,size,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),namedThreadFactory);
        return executorService;
    }
}
