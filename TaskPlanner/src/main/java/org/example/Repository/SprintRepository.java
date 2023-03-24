package org.example.Repository;

import org.example.Model.Priority;
import org.example.Model.Sprint;
import org.example.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Integer>, PagingAndSortingRepository<Sprint,Integer> {

    @Query("SELECT s from Sprint s INNER JOIN s.user u where u.userId = :userId")
    public Page<Sprint> getSprintByPageAndSize(@Param("userId") Integer userId, Pageable pageable);

}
