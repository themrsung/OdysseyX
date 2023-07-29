package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import civitas.celestis.util.Counter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;

/**
 * <h2>Scheduler</h2>
 * <p>Manages the lifecycle of tasks.</p>
 */
public class Scheduler {
    /**
     * Starts this scheduler.
     */
    public void start() {
        Arrays.stream(cores).forEach(SchedulerCore::start);
    }

    /**
     * Stops this scheduler.
     */
    public void stop() {
        Arrays.stream(cores).forEach(SchedulerCore::stop);
    }

    /**
     * Registers a task to this scheduler.
     *
     * @param task Task to register
     */
    public void registerTask(@Nonnull Task task) {
        cores[distributor.next()].registerTask(task);
    }

    /**
     * Registers multiple tasks to this scheduler.
     * Tasks will be registered to a single core.
     *
     * @param tasks Tasks to register
     */
    public void registerSyncTasks(@Nonnull Task... tasks) {
        final int i = distributor.next();
        Arrays.stream(tasks).forEach(cores[i]::registerTask);
    }

    /**
     * Registers multiple tasks to this scheduler.
     * Tasks will be registered to a single core.
     *
     * @param tasks Collection of tasks to register
     */
    public void registerSyncTasks(@Nonnull Collection<Task> tasks) {
        final int i = distributor.next();
        tasks.forEach(cores[i]::registerTask);
    }

    /**
     * Registers multiple tasks to this scheduler.
     * Tasks will be evenly distributed.
     *
     * @param tasks Tasks to register
     */
    public void registerAsyncTasks(@Nonnull Task... tasks) {
        Arrays.stream(tasks).forEach(this::registerTask);
    }

    /**
     * Registers multiple tasks to this scheduler.
     * Tasks will be evenly distributed.
     *
     * @param tasks Collection of tasks to register
     */
    public void registerAsyncTasks(@Nonnull Collection<Task> tasks) {
        tasks.forEach(this::registerTask);
    }

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task Task to unregister
     */
    public void unregisterTask(@Nonnull Task task) {
        Arrays.stream(cores).forEach(c -> c.unregisterTask(task));
    }

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks Tasks to unregister
     */
    public void unregisterTasks(@Nonnull Task... tasks) {
        Arrays.stream(tasks).forEach(this::unregisterTask);
    }

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks Collection of tasks to unregister
     */
    public void unregisterTasks(@Nonnull Collection<Task> tasks) {
        tasks.forEach(this::unregisterTask);
    }

    private final SchedulerCore[] cores = {
            new SchedulerCore("Scheduler-1", 1),
            new SchedulerCore("Scheduler-2", 1),
            new SchedulerCore("Scheduler-3", 1),
            new SchedulerCore("Scheduler-4", 1),
            new SchedulerCore("Scheduler-5", 1),
            new SchedulerCore("Scheduler-6", 1),
            new SchedulerCore("Scheduler-7", 1),
            new SchedulerCore("Scheduler-8", 1)
    };
    private final Counter distributor = new Counter(cores.length);
}
