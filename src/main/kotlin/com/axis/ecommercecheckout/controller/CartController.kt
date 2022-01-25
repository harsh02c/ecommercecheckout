package com.axis.ecommercecheckout.controller

import com.axis.ecommercecheckout.dao.ICartDAO
import com.axis.ecommercecheckout.dao.IProductDAO
import com.axis.ecommercecheckout.dao.ISellerDAO
import com.axis.ecommercecheckout.dao.IUserDAO
import com.axis.ecommercecheckout.model.Cart
import com.axis.ecommercecheckout.model.CartProduct
import com.axis.ecommercecheckout.model.Product
import com.axis.ecommercecheckout.model.User
import com.axis.ecommercecheckout.service.ICartService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/ecommerce-checkout/cart")
class CartController {
    @Autowired
    private lateinit var iCartService: ICartService
    @Autowired
    private lateinit var iSellerDAO: ISellerDAO
    @Autowired
    private lateinit var iProductDAO: IProductDAO
    @Autowired
    private lateinit var iUserDAO: IUserDAO
    @Autowired
    private lateinit var iCartDAO: ICartDAO

    @Autowired
    private val mongoTemplate: MongoTemplate? = null
//    @PostMapping("/addToCart")
//    fun addToCart( @RequestParam("productId")  productId: String,
//                   @RequestParam("userId")  userId: String,
//                   @RequestParam("quantity")  quantity: String
//                    ): ResponseEntity<Any?>
//    {
//        var product = iProductDAO.findById(productId) //findById get Optional<>
//        val roomProduct: Product = product.get()
//
//        var user = iUserDAO.findById(userId) //findById get Optional<>
//        val roomUser: User = user.get()
//
//        var getUserCart = iCartService.getCart(ObjectId(userId))
//
////        if(getUserCart.isNullOrEmpty()){
//        if(getUserCart==null){
//
//            var list =  listOf(  CartProduct(roomProduct._id, roomProduct,"1") )
//
//            var cart = Cart()
//            cart.product  = list
//            cart.user     = roomUser
//            cart.quantity = quantity
//
//            var addToCart = iCartService.addToCart(cart)
//            return ResponseEntity(addToCart, HttpStatus.OK)
//        }else{
////            var getCart = iCartService.getCartProduct(ObjectId(userId), ObjectId(productId))
//            var getCart = iCartService.getCart(ObjectId(userId))
//
//            println(getCart!!.product?.size )
//            var list = listOf( CartProduct(roomProduct._id, roomProduct,"1") )
//
//
//            for (i in 0 until getCart!!.product!!.size-1 ) {
//                println("Simple for loop: i = $i")
//
//                list.toMutableList().add(getCart!!.product?.get(i) as CartProduct)
//            }
//            var cart = Cart()
//            cart.product  = list
//            cart.user     = roomUser
//            cart.quantity = quantity
//
//            var addToCart = iCartService.addToCart(cart)
//            return ResponseEntity(addToCart, HttpStatus.OK)
//        }
//    }

    @PostMapping("/addToCart")
    fun addToCart( @RequestParam("productId")  productId: String,
                   @RequestParam("userId")  userId: String,
                   @RequestParam("quantity")  quantity: String
    ): ResponseEntity<Any?>
    {
        var product = iProductDAO.findById(productId) //findById get Optional<>
        val roomProduct: Product = product.get()

        var user = iUserDAO.findById(userId) //findById get Optional<>
        val roomUser: User = user.get()

        var getUserCart = iCartService.getCartProduct(ObjectId(userId),ObjectId(productId))
        if(getUserCart==null) {
            var cart = Cart()
            cart.product  = roomProduct
            cart.user     = roomUser
            cart.quantity = 1
            cart.totalPrice = roomProduct.price
            var addToCart = iCartService.addToCart(cart)
            return ResponseEntity(addToCart, HttpStatus.OK)
        }else{
            var cart = Cart()
            cart.product  = roomProduct
            cart.user     = roomUser
            cart.quantity = 1
            cart.totalPrice = roomProduct.price
            var addToCart = getUserCart!!._id?.let { iCartService.updateCart(it,cart) }
            return ResponseEntity(addToCart, HttpStatus.OK)
        }
    }

    @GetMapping("/getCart/{id}")
    fun getCart(@PathVariable id : ObjectId): ResponseEntity<Any?>
    {
        var getCart = iCartService.getCart(id)
        return ResponseEntity(getCart, HttpStatus.OK)
    }

    @DeleteMapping("/deleteById/{id}")
    fun deleteProductById(@PathVariable id: String):ResponseEntity<String?>
    {
        var deleteCart = iCartService.deleteCart(id)
        return ResponseEntity(deleteCart,HttpStatus.OK)
    }
}