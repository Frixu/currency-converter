// === Vector3DDecorator.java ===
public class Vector3DDecorator implements IVector {
    private IVector srcVector;
    private double z;

    public Vector3DDecorator(IVector srcVector, double z) {
        this.srcVector = srcVector;
        this.z = z;
    }

    @Override
    public double abs() {
        double[] c = srcVector.getComponents();
        return Math.sqrt(c[0]*c[0] + c[1]*c[1] + z*z);
    }

    @Override
    public double cdot(IVector param) {
        double[] c1 = this.getComponents();
        double[] c2 = param.getComponents();
        return c1[0]*c2[0] + c1[1]*c2[1] + c1[2]*c2[2];
    }

    @Override
    public double[] getComponents() {
        double[] base = srcVector.getComponents();
        return new double[]{base[0], base[1], z};
    }

    public Vector3DDecorator cross(IVector param) {
        double[] a = this.getComponents();
        double[] b = param.getComponents();
        double cx = a[1]*b[2] - a[2]*b[1];
        double cy = a[2]*b[0] - a[0]*b[2];
        double cz = a[0]*b[1] - a[1]*b[0];
        return new Vector3DDecorator(new Vector2D(cx, cy), cz);
    }

    public IVector getSrcV() {
        return srcVector;
    }
}
