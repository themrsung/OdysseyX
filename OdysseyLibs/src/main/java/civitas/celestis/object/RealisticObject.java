package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.geometry.vertex.Vertex3;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * <h2>RealisticObject</h2>
 * <p>The default implementation of {@link TangibleObject}.</p>
 */
public class RealisticObject extends AbstractObject implements TangibleObject {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param mass     Mass of this object
     * @param geometry Geometric profile of this object
     */
    public RealisticObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnegative double mass,
            @Nonnull Geometry geometry
    ) {
        super(uniqueId, location);
        this.mass = mass;
        this.geometry = geometry;
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param rotation Rotation of this object
     * @param mass     Mass of this object
     * @param geometry Geometric profile of this object
     */
    public RealisticObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Rotation rotation,
            @Nonnegative double mass,
            @Nonnull Geometry geometry
    ) {
        super(uniqueId, location, rotation);
        this.mass = mass;
        this.geometry = geometry;
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId     Unique identifier of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     * @param mass         Mass of this object
     * @param geometry     Geometric profile of this object
     */
    public RealisticObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Rotation rotation,
            @Nonnull Rotation rotationRate,
            @Nonnegative double mass,
            @Nonnull Geometry geometry
    ) {
        super(uniqueId, location, acceleration, rotation, rotationRate);
        this.mass = mass;
        this.geometry = geometry;
    }

    @Nonnegative
    private double mass;
    @Nonnull
    private Geometry geometry;

    @Override
    @Nonnegative
    public double getMass() {
        return mass;
    }

    @Override
    @Nonnull
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    @Nonnegative
    public double getVolume() {
        return geometry.volume();
    }

    @Override
    @Nonnegative
    public double getDensity() {
        final double v = geometry.volume();
        if (v == 0) return 0;

        return mass / v;
    }

    @Nonnull
    @Override
    public Solid getSolid() {
        return geometry.build(this);
    }

    @Override
    public boolean overlaps(@Nonnull Solid solid) {
        return getSolid().overlaps(solid);
    }

    @Override
    public boolean overlaps(@Nonnull TangibleObject other) {
        return getSolid().overlaps(other.getSolid());
    }

    @Nonnull
    @Override
    public List<Vertex3> getVertices() {
        return getSolid().vertices();
    }

    @Override
    public double getDragCoefficient() {
        return getSolid().dragCoefficient(getAcceleration().negate());
    }

    @Override
    public double getCrossSection() {
        return getSolid().dragCoefficient(getAcceleration().negate());
    }

    @Override
    public void setMass(@Nonnegative double mass) {
        this.mass = mass;
    }

    @Override
    public void setGeometry(@Nonnull Geometry geometry) {
        this.geometry = geometry;
    }
}
