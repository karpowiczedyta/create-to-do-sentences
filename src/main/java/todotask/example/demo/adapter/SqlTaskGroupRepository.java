package todotask.example.demo.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import todotask.example.demo.model.TaskGroup;
import todotask.example.demo.model.TaskGroupRepository;

import java.util.List;

@Repository
public interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {

    @Override
    @Query("select distinct g from TaskGroup g join fetch g.tasks")
    List<TaskGroup> findAll();

    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);

}
