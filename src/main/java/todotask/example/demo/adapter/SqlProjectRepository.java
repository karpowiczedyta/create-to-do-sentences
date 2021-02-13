package todotask.example.demo.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import todotask.example.demo.model.Project;
import todotask.example.demo.model.ProjectRepository;


import java.util.List;

@Repository
public interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {

    @Override
    @Query("select distinct p from Project p join fetch p.steps")
    List<Project> findAll();

}
