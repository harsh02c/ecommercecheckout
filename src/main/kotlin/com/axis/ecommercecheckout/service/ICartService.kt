package com.axis.ecommercecheckout.service

import com.axis.ecommercecheckout.model.Cart
import org.bson.types.ObjectId
import java.util.*

interface ICartService {
    fun addToCart(cart: Cart):Any?
    fun updateCart(Id:String,cart: Cart):Any?
    fun getCart(cart: ObjectId):MutableList<Cart?>
//    fun getCart(cart: ObjectId):Cart?
    fun getCartProduct(user: ObjectId,product:ObjectId): Cart?
    fun deleteCart(id: String):String
    fun emptyCart(id: ObjectId):String

}