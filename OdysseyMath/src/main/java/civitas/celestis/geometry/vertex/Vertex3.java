package civitas.celestis.geometry.vertex;

import civitas.celestis.geometry.ray.Ray3;
import civitas.celestis.math.Numbers3;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>Vertex3</h2>
 * <p>A three-dimensional vertex.</p>
 */
@Immutable
public class Vertex3 implements Vertex<Vector3> {
    /**
     * Creates a new vertex.
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     */
    public Vertex3(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Creates a new vertex from another.
     *
     * @param other Vertex to copy
     */
    public Vertex3(@Nonnull Vertex3 other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    @Nonnull
    private final Vector3 a;
    @Nonnull
    private final Vector3 b;
    @Nonnull
    private final Vector3 c;

    @Nonnull
    @Override
    public Vector3 a() {
        return a;
    }

    @Nonnull
    @Override
    public Vector3 b() {
        return b;
    }

    @Nonnull
    @Override
    public Vector3 c() {
        return c;
    }

    @Nonnull
    @Override
    public List<Vector3> points() {
        return List.of(a, b, c);
    }

    @Nonnull
    @Override
    public Vector3 centroid() {
        return a.add(b).add(c).divide(3);
    }

    @Nonnull
    @Override
    public Vertex3 inflate(double s) {
        return new Vertex3(
                a.multiply(s),
                b.multiply(s),
                c.multiply(s)
        );
    }

    /**
     * Transforms this vertex to a relative coordinate system.
     *
     * @param origin Origin of new coordinate system
     * @param r      Global rotation
     * @return Transformed vertex
     */
    @Nonnull
    public Vertex3 transform(@Nonnull Vector3 origin, @Nonnull Rotation r) {
        return transform(origin, r.quaternion());
    }

    /**
     * Transforms this vertex to a relative coordinate system.
     *
     * @param origin Origin of new coordinate system
     * @param rq     Global rotation
     * @return Transformed vertex
     */
    @Nonnull
    public Vertex3 transform(@Nonnull Vector3 origin, @Nonnull Quaternion rq) {
        return new Vertex3(
                a.subtract(origin).rotate(rq),
                b.subtract(origin).rotate(rq),
                c.subtract(origin).rotate(rq)
        );
    }

    /**
     * Gets the surface normal of this vertex.
     *
     * @return Surface normal
     */
    @Nonnull
    public Vector3 normal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    /**
     * Gets the intersection between {@code this} and given ray.
     *
     * @param ray Ray to check
     * @return Intersection if found, {@code null} if not
     */
    @Nullable
    public Vector3 intersection(@Nonnull Ray3 ray) {
        if (!Numbers3.intersects(this, ray)) return null;

        final Vector3 n = normal();

        final double denominator = ray.direction().dot(n);
        if (denominator == 0) return null;

        final double length = (centroid().subtract(ray.origin()).dot(n)) / denominator;
        if (length < 0) return null;

        return ray.destination(length);
    }

    @Override
    public Iterator<Vector3> iterator() {
        return points().iterator();
    }
}
