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

}