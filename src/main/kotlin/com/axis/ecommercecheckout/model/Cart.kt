package com.axis.ecommercecheckout.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document(collection = "cart")
data class Cart (
    @Id
    var _id: String?=null,
    @DocumentReference
    var user: User?=null,
//    var product:List<Any?>?=null,
    @DocumentReference
    var product:Product?=null,
    var quantity:Int?=null,
    var totalPrice:Float?=null,
)