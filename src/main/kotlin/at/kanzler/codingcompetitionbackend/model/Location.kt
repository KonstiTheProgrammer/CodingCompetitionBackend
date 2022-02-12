package at.kanzler.codingcompetitionbackend.model

import javax.persistence.*

@Entity(name = "Location")
@Table(name = "locations")
open class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID", nullable = false)
    open var id: Int? = null

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

    @ManyToMany(mappedBy = "locations")
    open var codingCompetitions: MutableSet<CodingCompetition> = mutableSetOf()

    @OneToMany(mappedBy = "location")
    open var userAttendsCodingCompetitions: MutableSet<UserAttendsCodingCompetition> = mutableSetOf()

}