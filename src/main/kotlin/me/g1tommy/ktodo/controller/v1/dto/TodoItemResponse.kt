package me.g1tommy.ktodo.controller.v1.dto

import java.time.Instant

data class TodoItemResponse(val id: Long?, val title: String, val content: String, val due: Instant, val createdAt: Instant?, val updatedAt: Instant?)
