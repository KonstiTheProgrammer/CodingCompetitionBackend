package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "File")
@Table(name = "files")
open class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID")
    open var task: Task? = null

    @Column(name = "FILE_URL", nullable = false, length = 100)
    open var fileUrl: String? = null

}