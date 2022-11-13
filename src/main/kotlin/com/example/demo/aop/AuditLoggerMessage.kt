package com.example.demo.aop

import java.sql.Timestamp

data class AuditLoggerMessage(val correlationId:Long,val currentTime:Timestamp, val productName:String, val productPrice:Double, val action:String) {
    override fun toString(): String {
        return "$correlationId|$currentTime|$productName|$productPrice|$action"
    }
}