package org.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @Enumerated(value = EnumType.STRING)
    private TaskType taskType;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Enumerated(value = EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TaskStatus taskStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Sprint sprint;

}
