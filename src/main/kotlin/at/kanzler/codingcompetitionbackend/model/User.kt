package at.kanzler.codingcompetitionbackend.model

import org.hibernate.annotations.Fetch
import javax.persistence.*

@Entity(name = "User")
@Table(name = "users")
open class User {
    @Id
    @Column(name = "USER_ID", nullable = false)
    @SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    open var id: Long? = null

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    open var userAttendsCodingCompetitions: MutableSet<UserAttendsCodingCompetition> = mutableSetOf()

}