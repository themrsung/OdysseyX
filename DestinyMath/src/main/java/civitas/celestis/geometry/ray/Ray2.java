package civitas.celestis.geometry.ray;

import civitas.celestis.math.vector.Vector2;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>Ray2</h2>
 * <p>A two-dimensional ray.</p>
 */
@Immutable
public class Ray2 implements Ray<Vector2> {
    /**
     * Creates a new ray.
     *
     * @param origin    Origin of ray
     * @param direction Direction of ray
     */
    public Ray2(@Nonnull Vector2 origin, @Nonnull Vector2 direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    /**
     * Creates a new ray from another.
     *
     * @param other Ray to copy
     */
    public Ray2(@Nonnull Ray2 other) {
        this.origin = other.origin;
        this.direction = other.direction;
    }

    @Nonnull
    private final Vector2 origin;
    @Nonnull
    private final Vector2 direction;

    @Nonnull
    @Override
    public Vector2 origin() {
        return origin;
    }

    @Nonnull
    @Override
    public Vector2 direction() {
        return direction;
    }

    @Nonnull
    @Override
    public Vector2 destination(double t) {
        return origin.add(direction.multiply(t));
    }
}
