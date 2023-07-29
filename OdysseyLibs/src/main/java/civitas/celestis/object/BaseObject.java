package civitas.celestis.object;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>BaseObject</h2>
 * <p>A basic object.</p>
 */
public interface BaseObject {
    /**
     * Gets the unique identifier of this object.
     *
     * @return Unique identifier
     */
    @Nonnull
    UUID getUniqueId();

    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);

    /**
     * Gets the location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector3 getLocation();

    /**
     * Gets the acceleration of this object.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector3 getAcceleration();

    /**
     * Gets the rotation of this object.
     *
     * @return Rotation
     */
    @Nonnull
    Rotation getRotation();

    /**
     * Gets the rate of rotation of this object.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Rotation getRotationRate();

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector3 location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector3 acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Rotation rotation);

    /**
     * Sets the rotation of this object.
     *
     * @param rq Rotation quaternion
     */
    void setRotation(@Nonnull Quaternion rq);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rotation Rate of rotation
     */
    void setRotationRate(@Nonnull Rotation rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rq Rate of rotation in quaternion notation
     */
    void setRotationRate(@Nonnull Quaternion rq);

    /**
     * Moves this object.
     *
     * @param amount Amount to move
     */
    void move(@Nonnull Vector3 amount);

    /**
     * Accelerates this object.
     *
     * @param amount Amount of acceleration to apply
     */
    void accelerate(@Nonnull Vector3 amount);

    /**
     * Rotates this object.
     *
     * @param r Rotation to apply
     */
    void rotate(@Nonnull Rotation r);

    /**
     * Rotates this object.
     *
     * @param rq Rotation quaternion to apply
     */
    void rotate(@Nonnull Quaternion rq);

    /**
     * Changes the rate of rotation of this object.
     *
     * @param r Rate of rotation to apply
     */
    void rotateRate(@Nonnull Rotation r);

    /**
     * Changes the rate of rotation of this object.
     *
     * @param rq Rotation quaternion to apply
     */
    void rotateRate(@Nonnull Quaternion rq);
}
