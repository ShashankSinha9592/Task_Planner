package org.example.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sprintId;

    @NotNull
    @Column(unique = true, nullable = false)
    private String sprintName;

    @FutureOrPresent(message = "Date cannot be past")
    private LocalDate date;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private User user;

}
