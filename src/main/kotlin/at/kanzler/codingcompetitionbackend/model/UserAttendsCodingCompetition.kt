package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "UserAttendsCodingCompetition")
@Table(name = "user_attends_coding_competition")
open class UserAttendsCodingCompetition {
    @EmbeddedId
    open var id: UserAttendsCodingCompetitionId? = null

    @MapsId("codingCompetitionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODING_COMPETITION_ID")
    open var codingCompetition: CodingCompetition? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    open var location: Location? = null

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    open var user: User? = null

    @OneToMany(mappedBy = "userAttendsCodingCompetition")
    open var submits: MutableSet<Submit> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserAttendsCodingCompetition) return false

        if (id != other.id) return false
        if (codingCompetition != other.codingCompetition) return false
        if (location != other.location) return false
        if (user != other.user) return false
        if (submits != other.submits) return false

        return true
    }
    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (codingCompetition?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + submits.hashCode()
        return result
    }
    override fun toString(): String {
        return "UserAttendsCodingCompetition(id=$id, codingCompetition=$codingCompetition, location=$location, user=$user, submits=$submits)"
    }
}