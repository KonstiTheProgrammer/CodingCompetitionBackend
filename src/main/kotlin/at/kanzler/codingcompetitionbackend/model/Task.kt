package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "Task")
@Table(name = "tasks")
open class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPETITION_ID")
    open var competition: CodingCompetition? = null

    @Column(name = "TITLE", nullable = false, length = 45)
    open var title: String? = null

    @Column(name = "LEVEL", nullable = false)
    open var level: Int? = null

    @Lob
    @Column(name = "DESCRIPTION", nullable = false)
    open var description: String? = null

    @OneToMany(mappedBy = "task")
    open var files: MutableSet<File> = mutableSetOf()

    @OneToMany(mappedBy = "task")
    open var submits: MutableSet<Submit> = mutableSetOf()

}