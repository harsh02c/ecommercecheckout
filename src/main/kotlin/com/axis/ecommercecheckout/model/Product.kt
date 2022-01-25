package com.axis.ecommercecheckout.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import javax.validation.constraints.NotBlank

@Document(collection = "product")
data class Product(
    @Id
    var _id:String?=null,
    @field:NotBlank(message = "Name field is mandatory")
    var name:String?=null,
    @field:NotBlank(message = "Description field is mandatory")
    var description:String?=null,
    @DocumentReference
    var category: Category?=null,
    @DocumentReference
    var subCategory: SubCategory?=null,
    @field:NotBlank(message = "Price field is mandatory")
    var price:Float?=null,
    var images:String?=null,
    @DocumentReference
    var seller: Seller?=null
)