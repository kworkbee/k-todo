package me.g1tommy.ktodo.domain.Todo

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository: R2dbcRepository<Todo, Long> {
}