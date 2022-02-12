package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "File")
@Table(name = "files")
open class File {
    @Id
    @Column(name = "FILE_ID", nullable = false)
    @SequenceGenerator(name = "FILE_ID_GENERATOR", sequenceName = "FILE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ID_GENERATOR")
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID")
    open var task: Task? = null

    @Column(name = "FILE_URL", nullable = false, length = 100)
    open var fileUrl: String? = null

}