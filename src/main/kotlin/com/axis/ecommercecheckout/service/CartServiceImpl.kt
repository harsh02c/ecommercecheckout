package com.axis.ecommercecheckout.service

import com.axis.ecommercecheckout.dao.ICartDAO
import com.axis.ecommercecheckout.model.Cart
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartServiceImpl:ICartService {
    @Autowired
    private lateinit var iCheckoutService: ICheckoutService
    @Autowired
    private lateinit var iCartDAO: ICartDAO
    override fun addToCart(cart: Cart): Any? {
        return iCartDAO.save(cart)
    }

    override fun updateCart(Id: String, cart: Cart): Any? {
        return if(iCartDAO.existsById(Id)){
            val data =  iCartDAO.findById(Id).get()

            val updatedCompany = iCartDAO.save(
                data.apply {
                    quantity = data.quantity?.plus(1)
                    totalPrice = quantity?.times(data.product!!.price!!)
                }
            )
            iCartDAO.save(updatedCompany)
        } else
        {
            throw Exception("product id not found")
        }
    }


    override fun getCart(cart: ObjectId):MutableList<Cart?> {
//        return iCartDAO.findAll(cart)
        return iCartDAO.getCart(cart)
    }

    override fun getCartProduct(user: ObjectId,product:ObjectId):Cart?{
        return iCartDAO.getCartProduct(user,product)
    }

    override fun deleteCart(id: String): String {
        return if(iCartDAO.existsById(id))
        {
            iCartDAO.deleteById(id)
            "Cart id deleted"
        }
        else {
            "Cart with id $id not found"
        }
    }

    override fun emptyCart(id: ObjectId): String {
        iCartDAO.deleteByUserId(id)
        return "Cart id deleted"
    }
}