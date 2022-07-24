package me.g1tommy.ktodo.controller.v1.dto

import me.g1tommy.ktodo.domain.Todo.Todo
import java.time.Instant

data class TodoItemRequest(val title: String, val content: String, val due: Instant) {
    fun toDomain() = Todo(id = null, title = title, content = content, due = due)
}