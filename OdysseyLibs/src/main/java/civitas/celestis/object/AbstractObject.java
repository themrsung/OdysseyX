package civitas.celestis.object;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>AbstractObject</h2>
 * <p>The default implementation of {@link BaseObject}.</p>
 */
public abstract class AbstractObject implements BaseObject {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location) {
        this(uniqueId, location, Rotation.NO_ROTATION);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param rotation Rotation of this object
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Rotation rotation) {
        this(uniqueId, location, Vector3.ZERO, rotation, Rotation.NO_ROTATION);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId     Unique identifier of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     */
    public AbstractObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Rotation rotation,
            @Nonnull Rotation rotationRate
    ) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private Vector3 location;
    @Nonnull
    private Vector3 acceleration;
    @Nonnull
    private Rotation rotation;
    @Nonnull
    private Rotation rotationRate;

    @Override
    public void tick(@Nonnull Duration delta) {
        // Convert delta to seconds
        final double seconds = delta.getMillis() / 1000d;

        // Apply acceleration
        move(acceleration.multiply(seconds));

        // Apply rate of rotation
        rotate(rotationRate.scale(seconds));
    }

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public Vector3 getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector3 getAcceleration() {
        return acceleration;
    }

    @Override
    @Nonnull
    public Rotation getRotation() {
        return rotation;
    }

    @Override
    @Nonnull
    public Rotation getRotationRate() {
        return rotationRate;
    }

    @Override
    public void setLocation(@Nonnull Vector3 location) {
        this.location = location;
    }

    @Override
    public void setAcceleration(@Nonnull Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setRotation(@Nonnull Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setRotation(@Nonnull Quaternion rq) {
        this.rotation = rq.rotation();
    }

    @Override
    public void setRotationRate(@Nonnull Rotation rotationRate) {
        this.rotationRate = rotationRate;
    }

    @Override
    public void setRotationRate(@Nonnull Quaternion rq) {
        this.rotationRate = rq.rotation();
    }

    @Override
    public void move(@Nonnull Vector3 amount) {
        this.location = location.add(amount);
    }

    @Override
    public void accelerate(@Nonnull Vector3 amount) {
        this.acceleration = acceleration.add(amount);
    }

    @Override
    public void rotate(@Nonnull Rotation r) {
        this.rotation = rotation.rotate(r);
    }

    @Override
    public void rotate(@Nonnull Quaternion rq) {
        this.rotation = rotation.rotate(rq);
    }

    @Override
    public void rotateRate(@Nonnull Rotation r) {
        this.rotationRate = rotationRate.rotate(r);
    }

    @Override
    public void rotateRate(@Nonnull Quaternion rq) {
        this.rotationRate = rotationRate.rotate(rq);
    }
}
