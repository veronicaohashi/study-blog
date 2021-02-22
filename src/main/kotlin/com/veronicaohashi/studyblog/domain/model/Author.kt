package com.veronicaohashi.studyblog.domain.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "authors")
data class Author(
    @Id @GeneratedValue
    val id: UUID,
    val name: String,
    val about: String?
)
