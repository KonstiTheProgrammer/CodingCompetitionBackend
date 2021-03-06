package at.kanzler.codingcompetitionbackend.entity

import at.kanzler.codingcompetitionbackend.enum.Role
import javax.persistence.*

@Entity(name = "User")
@Table(name = "users", uniqueConstraints = [UniqueConstraint(name = "unique_username", columnNames = ["username"])])
open class User {
    @Id
    @Column(name = "USER_ID")
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    open var id: Long? = null

    @Column(name = "USERNAME", nullable = false, length = 20)
    open var username: String? = null

    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    open var firstName: String? = null

    @Column(name = "LAST_NAME", nullable = false, length = 45)
    open var lastName: String? = null

    @Column(name = "EMAIL", nullable = false, length = 45)
    open var email: String? = null

    @Column(name = "ROLE", nullable = false, columnDefinition = "smallint")
    open var role: Role = Role.USER

    @Column(name = "ENABLED", nullable = false)
    open var enabled: Boolean? = false

    @Column(name = "PASSWORD", nullable = false, length = 60)
    open var password: String? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    open var userAttendsCodingCompetitions: MutableSet<UserAttendsCodingCompetition> = mutableSetOf()

//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    open var verificationToken: VerificationToken? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (username != other.username) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (userAttendsCodingCompetitions != other.userAttendsCodingCompetitions) return false
//        if (verificationToken != other.verificationToken) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + userAttendsCodingCompetitions.hashCode()
//        result = 31 * result + verificationToken.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, username=$username, firstName=$firstName, lastName=$lastName, email=$email, password=$password, userAttendsCodingCompetitions=$userAttendsCodingCompetitions))"
    }
}