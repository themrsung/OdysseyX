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
     * @return {@link HandlerPriority}
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.NORMAL;

}
