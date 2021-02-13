package todotask.example.demo.model.event;

import todotask.example.demo.model.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent {
     TaskUndone(Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
