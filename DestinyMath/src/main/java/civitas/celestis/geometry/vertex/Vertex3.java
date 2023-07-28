package civitas.celestis.geometry.vertex;

import civitas.celestis.math.vector.Vector3;

import javax.annotation.Nonnull;
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

    /**
     * Gets the surface normal of this vertex.
     * @return Surface normal
     */
    @Nonnull
    public Vector3 normal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    @Override
    public Iterator<Vector3> iterator() {
        return points().iterator();
    }
}
