package civitas.celestis.world.lifecycle;

import civitas.celestis.task.lifecycle.SchedulerCore;
import civitas.celestis.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <h2>WorldManager</h2>
 * <p>Handles the lifecycle of worlds.</p>
 */
public class WorldManager {
    /**
     * Starts ticking worlds.
     */
    public void start() {
        core.start();
    }

    /**
     * Stops ticking worlds.
     */
    public void stop() {
        core.stop();
    }

    /**
     * Gets a list of all worlds in this manager.
     *
     * @return List of worlds
     */
    @Nonnull
    public List<World> getWorlds() {
        return new ArrayList<>(worlds);
    }

    /**
     * Gets a world by unique identifier.
     *
     * @param uniqueId Unique identifier of world
     * @return World of unique identifier
     * @throws NullPointerException When a world of unique identifier cannot be found
     */
    @Nonnull
    public World getWorld(@Nonnull UUID uniqueId) throws NullPointerException {
        for (World world : getWorlds()) {
            if (world.getUniqueId().equals(uniqueId)) return world;
        }

        throw new NullPointerException("World of unique identifier " + uniqueId + " cannot be found.");
    }

    /**
     * Gets a world by name.
     *
     * @param name Name of world
     * @return World of name if found, {@code null} if not
     */
    @Nullable
    public World getWorld(@Nonnull String name) {
        for (World world : getWorlds()) {
            if (world.getName().equals(name)) return world;
        }

        return null;
    }

    /**
     * Adds a world to this manager.
     *
     * @param world World to add
     */
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
    }

    /**
     * Removes a world from this manager.
     *
     * @param world World to remove
     */
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
    }

    private final List<World> worlds = new ArrayList<>();
    private final SchedulerCore core = new SchedulerCore("WorldManager-1", 0);

    /**
     * Default constructor.
     */
    public WorldManager() {
        core.registerTask(delta -> getWorlds().forEach(w -> w.tick(delta)));
    }
}
