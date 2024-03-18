package AB1;

import codedraw.CodeDraw;

/**
 * This class represents vectors in a 3D vector space.
 */
public class Vector3 {

    //TODO: change modifiers.
    private double x;
    private double y;
    private double z;

    //TODO: define constructor.
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Returns the sum of this vector and vector 'v'.
     *
     * @param v another vector, v != null.
     * @return the sum of this vector and vector 'v'.
     */
    public Vector3 plus(Vector3 v) {
        //TODO: implement method.
        double sum_x = this.x + v.x;
        double sum_y = this.y + v.y;
        double sum_z = this.z + v.z;
        return new Vector3(sum_x, sum_y, sum_z);
    }

    /**
     * Returns the product of this vector and 'd'.
     *
     * @param d the coefficient for the product.
     * @return the product of this vector and 'd'.
     */
    public Vector3 times(double d) {
        //TODO: implement method.
        double mul_x = this.x * d;
        double mul_y = this.y * d;
        double mul_z = this.z * d;
        return new Vector3(mul_x, mul_y, mul_z);
    }

    /**
     * Returns the sum of this vector and -1*v.
     *
     * @param v another vector, v != null.
     * @return the sum of this vector and -1*v.
     */
    public Vector3 minus(Vector3 v) {
        //TODO: implement method.
        Vector3 new_vektor = v.times(-1);
        return this.plus(new_vektor);
    }

    /**
     * Returns the Euclidean distance of this vector to the specified vector 'v'.
     *
     * @param v another vector, v != null.
     * @return the Euclidean distance of this vector to the specified vector 'v'.
     */
    public double distanceTo(Vector3 v) {
        //TODO: implement method.
        Vector3 new_vektor = this.minus(v);
        return Math.sqrt(new_vektor.x * new_vektor.x + new_vektor.y * new_vektor.y + new_vektor.z * new_vektor.z);
    }

    /**
     * Returns the norm of this vector.
     *
     * @return the length (norm) of this vector.
     */
    public double length() {
        //TODO: implement method.
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Normalizes this vector: changes the length of this vector such that it becomes 1.
     * The direction of the vector is not affected.
     */
    public void normalize() {
        //TODO: implement method.
        double vektor_length = this.length();
        if (vektor_length > 0) {
            this.x /= vektor_length;
            this.y /= vektor_length;
            this.z /= vektor_length;
        }
    }

    /**
     * Draws a filled circle with a specified radius centered at the (x,y) coordinates of this vector
     * in the canvas associated with 'cd'. The z-coordinate is not used.
     *
     * @param cd     the CodeDraw object used for drawing.
     * @param radius the radius > 0.
     */
    public void drawAsFilledCircle(CodeDraw cd, double radius) {
        //TODO: implement method.
        double x = cd.getWidth() * (this.x + Simulation.SECTION_SIZE / 2) / Simulation.SECTION_SIZE;
        double y = cd.getWidth() * (this.y + Simulation.SECTION_SIZE / 2) / Simulation.SECTION_SIZE;
        radius = cd.getWidth() * radius / Simulation.SECTION_SIZE;
        cd.fillCircle(x, y, Math.max(radius, 1.5));
    }

    /**
     * Returns the coordinates of this vector in brackets as a string
     * in the form "[x, y, z]", e.g., "[1.48E11, 0.0, 0.0]".
     *
     * @return 'this' represented as a string.
     */
    public String toString() {
        //TODO: implement method.
        return "[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
}

