package org.example.Repository;

import org.example.Model.Priority;
import org.example.Model.Sprint;
import org.example.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Integer> {

}
