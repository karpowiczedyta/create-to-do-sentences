package todotask.example.demo.model.projection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import todotask.example.demo.model.Task;
import todotask.example.demo.model.TaskGroup;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GroupReadModelTest {

    @Test
    @DisplayName("should create null deadline for group when no task deadlines")
    void constructor_noDeadlines_createsNullDeadline() {

        //given
        var source = new TaskGroup();
        source.setDescription("foo");
        source.setTasks(List.of(new Task("bar", null)));
        //when
        var result = new GroupReadModel(source);
        //then
        assertThat(result).hasFieldOrPropertyWithValue("deadline", null);

    }
}