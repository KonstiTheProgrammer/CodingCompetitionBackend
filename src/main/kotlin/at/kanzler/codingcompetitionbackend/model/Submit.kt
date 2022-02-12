package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "Submit")
@Table(name = "submits")
open class Submit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBMIT_ID", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID")
    open var task: Task? = null

    @JoinColumns(
        JoinColumn(name = "USER_ID"),
        JoinColumn(name = "CODING_COMPETITION_ID")
    )
    @ManyToOne(fetch = FetchType.LAZY)
    open var userAttendsCodingCompetition: UserAttendsCodingCompetition? = null

    @Lob
    @Column(name = "CONTENT_JSON", nullable = false)
    open var contentJson: String? = null

    @Column(name = "VALID", nullable = false)
    open var valid: Int? = null

}