package com.axis.ecommercecheckout.dao

import com.axis.ecommercecheckout.model.Seller
import org.springframework.data.mongodb.repository.MongoRepository

interface ISellerDAO: MongoRepository<Seller, String> {

}