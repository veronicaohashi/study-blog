package com.veronicaohashi.studyblog.domain.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.JoinTable

@Entity
@Table(name = "posts")
data class Post(
    @Id @GeneratedValue()
    val id: UUID,
    val title: String,
    val subtitle: String,
    val content: String,
    val writtenAt: LocalDateTime? = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    val author: Author,

    @ManyToMany
    @JoinTable(
        name = "posts_categories",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: List<Category>
)
