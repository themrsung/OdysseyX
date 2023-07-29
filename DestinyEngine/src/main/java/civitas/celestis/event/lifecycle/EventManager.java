package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;
import civitas.celestis.task.lifecycle.SchedulerCore;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <h2>EventManager</h2>
 * <p>Manages the lifecycle of events.</p>
 */
public class EventManager {
    /**
     * Starts processing events.
     */
    public void start() {
        core.start();
    }

    /**
     * Stops processing events.
     */
    public void stop() {
        core.stop();
    }

    /**
     * Calls an event to be handled.
     *
     * @param event Event to call
     * @param <E>   Type of event to call
     */
    public <E extends Event> void call(@Nonnull E event) {
        eventQueue.add(event);
    }

    /**
     * Registers a listener to this event manager.
     *
     * @param listener Listener to register
     */
    public void registerListener(@Nonnull Listener listener) {
        listeners.add(listener);
    }

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners Listeners to register
     */
    public void registerListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::registerListener);
    }

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners Collection of listeners to register
     */
    public void registerListeners(@Nonnull Collection<Listener> listeners) {
        listeners.forEach(this::registerListener);
    }

    /**
     * Unregisters a listener from this event manager.
     *
     * @param listener Listener to unregister
     */
    public void unregisterListener(@Nonnull Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners Listeners to unregister
     */
    public void unregisterListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::unregisterListener);
    }

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners Collection of listeners to unregister
     */
    public void unregisterListeners(@Nonnull Collection<Listener> listeners) {
        listeners.forEach(this::unregisterListener);
    }

    @Nonnull
    private final Queue<Event> eventQueue = new LinkedList<>();
    @Nonnull
    private final List<Listener> listeners = new ArrayList<>();
    @Nonnull
    private final SchedulerCore core = new SchedulerCore("EventManager-1", 0);

    /**
     * Default constructor.
     */
    public EventManager() {
        core.registerTask(delta -> {
            while (!eventQueue.isEmpty()) {
                final Event event = eventQueue.poll();
                final List<Handler> handlers = new ArrayList<>();

                if (event != null) {
                    listeners.forEach(l -> Arrays.stream(l.getClass().getDeclaredMethods()).forEach(m -> {
                        if (!m.isAnnotationPresent(EventHandler.class)) return;
                        if (m.getParameterCount() != 1) return;
                        if (!m.getParameters()[0].getType().isAssignableFrom(event.getClass())) return;

                        handlers.add(new Handler(l, m));
                    }));
                }

                handlers.sort(Comparator.comparing(h -> h.method().getAnnotation(EventHandler.class).priority()));

                handlers.forEach(h -> {
                    try {
                        h.method().invoke(h.listener, event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    /**
     * A transient data class for holding handler methods temporarily.
     *
     * @param listener Listener object
     * @param method   Method to call
     */
    private record Handler(
            @Nonnull Listener listener,
            @Nonnull Method method
    ) {}
}
