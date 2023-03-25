package org.example.Repository;

import org.example.Model.Priority;
import org.example.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer>, PagingAndSortingRepository<Task, Integer> {

    @Query("SELECT t FROM Task t " +
            "INNER JOIN Fetch t.sprint s " +
            "INNER JOIN Fetch t.sprint.user u " +
            "WHERE u.userId = :userId " +
            "AND t.priority = :priority " +
            "AND s.sprintId = :sprintId")
    public List<Task> getTasksByPriorityAndSprint(@Param("priority") Priority priority, @Param("sprintId") Integer sprintId, @Param("userId") Integer userId);

    @Query("SELECT t FROM Task t " +
            "INNER JOIN Fetch t.sprint s" +
            "INNER JOIN Fetch t.sprint.user u " +
            "WHERE u.userId = :userId " +
            "AND t.priority = :priority")
    public List<Task> getTasksByPriority(@Param("priority") Priority priority, @Param("userId") Integer userId);

    @Query("SELECT t FROM Task t " +
            "INNER JOIN t.sprint s " +
            "INNER JOIN t.sprint.user u " +
            "WHERE s.sprintId = :sprintId " +
            "AND u.userId = :userId")
    public Page<Task> getTasksByPageNumberAndNumberOfDataAndSortedByFieldInSprint(@Param("userId") Integer userId, @Param("sprintId") Integer sprintid, Pageable pageable);

    @Query("SELECT t FROM Task t " +
            "INNER JOIN t.sprint s " +
            "INNER JOIN t.sprint.user u " +
            "WHERE u.userId = :userId")
    public Page<Task> getTasksByPageNumberAndNumberOfDataAndSortedByField(@Param("userId") Integer userId, Pageable pageable);


    @Query("SELECT t FROM Task t " +
            "INNER JOIN t.sprint s " +
            "INNER JOIN t.sprint.user u " +
            "WHERE u.userId = :userId " +
            "AND " +
            "s.sprintId= :sprintId")
    public List<Task> getSortedTaskInSprintAndByField(@Param("sprintId") Integer sprintId , @Param("userId") Integer userId, Sort sort);

}
