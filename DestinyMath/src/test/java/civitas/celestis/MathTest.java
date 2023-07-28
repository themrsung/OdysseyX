package civitas.celestis;

import civitas.celestis.math.Numbers2;
import civitas.celestis.math.matrix.Matrix3;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;

public class MathTest {
    public static void main(String[] args) {
        final Vector3 v3 = new Vector3(0, 0, 100);
        System.out.println(v3.rotate(new Rotation(Vector3.POSITIVE_Y, Math.toRadians(90))));

        final Matrix3 m1 = new Matrix3();
        final Matrix3 m2 = new Matrix3();

        m1.fill(1);
        m2.fill(3);

        System.out.println(Numbers2.multiply(m1, Vector3.NEGATIVE_X));
    }
}
