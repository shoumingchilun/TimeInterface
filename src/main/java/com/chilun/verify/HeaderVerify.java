package com.chilun.verify;

import com.chilun.apiopenspace.starter.PublicKeyPrivateKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author 齿轮
 * @date 2023-12-04-10:38
 */
@Aspect
@Component
@Slf4j
public class HeaderVerify {
    @Resource
    PublicKeyPrivateKeyUtils publicKeyPrivateKeyUtils;

    @Before("execution(* com.chilun.controller.TimeController.*(..))")
    public void verifyFrom() throws Exception {
        //获得参数：请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //打印全部headers属性
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " : " + request.getHeader(headerName));
        }
        System.out.println("==========================");
        System.out.println(request.getRequestURI());
        System.out.println("==========================");
        String originalData = request.getHeader("ChilunAPISpace-originalData");
        String encryptedData = request.getHeader("ChilunAPISpace-encryptedData");
        System.out.println(publicKeyPrivateKeyUtils.verify(encryptedData, originalData));
        System.out.println(publicKeyPrivateKeyUtils.decrypt(encryptedData));
    }
}
