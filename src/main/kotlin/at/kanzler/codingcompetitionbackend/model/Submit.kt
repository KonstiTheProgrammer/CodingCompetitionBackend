package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "Submit")
@Table(name = "submits")
open class Submit {
    @Id
    @Column(name = "SUBMIT_ID", nullable = false)
    @SequenceGenerator(name = "SUBMIT_ID_SEQ", sequenceName = "SUBMIT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMIT_ID_SEQ")
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID",
        foreignKey = ForeignKey(name = "SUBMIT_TASK_ID_FK"))
    open var task: Task? = null

    @JoinColumns(JoinColumn(name = "USER_ID"),
        JoinColumn(name = "CODING_COMPETITION_ID"),
        foreignKey = ForeignKey(name = "SUBMIT_USER_FK"))
    @ManyToOne(fetch = FetchType.LAZY)
    open var userAttendsCodingCompetition: UserAttendsCodingCompetition? = null

    @Lob
    @Column(name = "CONTENT_JSON", nullable = false, columnDefinition = "TEXT")
    open var contentJson: String? = null

    @Column(name = "VALID", nullable = false)
    open var valid: Boolean? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Submit) return false

        if (id != other.id) return false
        if (task != other.task) return false
        if (userAttendsCodingCompetition != other.userAttendsCodingCompetition) return false
        if (contentJson != other.contentJson) return false
        if (valid != other.valid) return false

        return true
    }

    override fun toString(): String {
        return "Submit(id=$id, task=$task, userAttendsCodingCompetition=$userAttendsCodingCompetition, contentJson=$contentJson, valid=$valid)"
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (task?.hashCode() ?: 0)
        result = 31 * result + (userAttendsCodingCompetition?.hashCode() ?: 0)
        result = 31 * result + (contentJson?.hashCode() ?: 0)
        result = 31 * result + (valid?.hashCode() ?: 0)
        return result
    }
}