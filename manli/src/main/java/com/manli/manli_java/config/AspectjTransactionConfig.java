package com.manli.manli_java.config;

import com.manli.manli_java.advice.AnnotationAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
public class AspectjTransactionConfig {
//    public static final String transactionExecution = "execution (* com.manli.manli_java.service.*.*(..))";
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//
//    @Bean
//    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
//
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(transactionExecution);
//        advisor.setPointcut(pointcut);
//
//        Properties attributes = new Properties();
//        attributes.setProperty("get*", "PROPAGATION_REQUIRED,-EXCEPTION");
//        attributes.setProperty("add*", "PROPAGATION_REQUIRED,-EXCEPTION");
//        attributes.setProperty("save*", "PROPAGATION_REQUIRED,-EXCEPTION");
//        attributes.setProperty("update*", "PROPAGATION_REQUIRED,-EXCEPTION");
//        attributes.setProperty("delete*", "PROPAGATION_REQUIRED,-EXCEPTION");
//
//        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, attributes);
//        advisor.setAdvice(txAdvice);
//
//        return advisor;
//    }
}
