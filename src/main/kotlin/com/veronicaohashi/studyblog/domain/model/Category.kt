package com.veronicaohashi.studyblog.domain.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "categories")
data class Category(
    @Id @GeneratedValue
    val id: UUID,
    val name: String
)
