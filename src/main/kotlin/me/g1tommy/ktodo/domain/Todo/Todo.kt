package me.g1tommy.ktodo.domain.Todo

import lombok.Getter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table
@Getter
data class Todo(
    @Id var id: Long?,
    var title: String,
    var content: String,
    var due: Instant,
    @CreatedDate var createdAt: Instant? = Instant.now(),
    @LastModifiedDate var updatedAt: Instant? = createdAt
)