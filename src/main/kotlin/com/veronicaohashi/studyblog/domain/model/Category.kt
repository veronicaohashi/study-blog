package com.veronicaohashi.studyblog.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Category(
    @Id @GeneratedValue
    val id: UUID,
    val name: String
)