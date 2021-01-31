package com.veronicaohashi.studyblog.domain.exception

import com.veronicaohashi.studyblog.domain.model.Author

class AuthorAlreadyExistsException(author: Author) : BusinessException("Author $author already exists")
