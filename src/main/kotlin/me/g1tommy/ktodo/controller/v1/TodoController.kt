package me.g1tommy.ktodo.controller.v1

import me.g1tommy.ktodo.controller.v1.dto.TodoItemRequest
import me.g1tommy.ktodo.controller.v1.dto.TodoItemResponse
import me.g1tommy.ktodo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.net.URI

@RestController
@RequestMapping("/v1/todo")
class TodoController(private val todoService: TodoService) {

    @GetMapping("/{id}")
    fun todoItem(@PathVariable id: Long): Mono<ResponseEntity<TodoItemResponse>> {
        return todoService.todo(id)
            .flatMap { todo -> Mono.just(ok(todo)) }
    }

    @GetMapping
    fun todoList(): Mono<ResponseEntity<List<TodoItemResponse>>> {
        return todoService.todos()
            .collectList()
            .flatMap { todo -> Mono.just(ok(todo)) }
    }

    @PostMapping
    fun newTodo(@RequestBody requestMono: Mono<TodoItemRequest>): Mono<ResponseEntity<TodoItemResponse>> {
        return requestMono.flatMap { request -> todoService.newTodo(request) }
            .map { todo -> created(URI.create("/todo/" + todo.id)).body(todo) }
    }

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody requestMono: Mono<TodoItemRequest>): Mono<ResponseEntity<TodoItemResponse>> {
        return requestMono.flatMap { request ->
            todoService.updateTodo(id, request)
                .flatMap { response -> Mono.just(ok(response)) }
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long): Mono<ResponseEntity<Any>> {
        return todoService.deleteTodo(id).thenReturn(noContent().build())
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleException(e: IllegalStateException): Mono<ResponseEntity<String>> {
        return Mono.just(badRequest().body(e.message))
    }

}