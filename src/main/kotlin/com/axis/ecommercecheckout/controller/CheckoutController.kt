package com.axis.ecommercecheckout.controller

import com.axis.ecommercecheckout.service.ICartService
import com.axis.ecommercecheckout.service.ICheckoutService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.*
import java.awt.SystemColor.text


@RestController
@CrossOrigin
@RequestMapping("/ecommerce-checkout/checkout")
class CheckoutController {
    @Autowired
    private lateinit var iCheckoutService: ICheckoutService
    @Autowired
    private lateinit var iCartService: ICartService

    @Autowired
    private lateinit var emailSender: JavaMailSender

    @GetMapping("/createOrder/{id}")
    fun createOrder(@PathVariable id : ObjectId): ResponseEntity<Any?>
    {
        var getCart = iCartService.getCart(id)

        for (i in 0 until getCart!!.size ) {
//                println("Simple for loop: i = $i")
            println(getCart.get(i)!!.product!!.seller!!.email)

            val message = SimpleMailMessage()
            message.setFrom("noreply@baeldung.com")
//            message.setTo("harsh02chauhan@gmail.com")
            message.setTo(getCart.get(i)!!.product!!.seller!!.email)
            message.setSubject("You have received an order!")
            message.setText("Congratulations!! Your product "+getCart.get(i)!!.product!!.name+" has been purchased by a customer. Please review your order. Thank you!")
            emailSender.send(message)

            var deleteCart = getCart.get(i)!!._id?.let { iCartService.deleteCart(it) }
        }

//        var deleteCart = iCartService.emptyCart(id)
        return ResponseEntity("Mail sent successfully", HttpStatus.OK)
    }



}