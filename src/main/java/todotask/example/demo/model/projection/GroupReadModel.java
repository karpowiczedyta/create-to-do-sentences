package todotask.example.demo.model.projection;

import todotask.example.demo.model.Task;
import todotask.example.demo.model.TaskGroup;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GroupReadModel {
    private int id;
    private String description;
    private LocalDateTime deadline;
    private List<GroupTaskReadModel> tasks = new ArrayList<>();

    public GroupReadModel(TaskGroup source) {
        id = source.getId();
        this.description = source.getDescription();

        source.getTasks().stream()
                .map(Task::getDeadline)
                .filter(Objects::nonNull)
                .max(LocalDateTime::compareTo)
                .ifPresent(date -> deadline = date);

        tasks = source.getTasks().stream()
                .map(GroupTaskReadModel::new)
                .collect(Collectors.toList());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<GroupTaskReadModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<GroupTaskReadModel> tasks) {
        this.tasks = tasks;
    }
}
