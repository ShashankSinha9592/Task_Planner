package org.example.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCurrentSession {

    @Id
    private Integer userSessionId;

    @NotNull
    @Column(unique = true,nullable = false)
    private String token;

    @NotNull
    private LocalDateTime timeSpan;

}
