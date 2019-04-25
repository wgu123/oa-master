package com.wgu.logging.service.impl;

import cn.hutool.json.JSONObject;
import com.wgu.annotations.Log;
import com.wgu.entity.Logs;
import com.wgu.logging.repository.LogRepository;
import com.wgu.logging.service.LogService;
import com.wgu.modules.user.dto.UserDTO;
import com.wgu.modules.user.service.AuthService;
import com.wgu.modules.user.service.UserService;
import com.wgu.utils.MyStringUtils;
import com.wgu.utils.RequestHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by w on 2019/4/25.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final AuthService authService;

    private final String LOGINPATH = "login";

    @Autowired
    public LogServiceImpl(LogRepository logRepository, AuthService authService) {
        this.logRepository = logRepository;
        this.authService = authService;
    }

    @Override
    public void save(ProceedingJoinPoint joinPoint, Logs log){

        // 获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log aopLog = method.getAnnotation(Log.class);

        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";

        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 用户名
        String username = "";

        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }

        // 获取IP地址
        log.setRequestIp(MyStringUtils.getIP(request));

        if(!LOGINPATH.equals(signature.getName())){
            UserDTO userDTO = authService.getUserInfo();
            username = userDTO.getUsername();
        } else {
            try {
                JSONObject jsonObject = new JSONObject(argValues[0]);
                username = jsonObject.get("username").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params + " }");
        logRepository.save(log);
    }
}
