package com.example.demo.util

import java.util.UUID

class IdGenerator {

    fun generateId():Long{
        val currentTime = System.nanoTime()
        val sb = buildString { append(currentTime) }
        return sb.toLong()
    }
}