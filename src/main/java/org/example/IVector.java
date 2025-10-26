
public interface IVector {
    double abs();                      // długość wektora
    double cdot(IVector param);        // iloczyn skalarny
    double[] getComponents();          // zwraca współrzędne
}
