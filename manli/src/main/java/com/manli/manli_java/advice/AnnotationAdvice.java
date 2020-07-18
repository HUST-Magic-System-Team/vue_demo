package com.manli.manli_java.advice;

//注解增强

import org.aopalliance.aop.Advice;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationAdvice implements Advice {

    // 定义前置增强
    @Before("execution(* com.manli.manli_java.service.*(..))")
    public void before(JoinPoint jp) {
        System.out.println("调用" + jp.getTarget() + "的" + jp.getSignature().getName() + "方法，参数:" + jp.getArgs() + ",参数个数:" + jp.getArgs().length);
        System.out.println("before");
    }

    // 定义后置增强
    @AfterReturning(pointcut = "execution(* com.manli.manli_java.service.*(..))", returning = "returnValue")
    public void afterReturning(JoinPoint jp, Object returnValue) {
        System.out.println("调用" + jp.getTarget() + "的" + jp.getSignature().getName() + "方法，参数:" + jp.getArgs() + ",返回值为:" + returnValue);
        System.out.println("after");
    }
}