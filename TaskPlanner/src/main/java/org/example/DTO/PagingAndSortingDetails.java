package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingAndSortingDetails {

    private Integer pageNumber;

    private Integer size;

    private String field;

    private Integer sprintId;

    private String direction;

}
