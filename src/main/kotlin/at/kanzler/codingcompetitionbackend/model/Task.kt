package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "Task")
@Table(name = "tasks")
open class Task {
    @Id
    @Column(name = "TASK_ID", nullable = false)
    @SequenceGenerator(name = "TASK_ID_SEQ", sequenceName = "TASK_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ID_SEQ")
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPETITION_ID", foreignKey = ForeignKey(name = "TASK_COMPETITION_ID_FK"))
    open var competition: CodingCompetition? = null

    @Column(name = "TITLE", nullable = false, length = 45)
    open var title: String? = null

    @Column(name = "LEVEL", nullable = false)
    open var level: Int? = null

    @Lob
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "TEXT")
    open var description: String? = null

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    open var files: MutableSet<File> = mutableSetOf()

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    open var submits: MutableSet<Submit> = mutableSetOf()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Task) return false

        if (id != other.id) return false
        if (competition != other.competition) return false
        if (title != other.title) return false
        if (level != other.level) return false
        if (description != other.description) return false
        if (files != other.files) return false
        if (submits != other.submits) return false

        return true
    }
    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (competition?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (level ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + files.hashCode()
        result = 31 * result + submits.hashCode()
        return result
    }
    override fun toString(): String {
        return "Task(id=$id, competition=$competition, title=$title, level=$level, description=$description, files=$files, submits=$submits)"
    }
}