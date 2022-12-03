package de.promotos.sirbookmark.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.io.Serializable

@Entity
class BarEntity : Serializable {

    @Id
    @GeneratedValue
    private val id: Long? = null

    @Column(nullable = false)
    var name: String? = null
        private set

    @Column(nullable = false)
    var state: String? = null
        private set

    protected constructor()

    constructor(name: String?, state: String?) {
        this.name = name
        this.state = state
    }

}