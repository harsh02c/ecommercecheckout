package com.axis.ecommercecheckout.dao

import com.axis.ecommercecheckout.model.Cart
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface ICartDAO: MongoRepository<Cart, String> {
    @Query("{ 'user':  ?0 }")
//    fun getCart(id: ObjectId): Cart?
    fun getCart(id: ObjectId): MutableList<Cart?>

    //Below query is to search element within array
//    @Query("{ 'user':  ?0 ,product: { \$elemMatch: { productId: ?1 } } }")
//    fun getCartProduct(user: ObjectId,product: ObjectId): Cart?

    @Query("{ 'user':  ?0 ,product: ?1 }")
    fun getCartProduct(user: ObjectId,product: ObjectId): Cart?

    @Query(value = "{'user' : ?0}", delete = true)
    fun deleteByUserId(id: ObjectId?): Cart?
}