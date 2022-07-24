package me.g1tommy.ktodo.service

import me.g1tommy.ktodo.controller.v1.dto.TodoItemRequest
import me.g1tommy.ktodo.controller.v1.dto.TodoItemResponse
import me.g1tommy.ktodo.domain.Todo.TodoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TodoService(private val todoRepository: TodoRepository) {

    fun todo(id: Long): Mono<TodoItemResponse> {
        return todoRepository.existsById(id)
            .flatMap { isExisted ->
                if (!isExisted) {
                    throw IllegalStateException("Invalid Id")
                }
                todoRepository.findById(id)
            }
            .flatMap { todo -> Mono.just(TodoItemResponse(todo.id, todo.title, todo.content, todo.due, todo.createdAt, todo.updatedAt)) }
    }

    fun todos(): Flux<TodoItemResponse> {
        return todoRepository.findAll()
            .flatMap { todo -> Mono.just(TodoItemResponse(todo.id, todo.title, todo.content, todo.due, todo.createdAt, todo.updatedAt)) }
    }

    fun newTodo(dto: TodoItemRequest): Mono<TodoItemResponse> {
        val newTodo = dto.toDomain()
        return todoRepository.save(newTodo)
            .flatMap { todo -> Mono.just( TodoItemResponse(todo.id, todo.title, todo.content, todo.due, todo.createdAt, todo.updatedAt)) }
    }

    fun updateTodo(id: Long, request: TodoItemRequest): Mono<TodoItemResponse> {
        return todoRepository.existsById(id)
            .flatMap { isExist ->
                if (!isExist) {
                    throw IllegalStateException("Invalid Id")
                }

                val newTodo = request.toDomain()
                newTodo.id = id
                todoRepository.save(newTodo).flatMap {
                    updatedTodo -> Mono.just(TodoItemResponse(updatedTodo.id, updatedTodo.title, updatedTodo.content, updatedTodo.due, updatedTodo.createdAt, updatedTodo.updatedAt))
                }
            }
    }

    fun deleteTodo(id: Long): Mono<Void> {
        return todoRepository.existsById(id)
            .flatMap { isExist ->
                if (!isExist) {
                    throw IllegalStateException("Invalid Id")
                }
                todoRepository.deleteById(id)
            }
    }
}