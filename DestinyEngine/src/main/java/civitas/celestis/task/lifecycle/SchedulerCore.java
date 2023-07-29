package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>SchedulerCore</h2>
 * <p>The internal core of a {@link Scheduler}.</p>
 */
public final class SchedulerCore {
    /**
     * Creates a new scheduler core.
     *
     * @param name     Name of this core
     * @param accuracy Accuracy of this core in milliseconds
     */
    @SuppressWarnings("BusyWait")
    public SchedulerCore(@Nonnull String name, @Nonnegative long accuracy) {
        this.tasks = new ArrayList<>();
        this.previousTimes = new HashMap<>();
        this.thread = new Thread(() -> {
            while (true) {
                List.copyOf(tasks).forEach(t -> {
                    // Cache current time for consistency
                    final DateTime now = DateTime.now();
                    final DateTime previous = previousTimes.getOrDefault(t, now);
                    final Duration delta = new Duration(previous, now);

                    // Respect interval
                    if (delta.isShorterThan(t.interval())) return;

                    // Execute task and keep time
                    t.execute(delta);
                    previousTimes.put(t, now);
                });

                try {
                    Thread.sleep(accuracy);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, name);
    }

    /**
     * Registers a task to this scheduler core.
     *
     * @param task Task to register
     */
    public void registerTask(@Nonnull Task task) {
        tasks.add(task);
        previousTimes.put(task, DateTime.now());
    }

    /**
     * Unregisters a task from this scheduler core.
     *
     * @param task Task to unregister
     */
    public void unregisterTask(@Nonnull Task task) {
        if (tasks.remove(task)) previousTimes.remove(task);
    }

    /**
     * Starts this scheduler core.
     */
    public void start() {
        thread.start();
    }

    /**
     * Stops this scheduler core.
     */
    public void stop() {
        thread.interrupt();
    }

    private final List<Task> tasks;
    private final Map<Task, DateTime> previousTimes;
    private final Thread thread;
}
