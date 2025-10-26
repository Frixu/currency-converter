
public class Polar2DAdapter implements IPolar2D, IVector {
    private Vector2D srcVector;

    public Polar2DAdapter(Vector2D v) {
        this.srcVector = v;
    }

    @Override
    public double abs() {
        return srcVector.abs();
    }

    @Override
    public double getAngle() {
        double[] comp = srcVector.getComponents();
        return Math.atan2(comp[1], comp[0]); // zwraca kąt w radianach
    }

    @Override
    public double cdot(IVector param) {
        return srcVector.cdot(param);
    }

    @Override
    public double[] getComponents() {
        return srcVector.getComponents();
    }
}
