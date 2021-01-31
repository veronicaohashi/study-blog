package com.veronicaohashi.studyblog.domain.exception

import java.lang.RuntimeException

open class BusinessException(message: String) : RuntimeException(message)
