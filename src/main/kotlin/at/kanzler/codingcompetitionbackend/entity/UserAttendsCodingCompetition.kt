package at.kanzler.codingcompetitionbackend.entity

import javax.persistence.*

@Entity(name = "UserAttendsCodingCompetition")
@Table(name = "user_attends_coding_competition")
open class UserAttendsCodingCompetition {
    @EmbeddedId
    open var id: UserAttendsCodingCompetitionId? = null

    @MapsId("codingCompetitionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "CODING_COMPETITION_ID", foreignKey = ForeignKey(name = "FK_USER_ATTENDS_CODING_COMPETITION"))
    open var codingCompetition: CodingCompetition? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "LOCATION_ID", foreignKey = ForeignKey(name = "FK_USER_ATTENDS_LOCATION"))
    open var location: Location? = null

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "USER_ID",
        nullable = false,
        foreignKey = ForeignKey(name = "FK_USER_ATTENDS_CODING_COMPETITION_USER"))
    open var user: User? = null

    @OneToMany(mappedBy = "userAttendsCodingCompetition", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    open var submits: MutableSet<Submit> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserAttendsCodingCompetition) return false

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