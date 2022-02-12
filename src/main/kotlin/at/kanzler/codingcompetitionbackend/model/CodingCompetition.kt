package at.kanzler.codingcompetitionbackend.model

import java.time.Instant
import javax.persistence.*

@Entity(name = "CodingCompetition")
@Table(name = "coding_competition")
open class CodingCompetition {
    @Id
    @Column(name = "CODING_COMPETITION_ID", nullable = false)
    @SequenceGenerator(name = "CODING_COMPETITION_ID_GENERATOR",
        sequenceName = "CODING_COMPETITION_ID_SEQ",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CODING_COMPETITION_ID_SEQ")
    open var id: Long? = null

    @Column(name = "START_DATE", nullable = false)
    open var startDate: Instant? = null

    @Column(name = "END_DATE", nullable = false, length = 45)
    open var endDate: String? = null

    @Column(name = "TITLE", nullable = false, length = 100)
    open var title: String? = null

    @Lob
    @Column(name = "DESCRIPTION", nullable = false)
    open var description: String? = null

    @OneToMany(mappedBy = "competition")
    open var tasks: MutableSet<Task> = mutableSetOf()

    @ManyToMany
    @JoinTable(name = "coding_competition_has_location",
        joinColumns = [JoinColumn(name = "CODING_COMPETITION_ID")],
        inverseJoinColumns = [JoinColumn(name = "LOCATION_ID")])

    open var locations: MutableSet<Location> = mutableSetOf()

    @OneToMany(mappedBy = "codingCompetition")
    open var userAttendsCodingCompetitions: MutableSet<UserAttendsCodingCompetition> = mutableSetOf()
}