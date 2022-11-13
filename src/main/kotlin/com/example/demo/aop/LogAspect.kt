package com.example.demo.aop

import com.example.demo.model.Product
import com.example.demo.util.IdGenerator
import com.google.gson.Gson
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import java.sql.Timestamp
import java.util.concurrent.TimeUnit

@Order(0)
@Aspect
@Configuration
class LogAspect {
    companion object {
        @JvmStatic
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Around(value = "com.example.demo.aop.AppPointCuts.mainPointCut()")
    fun calculateMethodTimeAdvice(jointPoint: ProceedingJoinPoint): Any {
        try {
            val classLogger: Logger = LoggerFactory.getLogger(jointPoint.target.javaClass)
            if (!classLogger.isDebugEnabled) {
                return jointPoint.proceed()
            }
            val className: String = jointPoint.target.javaClass.name
            val methodName: String = (jointPoint.signature as MethodSignature).method.name
            val methodArgs: String = jointPoint.args.toString()
            val startTime: Long = System.nanoTime()
            val result: Any = jointPoint.proceed()
            val elapsedTime = System.nanoTime() - startTime

            val message: LoggerMessage = LoggerMessage(
                className,
                methodName,
                methodArgs,
                TimeUnit.NANOSECONDS.toMillis(elapsedTime),
                TimeUnit.NANOSECONDS.toMicros(elapsedTime),
                result
            )
            classLogger.debug("{}", message)
            return result
        } catch (e: Exception) {
            logger.error("An Error Occurred ", e)
            throw e
        }
    }

    @Around(value = "com.example.demo.aop.AppPointCuts.controllerPointCut() && args(parameters)")
    fun auditLogging(jointPoint: ProceedingJoinPoint, parameters: Product): Any {
        try {
            val gson = Gson()
            val auditLogger: Logger = LoggerFactory.getLogger("AUDIT_LOG")
            val correlationId: Long = IdGenerator().generateId()
            val currentDateTime: Timestamp = Timestamp(System.currentTimeMillis())
            val productName: String = parameters.productName!!
            val productPrice: Double = parameters.productPrice!!
            val action: String = (jointPoint.signature as MethodSignature).method.name
            val result = jointPoint.proceed()
            val check = gson.fromJson(gson.toJson(result), AopResponse::class.java)
            if (check.status == "OK") {
                val message: AuditLoggerMessage =
                    AuditLoggerMessage(correlationId, currentDateTime, productName, productPrice, action)
                auditLogger.info("{}", message)
            }
            return result
        } catch (e: Exception) {
            logger.error("An Error Occurred ", e)
            throw e
        }
    }
}