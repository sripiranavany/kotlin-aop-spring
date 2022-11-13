package com.example.demo.controller

import com.example.demo.model.Product
import com.example.demo.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {
    companion object{
        @JvmStatic
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @PostMapping
    fun saveProduct(@RequestBody product: Product):ResponseEntity<Any>{
        logger.info("product Saved")
        productService.saveProduct(product = product)
        return ResponseEntity.ok("product Saved")
    }

    @GetMapping
    fun getAllProducts():ResponseEntity<MutableList<Product>>{
        logger.info("get All products")
        return ResponseEntity.ok(productService.getAllProducts())
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id:Int):ResponseEntity<Optional<Product>>{
        logger.info("get product by $id")
        return ResponseEntity.ok(productService.getProductById(id))
    }
}