package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "Location")
@Table(name = "locations")
open class Location {
    @Id
    @Column(name = "LOCATION_ID", nullable = false)
    @SequenceGenerator(name = "LOCATION_ID_SEQ", sequenceName = "LOCATION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_ID_SEQ")
    open var id: Long? = null

    @Column(name = "LOCATION_NAME", nullable = false, length = 45)
    open var locationName: String? = null

    @Column(name = "STREET", nullable = false, length = 70)
    open var street: String? = null

    @Column(name = "POSTAL_CODE", nullable = false, length = 45)
    open var postalCode: String? = null

    @Column(name = "PHONE_NR", nullable = false, length = 45)
    open var phoneNr: String? = null

    @Column(name = "CITY", nullable = false, length = 45)
    open var city: String? = null

    @Column(name = "HOUSE_NUMBER", nullable = false, length = 8)
    open var houseNumber: String? = null

    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    open var codingCompetitions: MutableSet<CodingCompetition> = mutableSetOf()

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    open var userAttendsCodingCompetitions: MutableSet<UserAttendsCodingCompetition> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Location) return false

        if (id != other.id) return false
        if (locationName != other.locationName) return false
        if (street != other.street) return false
        if (postalCode != other.postalCode) return false
        if (phoneNr != other.phoneNr) return false
        if (city != other.city) return false
        if (houseNumber != other.houseNumber) return false
        if (codingCompetitions != other.codingCompetitions) return false
        if (userAttendsCodingCompetitions != other.userAttendsCodingCompetitions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (locationName?.hashCode() ?: 0)
        result = 31 * result + (street?.hashCode() ?: 0)
        result = 31 * result + (postalCode?.hashCode() ?: 0)
        result = 31 * result + (phoneNr?.hashCode() ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (houseNumber?.hashCode() ?: 0)
        result = 31 * result + codingCompetitions.hashCode()
        result = 31 * result + userAttendsCodingCompetitions.hashCode()
        return result
    }

    override fun toString(): String {
        return "Location(id=$id, locationName=$locationName, street=$street, postalCode=$postalCode, phoneNr=$phoneNr, city=$city, houseNumber=$houseNumber, codingCompetitions=$codingCompetitions, userAttendsCodingCompetitions=$userAttendsCodingCompetitions)"
    }
}