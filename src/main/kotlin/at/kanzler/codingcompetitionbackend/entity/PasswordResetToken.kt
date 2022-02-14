package at.kanzler.codingcompetitionbackend.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "password_reset_token")
open class PasswordResetToken {
    companion object {
        const val EXPIRATION_TIME: Long = 1

        fun generateExpirationTime(): LocalDateTime {
            return LocalDateTime.now().plusHours(EXPIRATION_TIME)
        }
    }

    @Id
    @SequenceGenerator(name = "password_reset_token_id_seq",
        sequenceName = "password_reset_token_id_seq",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_reset_token_id_seq")
    open var id: Long? = null

    @Column(name = "TOKEN", nullable = false)
    open var token: String? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false, foreignKey = ForeignKey(name = "fk_password_reset_token_user_id"))
    open var user: User? = null;

    @Column(name = "EXPIRATION_DATE", nullable = false)
    open var expirationTime: LocalDateTime? = null;

    constructor(user: User, token: String) {
        this.user = user
        this.token = token
        this.expirationTime = generateExpirationTime()
    }

    constructor(token: String) {
        this.token = token
        this.expirationTime = generateExpirationTime()
    }
}
