package com.example.demo.repository

import com.example.demo.model.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int> {
    companion object{
        @JvmStatic
        val logger:Logger = LoggerFactory.getLogger(this::class.java)
    }
}