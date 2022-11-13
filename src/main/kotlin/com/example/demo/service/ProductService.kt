package com.example.demo.service

import com.example.demo.model.Product
import com.example.demo.repository.ProductRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val productRepository: ProductRepository) {
    companion object{
        @JvmStatic
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun saveProduct(product: Product):Product{
        logger.info("Product Saved")
        return productRepository.save(product)
    }

    fun getAllProducts():MutableList<Product>{
        logger.info("get all Product request call")
        return productRepository.findAll()
    }

    fun getProductById(id: Int): Optional<Product> {
        logger.info("get product by id: $id")
        return productRepository.findById(id)
    }
}