package at.kanzler.codingcompetitionbackend.entity

import javax.persistence.*

@Entity(name = "File")
@Table(name = "files")
open class File {
    @Id
    @Column(name = "FILE_ID")
    @SequenceGenerator(name = "FILE_ID_SEQ", sequenceName = "FILE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ID_SEQ")
    open var id: Long? = null

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID", foreignKey = ForeignKey(name = "FILE_TASK_ID_FK"))
    open var task: Task? = null

    @Column(name = "FILE_URL", nullable = false, length = 100)
    open var fileUrl: String? = null



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is File) return false

        if (task != other.task) return false
        if (fileUrl != other.fileUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (task?.hashCode() ?: 0)
        result = 31 * result + (fileUrl?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "File(id=$id, task=$task, fileUrl=$fileUrl)"
    }


}

