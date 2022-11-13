package com.example.demo.aop

data class LoggerMessage(
    val className: String,
    val methodName: String,
    val methodArgs: String,
    val elapsedTimeInMillis: Long,
    val elapsedTimeInMicros: Long,
    val result: Any
) {
    override fun toString(): String {
        return "LoggerMessage(className='$className', methodName='$methodName', methodArgs='$methodArgs', elapsedTimeInMillis=$elapsedTimeInMillis, elapsedTimeInMicros=$elapsedTimeInMicros, result=$result)"
    }
}
