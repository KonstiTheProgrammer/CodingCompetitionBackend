package at.kanzler.codingcompetitionbackend.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "verification_token")
open class VerificationToken {
    companion object {
        const val EXPIRATION_TIME: Long = 3
    }

    @Id
    @SequenceGenerator(name = "verification_token_seq", sequenceName = "verification_token_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_token_seq")
    open var id: Long? = null

    @Column(name = "TOKEN", nullable = false)
    open var token: String? = null

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "USER_ID",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_verification_token_user_user_id"))
    open var user: User? = null;

    @Column(name = "EXPIRATION_DATE", nullable = false)
    open var expirationTime: LocalDate? = null;

    constructor(user: User, token: String) {
        this.user = user
        this.token = token
        this.expirationTime = LocalDate.now().plusDays(EXPIRATION_TIME)
    }

    constructor(token: String) {
        this.token = token
        this.expirationTime = LocalDate.now().plusDays(EXPIRATION_TIME)
    }
}