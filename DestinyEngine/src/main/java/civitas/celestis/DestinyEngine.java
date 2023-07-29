package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.task.lifecycle.Scheduler;
import civitas.celestis.world.lifecycle.WorldManager;

import javax.annotation.Nonnull;

/**
 * <h2>DestinyEngine</h2>
 * <p>The main class of Destiny.</p>
 */
public final class DestinyEngine {
    /**
     * Starts the engine.
     */
    public static void start() {
        // Start modules
        eventManager.start();
        scheduler.start();
        worldManager.start();
    }

    /**
     * Stops the engine.
     */
    public static void stop() {
        // Stop modules
        scheduler.stop();
        eventManager.stop();
        worldManager.stop();
    }

    //
    // Modules
    //

    /**
     * Gets the scheduler instance.
     *
     * @return {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Gets the event manager instance.
     *
     * @return {@link EventManager}
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the world manager instance.
     *
     * @return {@link WorldManager}
     */
    @Nonnull
    public static WorldManager getWorldManager() {
        return worldManager;
    }

    private static final Scheduler scheduler = new Scheduler();
    private static final EventManager eventManager = new EventManager();
    private static final WorldManager worldManager = new WorldManager();
}