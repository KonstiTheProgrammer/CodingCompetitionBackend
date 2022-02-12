package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "User")
@Table(name = "user")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    open var id: Int? = null

    @Column(name = "USERNAME", nullable = false, length = 20)
    open var username: String? = null

    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    open var firstName: String? = null

    @Column(name = "LAST_NAME", nullable = false, length = 45)
    open var lastName: String? = null

    @Column(name = "EMAIL", nullable = false, length = 45)
    open var email: String? = null

    @Lob
    @Column(name = "PASSWORD", nullable = false)
    open var password: String? = null

    @OneToMany(mappedBy = "user")
    open var userAttendsCodingCompetitions: MutableSet<UserAttendsCodingCompetition> = mutableSetOf()

}