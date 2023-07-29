package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.geometry.solid.Solid;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public interface TangibleObject extends BaseObject {
    /**
     * Gets the mass of this object.
     *
     * @return Mass
     */
    @Nonnegative
    double getMass();

    /**
     * Gets the geometric profile of this object.
     *
     * @return Geometric profile
     */
    @Nonnull
    Geometry getGeometry();

    /**
     * Gets the volume of this object.
     *
     * @return Volume
     */
    @Nonnegative
    double getVolume();

    /**
     * Gets the density of this object.
     *
     * @return Density
     */
    @Nonnegative
    double getDensity();

    /**
     * Gets the discrete solid of this object.
     *
     * @return Discrete solid
     */
    @Nonnull
    Solid getSolid();

    /**
     * Gets the current coefficient of drag of this object.
     *
     * @return Coefficient of drag
     */
    @Nonnegative
    double getDragCoefficient();

    /**
     * Gets the current cross-section of this object.
     *
     * @return Cross-section
     */
    @Nonnegative
    double getCrossSection();

    /**
     * Sets the mass of this object.
     *
     * @param mass Mass
     */
    void setMass(@Nonnegative double mass);

    /**
     * Sets the geometric profile of this object.
     *
     * @param geometry Geometric profile
     */
    void setGeometry(@Nonnull Geometry geometry);
}
