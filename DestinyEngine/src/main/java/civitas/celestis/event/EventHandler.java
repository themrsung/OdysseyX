package civitas.celestis.event;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h2>EventHandler</h2>
 * <p>
 * This annotation marks a method as a handler method.
 * Handler methods must be public, and have only one event parameter.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Gets the priority of this event handler.
     *
     * @return {@link Priority}
     */
    @Nonnull
    Priority priority() default Priority.NORMAL;

    /**
     * The priority of an event handler.
     */
    enum Priority {
        PREEMPTIVE,
        PRE_EARLY,
        EARLY,
        POST_EARLY,
        PRE_NORMAL,
        NORMAL,
        POST_NORMAL,
        PRE_LATE,
        LATE,
        POST_LATE,
        MONITOR
    }
}
