package civitas.celestis.world;

import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.TangibleObject;
import civitas.celestis.util.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>TangibleWorld</h2>
 * <p>A world with physics and air resistance.</p>
 */
public interface TangibleWorld {
    /**
     * Gets a list of overlapping object pairs.
     *
     * @return Overlapping object pairs
     */
    @Nonnull
    List<Pair<TangibleObject>> getOverlaps();

    /**
     * Gets the gravity of this world.
     *
     * @return Gravity vector of this world
     */
    @Nonnull
    Vector3 getGravity();

    /**
     * Gets the air density of this world.
     *
     * @return Air density
     */
    @Nonnegative
    double getAirDensity();

    /**
     * Sets the gravity of this world.
     *
     * @param gravity Gravity vector to use
     */
    void setGravity(@Nonnull Vector3 gravity);

    /**
     * Sets the air density of this world.
     *
     * @param density Air density
     */
    void setAirDensity(@Nonnegative double density);
}
