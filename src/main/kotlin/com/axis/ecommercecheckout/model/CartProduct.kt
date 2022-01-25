package com.axis.ecommercecheckout.model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

data class CartProduct (
    @Id
    var _id: String?=null,
    @DocumentReference
    var productId: Product?=null,
    var qantity: String?=null,

    )