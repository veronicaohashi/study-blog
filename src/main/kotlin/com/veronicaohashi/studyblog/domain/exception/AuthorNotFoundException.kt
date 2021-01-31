package com.veronicaohashi.studyblog.domain.exception

import java.util.UUID

class AuthorNotFoundException(id: UUID) : BusinessException("Author $id not found")
