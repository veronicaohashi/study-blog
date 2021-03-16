package com.veronicaohashi.studyblog.infrastrucure.repository

import com.veronicaohashi.studyblog.domain.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PostRepository : JpaRepository<Post, UUID> {

  @Query("""
      SELECT p
      FROM Post p
      WHERE lower(p.title) like lower(concat('%', :title, '%'))
    """)
  fun findAllByTitle(@Param("title") title: String?, pageable: Pageable): Page<Post>
}
