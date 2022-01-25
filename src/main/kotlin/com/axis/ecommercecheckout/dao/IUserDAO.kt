package com.axis.ecommercecheckout.dao

import com.axis.ecommercecheckout.model.Seller
import com.axis.ecommercecheckout.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface IUserDAO: MongoRepository<User, String> {
}