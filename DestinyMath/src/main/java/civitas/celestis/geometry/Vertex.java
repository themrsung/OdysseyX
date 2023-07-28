package civitas.celestis.geometry;

import civitas.celestis.math.vector.Vector;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Vertex</h2>
 * <p>The superinterface for all vertices.</p>
 *
 * @param <V> Type of vector to use as coordinates
 */
public interface Vertex<V extends Vector> extends Iterable<V> {
    /**
     * Gets the first point of this vertex.
     *
     * @return Point 1
     */
    @Nonnull
    V p1();

    /**
     * Gets the second point of this vertex.
     *
     * @return Point 2
     */
    @Nonnull
    V p2();

    /**
     * Gets the third point of this vertex.
     *
     * @return Point 3
     */
    @Nonnull
    V p3();

    /**
     * Gets a list of points.
     *
     * @return List of points
     */
    @Nonnull
    List<V> points();

    /**
     * Gets the centroid of this vertex.
     *
     * @return Centroid
     */
    @Nonnull
    V centroid();
}
