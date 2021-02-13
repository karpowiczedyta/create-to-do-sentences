package todotask.example.demo.logic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todotask.example.demo.TaskConfigurationProperties;
import todotask.example.demo.model.ProjectRepository;
import todotask.example.demo.model.TaskGroupRepository;
import todotask.example.demo.model.TaskRepository;


@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService projectService(
            ProjectRepository repository,
            TaskGroupRepository taskGroupRepository,
            TaskGroupService service,
            TaskConfigurationProperties config
    ) {
        return new ProjectService(repository, taskGroupRepository, service, config);
    }


    @Bean
    TaskGroupService taskGroupService(

            TaskGroupRepository taskGroupRepository,
            @Qualifier("sqlTaskRepository") TaskRepository taskRepository
    ) {
        return new TaskGroupService(taskGroupRepository, taskRepository);
    }


}
