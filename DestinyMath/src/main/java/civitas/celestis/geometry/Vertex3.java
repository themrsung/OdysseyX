package civitas.celestis.geometry;

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
     * @param p1 Point 1
     * @param p2 Point 2
     * @param p3 Point 3
     */
    public Vertex3(@Nonnull Vector3 p1, @Nonnull Vector3 p2, @Nonnull Vector3 p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     * Creates a new vertex from another.
     *
     * @param other Vertex to copy
     */
    public Vertex3(@Nonnull Vertex3 other) {
        this.p1 = other.p1;
        this.p2 = other.p2;
        this.p3 = other.p3;
    }

    @Nonnull
    private final Vector3 p1;
    @Nonnull
    private final Vector3 p2;
    @Nonnull
    private final Vector3 p3;

    @Nonnull
    @Override
    public Vector3 p1() {
        return p1;
    }

    @Nonnull
    @Override
    public Vector3 p2() {
        return p2;
    }

    @Nonnull
    @Override
    public Vector3 p3() {
        return p3;
    }

    @Nonnull
    @Override
    public List<Vector3> points() {
        return List.of(p1, p2, p3);
    }

    @Nonnull
    @Override
    public Vector3 centroid() {
        return p1.add(p2).add(p3).divide(3);
    }

    @Override
    public Iterator<Vector3> iterator() {
        return points().iterator();
    }
}
