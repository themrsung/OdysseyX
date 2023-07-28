package civitas.celestis.geometry;

import civitas.celestis.math.vector.Vector2;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>Vertex2</h2>
 * <p>A two-dimensional vertex.</p>
 */
@Immutable
public class Vertex2 implements Vertex<Vector2> {
    /**
     * Creates a new vertex.
     *
     * @param p1 Point 1
     * @param p2 Point 2
     * @param p3 Point 3
     */
    public Vertex2(@Nonnull Vector2 p1, @Nonnull Vector2 p2, @Nonnull Vector2 p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     * Creates a new vertex from another.
     *
     * @param other Vertex to copy
     */
    public Vertex2(@Nonnull Vertex2 other) {
        this.p1 = other.p1;
        this.p2 = other.p2;
        this.p3 = other.p3;
    }

    @Nonnull
    private final Vector2 p1;
    @Nonnull
    private final Vector2 p2;
    @Nonnull
    private final Vector2 p3;

    @Nonnull
    @Override
    public Vector2 p1() {
        return p1;
    }

    @Nonnull
    @Override
    public Vector2 p2() {
        return p2;
    }

    @Nonnull
    @Override
    public Vector2 p3() {
        return p3;
    }

    @Nonnull
    @Override
    public List<Vector2> points() {
        return List.of(p1, p2, p3);
    }

    @Nonnull
    @Override
    public Vector2 centroid() {
        return p1.add(p2).add(p3).divide(3);
    }

    @Override
    public Iterator<Vector2> iterator() {
        return points().iterator();
    }
}
