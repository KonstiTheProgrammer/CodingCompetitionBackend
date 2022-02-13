package at.kanzler.codingcompetitionbackend.model

import java.io.Serializable

open class UserModel {
    var username: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var password: String? = null
    var matchPassword: String? = null
}
