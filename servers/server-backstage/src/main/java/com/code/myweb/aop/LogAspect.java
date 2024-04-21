package com.code.myweb.aop;

import com.code.myweb.util.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    /**
     * 处理请求前执行
     */
    @Before(value = "pointCutName() && @annotation(logInfo)")
    public void logBefore(JoinPoint joinPoint, LogInfo logInfo) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Method methodTarget = methodSignature.getMethod();
        LogInfo logInfo2 = methodTarget.getAnnotation(LogInfo.class);
        logger.info("logBefore method:{}, params: {}", methodTarget.getName(), Arrays.toString(joinPoint.getArgs()));
        logger.info("logBefore ip:{}", IPUtil.getIpAddr());
    }

    @Pointcut(value = "@annotation(com.code.myweb.aop.LogInfo)")
    public void pointCutName() {
        // do nothing
        logger.info("LogAspect pointCutName =========>");
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "pointCutName() && @annotation(logInfo)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, LogInfo logInfo, Object jsonResult) {
        handleLog(joinPoint, logInfo, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e
     */
    @AfterThrowing(value = "pointCutName() && @annotation(logInfo)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, LogInfo logInfo, Exception e) {
        handleLog(joinPoint, logInfo, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, LogInfo logInfo, final Exception e, Object jsonResult) {
        try {
            if (e != null) {
                // 错误日志
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();

            // 插入日志
            Long costTi = System.currentTimeMillis() - TIME_THREADLOCAL.get();
            logger.info("costTi:{}", costTi);
        } catch (Exception ex) {
            logger.error("异常信息:{}", ex);
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

}
