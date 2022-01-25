package com.axis.ecommercecheckout.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "seller")
data class Seller (
    @Id
    var _id: String?=null,
    var name:String?=null,
    var mobileNo:String?=null,
    var email:String?=null,
    var password:String?=null

)