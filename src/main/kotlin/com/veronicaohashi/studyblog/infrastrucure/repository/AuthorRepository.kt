package com.veronicaohashi.studyblog.infrastrucure.repository

import com.veronicaohashi.studyblog.domain.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AuthorRepository : JpaRepository<Author, UUID> {
  fun findByName(name: String): Author?
}
