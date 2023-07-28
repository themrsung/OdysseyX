package civitas.celestis.geometry.ray;

import civitas.celestis.math.vector.Vector;

import javax.annotation.Nonnull;

/**
 * <h2>Ray</h2>
 * <p>The superinterface for all rays.</p>
 */
public interface Ray<V extends Vector> {
    /**
     * Gets the origin of this ray.
     *
     * @return Origin
     */
    @Nonnull
    V origin();

    /**
     * Gets the direction of this ray.
     *
     * @return Direction
     */
    @Nonnull
    V direction();

    /**
     * Gets the destination of this ray.
     *
     * @param t Length of ray
     * @return Destination
     */
    @Nonnull
    V destination(double t);
}
