package at.kanzler.codingcompetitionbackend.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "CodingCompetition")
@Table(name = "coding_competition")
open class CodingCompetition {
    @Id
    @Column(name = "CODING_COMPETITION_ID")
    @SequenceGenerator(name = "CODING_COMPETITION_ID_SEQ",
        sequenceName = "CODING_COMPETITION_ID_SEQ",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CODING_COMPETITION_ID_SEQ")
    open var id: Long? = null

    @Column(name = "START_DATE", nullable = false)
    open var startDate: LocalDate? = null

    @Column(name = "END_DATE", nullable = false, length = 45)
    open var endDate: LocalDate? = null

    @Column(name = "TITLE", nullable = false, length = 100)
    open var title: String? = null

    @Lob
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "TEXT")
    open var description: String? = null

    @OneToMany(mappedBy = "competition")
    open var tasks: MutableSet<Task> = mutableSetOf()

    @ManyToMany
    @JoinTable(name = "coding_competition_has_location",
        joinColumns = [JoinColumn(name = "CODING_COMPETITION_ID")],
        inverseJoinColumns = [JoinColumn(name = "LOCATION_ID")])
    @JoinColumn(name = "CODING_COMPETITION_ID",
        referencedColumnName = "CODING_COMPETITION_ID",
        foreignKey = ForeignKey(name = "CODING_COMPETITION_HAS_LOCATION_FK"))
    open var locations: MutableSet<Location> = mutableSetOf()

    @OneToMany(mappedBy = "codingCompetition")
    open var userAttendsCodingCompetitions: MutableSet<UserAttendsCodingCompetition> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CodingCompetition

        if (id != other.id) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (tasks != other.tasks) return false
        if (locations != other.locations) return false
        if (userAttendsCodingCompetitions != other.userAttendsCodingCompetitions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (startDate?.hashCode() ?: 0)
        result = 31 * result + (endDate?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + tasks.hashCode()
        result = 31 * result + locations.hashCode()
        result = 31 * result + userAttendsCodingCompetitions.hashCode()
        return result
    }

    override fun toString(): String {
        return "CodingCompetition(id=$id, startDate=$startDate, endDate=$endDate, title=$title, description=$description, tasks=$tasks, locations=$locations, userAttendsCodingCompetitions=$userAttendsCodingCompetitions)"
    }
}