// === Vector2D.java ===
public class Vector2D implements IVector {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double abs() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public double cdot(IVector param) {
        double[] comp = param.getComponents();
        return this.x * comp[0] + this.y * comp[1];
    }

    @Override
    public double[] getComponents() {
        return new double[]{x, y};
    }

    public double getX() { return x; }
    public double getY() { return y; }
}
