package com.veronicaohashi.studyblog.domain.exception

import java.util.UUID

class CategoryNotFoundException(ids: List<UUID>) : BusinessException("Categories $ids not found")
