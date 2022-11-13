package com.example.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
    val productName: String? = null,
    val productPrice: Double? = 0.0
){
    constructor():this(0,"",0.0)

    override fun toString(): String {
        return "Product(id=$id, productName=$productName, productPrice=$productPrice)"
    }


}
