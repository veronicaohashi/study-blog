package com.veronicaohashi.studyblog.infrastrucure.repository

import com.veronicaohashi.studyblog.domain.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CategoryRepository : JpaRepository<Category, UUID>
