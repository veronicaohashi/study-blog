package com.veronicaohashi.studyblog.domain.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
data class Post(
    @Id @GeneratedValue()
    val id: UUID,
    val title: String,
    val subtitle: String,
    val content: String,
    val date: LocalDateTime? = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    val author: Author,
    @OneToMany
    val categories: List<Category>
)
