package com.example.demo.aop

import org.aspectj.lang.annotation.Pointcut
import org.springframework.context.annotation.Configuration

@Configuration
class AppPointCuts {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    fun controllerPointCut(){}

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    fun servicePointCut(){}

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    fun repositoryPointCut(){}

    @Pointcut("execution(* com.example.demo..*(..))")
    fun appPointCut(){}

    @Pointcut("appPointCut() && (controllerPointCut() || servicePointCut() || repositoryPointCut())")
    fun mainPointCut(){}
}