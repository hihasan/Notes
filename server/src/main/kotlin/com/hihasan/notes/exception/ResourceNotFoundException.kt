package com.hihasan.notes.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(val resourceName: String?, val fieldName: String?, val fieldValue: Long) :
    RuntimeException(
        String.format("%s Not Found %s : %s", resourceName, fieldName, fieldValue)
    )