package com.example.dokizone.core

import java.io.IOException

data class NotFoundException(override val message: String) : IOException(message)
data class BadRequestException(override val message: String) : IOException(message)
data class ServerErrorException(override val message: String) : IOException(message)