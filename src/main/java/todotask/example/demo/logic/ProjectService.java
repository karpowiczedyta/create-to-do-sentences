package todotask.example.demo.logic;

import org.springframework.stereotype.Service;
import todotask.example.demo.TaskConfigurationProperties;
import todotask.example.demo.model.Project;
import todotask.example.demo.model.ProjectRepository;
import todotask.example.demo.model.TaskGroupRepository;
import todotask.example.demo.model.projection.GroupReadModel;
import todotask.example.demo.model.projection.GroupTaskWriteModel;
import todotask.example.demo.model.projection.GroupWriteModel;
import todotask.example.demo.model.projection.ProjectWriteModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskGroupService taskGroupService;
    private TaskConfigurationProperties config;

    public ProjectService() {
    }

    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository, TaskGroupService taskGroupService, TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskGroupService = taskGroupService;
        this.config = config;
    }

    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(ProjectWriteModel toSave) {
        return repository.save(toSave.toProject());
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        GroupReadModel result = repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(project.getSteps().stream()
                            .map(projectStep -> {
                                        var task = new GroupTaskWriteModel();
                                        task.setDescription(projectStep.getDescription());
                                        task.setDeadline(deadline.plusDays(projectStep.getDaysToDeadline()));
                                        return task;
                                    }
                            )
                            .collect(Collectors.toList())
                    );
                    return taskGroupService.createGroup(targetGroup, project);
                })
                .orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
        return result;

    }

}
