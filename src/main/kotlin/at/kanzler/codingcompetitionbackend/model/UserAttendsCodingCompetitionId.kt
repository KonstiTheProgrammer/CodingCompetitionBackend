package at.kanzler.codingcompetitionbackend.model

import org.hibernate.Hibernate
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Entity

@Embeddable
open class UserAttendsCodingCompetitionId : Serializable {
    @Column(name = "USER_ID", nullable = false)
    open var userId: Int? = null

    @Column(name = "CODING_COMPETITION_ID", nullable = false)
    open var codingCompetitionId: Int? = null

    override fun hashCode(): Int = Objects.hash(userId, codingCompetitionId)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as UserAttendsCodingCompetitionId

        return userId == other.userId &&
                codingCompetitionId == other.codingCompetitionId
    }

    companion object {
        private const val serialVersionUID = 3590657543791683435L
    }
}