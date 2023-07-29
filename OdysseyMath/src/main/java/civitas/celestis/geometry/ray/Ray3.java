package civitas.celestis.geometry.ray;

import civitas.celestis.geometry.vertex.Vertex3;
import civitas.celestis.math.Numbers3;
import civitas.celestis.math.vector.Vector3;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class Ray3 implements Ray<Vector3> {
    /**
     * Creates a new ray.
     *
     * @param origin    Origin of ray
     * @param direction Direction of ray
     */
    public Ray3(@Nonnull Vector3 origin, @Nonnull Vector3 direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    /**
     * Creates a new ray from an existing ray.
     *
     * @param other Ray to copy
     */
    public Ray3(@Nonnull Ray3 other) {
        this.origin = other.origin;
        this.direction = other.direction;
    }

    @Nonnull
    private final Vector3 origin;
    @Nonnull
    private final Vector3 direction;

    @Nonnull
    @Override
    public Vector3 origin() {
        return origin;
    }

    @Nonnull
    @Override
    public Vector3 direction() {
        return direction;
    }

    @Nonnull
    @Override
    public Vector3 destination(double t) {
        return origin.add(direction.multiply(t));
    }

    /**
     * Gets the reflection ray when collided with given surface.
     *
     * @param surface Surface to collide with
     * @return Reflection ray if the two objects intersect, {@code null} if not
     */
    @Nullable
    public Ray3 reflection(@Nonnull Vertex3 surface) {
        final Vector3 intersection = surface.intersection(this);
        if (intersection == null) return null;

        final Vector3 reflection = Numbers3.reflection(direction, surface.normal());
        return new Ray3(intersection, reflection);
    }
}
