<<<<<<< HEAD
// === Main.java ===
public class Main {
    public static void main(String[] args) {
        // Tworzenie wektorów 2D
        Vector2D v1 = new Vector2D(3, 4);
        Vector2D v2 = new Vector2D(1, 2);
        Vector2D v3 = new Vector2D(-2, 5);

        IVector[] vectors = {v1, v2, v3};

        // Wyświetlenie współrzędnych
        System.out.println("=== Wektory 2D (Kartezjańskie + Biegunowe) ===");
        for (Vector2D v : new Vector2D[]{v1, v2, v3}) {
            Polar2DAdapter polar = new Polar2DAdapter(v);
            System.out.printf("Wektor (x=%.2f, y=%.2f): |v|=%.2f, kąt=%.2f rad%n",
                    v.getX(), v.getY(), polar.abs(), polar.getAngle());
        }

        // Iloczyny skalarne
        System.out.println("\n=== Iloczyny skalarne ===");
        for (int i = 0; i < vectors.length; i++) {
            for (int j = i + 1; j < vectors.length; j++) {
                System.out.printf("v%d · v%d = %.2f%n", i+1, j+1, vectors[i].cdot(vectors[j]));
            }
        }

        // Iloczyny wektorowe (3D)
        System.out.println("\n=== Iloczyny wektorowe (3D) ===");
        for (int i = 0; i < vectors.length; i++) {
            for (int j = i + 1; j < vectors.length; j++) {
                Vector3DDecorator v3a = new Vector3DDecorator(vectors[i], 0);
                Vector3DDecorator v3b = new Vector3DDecorator(vectors[j], 0);
                Vector3DDecorator cross = v3a.cross(v3b);
                double[] c = cross.getComponents();
                System.out.printf("v%d × v%d = (%.2f, %.2f, %.2f)%n", i+1, j+1, c[0], c[1], c[2]);
            }
        }
    }
}
=======
package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}
>>>>>>> 3df5676f381b3e86d926941b98097cda92e8347a
