package github.akanemiku.akaneblog.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class HttpAspect {
    private Long startTime;

    @Before("execution(* github.akanemiku.akaneblog.controller..*(..))")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        startTime = System.currentTimeMillis();
        log.info("-- START --");
        log.info("HttpMethod={}", request.getMethod());
        log.info("URL={}", request.getRequestURI());
        log.info("Method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()");
        log.info("Args={}", joinPoint.getArgs());
        log.info("User={}", request.getRemoteUser());

    }

    @After("execution(* github.akanemiku.akaneblog.controller..*(..))")
    public void doAfter() {
        Long endTime = System.currentTimeMillis();
        log.info("-- TIME USED : " + (endTime - startTime) + " --");
    }
}
