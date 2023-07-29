package civitas.celestis.geometry.solid;

import civitas.celestis.geometry.vertex.Vertex3;
import civitas.celestis.math.vector.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Solid</h2>
 * <p>Represents a discrete solid.</p>
 */
public interface Solid {
    /**
     * Gets the geometric centroid of this solid.
     *
     * @return Geometric centroid
     */
    @Nonnull
    Vector3 centroid();

    /**
     * Gets a list of corners of this solid.
     *
     * @return List of corners
     */
    @Nonnull
    List<Vector3> corners();

    /**
     * Gets the coefficient of drag when viewed from given direction.
     *
     * @param direction Direction to view this solid from
     * @return Coefficient of drag
     */
    @Nonnegative
    double dragCoefficient(@Nonnull Vector3 direction);

    /**
     * Gets the cross-section of this solid when viewed from given direction.
     *
     * @param direction Direction to view this solid from
     * @return Cross-section
     */
    @Nonnegative
    double crossSection(@Nonnull Vector3 direction);

    /**
     * Checks if this solid contains given point.
     *
     * @param point Point to check
     * @return {@code true} if given point is within the bounds of {@code this}
     */
    boolean contains(@Nonnull Vector3 point);

    /**
     * Checks if this solid overlaps another.
     *
     * @param other Solid to check
     * @return {@code true} if there is at least one point which is contained within both solids
     */
    boolean overlaps(@Nonnull Solid other);

    /**
     * Gets the volume of this solid.
     * @return Volume
     */
    @Nonnegative
    double volume();

    /**
     * Gets the surface area of this solid.
     * @return Surface area
     */
    @Nonnegative
    double surfaceArea();

    /**
     * Gets a list of vertices to render.
     *
     * @return List of vertices
     */
    @Nonnull
    List<Vertex3> vertices();
}
