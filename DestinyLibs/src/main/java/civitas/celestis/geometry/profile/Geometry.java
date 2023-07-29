package civitas.celestis.geometry.profile;

import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.object.TangibleObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Geometry</h2>
 * <p>Represents a relative profile of geometry of an object.</p>
 */
public interface Geometry {
    /**
     * Gets the volume of this geometry.
     *
     * @return Volume
     */
    @Nonnegative
    double volume();

    /**
     * Builds a solid in respect to given object.
     *
     * @param object Object to build solid of
     * @return Built solid
     */
    @Nonnull
    Solid build(@Nonnull TangibleObject object);
}
