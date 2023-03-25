package org.example.Exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyErrorDetails{

    private LocalDateTime dateAndTime;
    private String message;
    private String description;

}
