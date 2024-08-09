package Prototype;

public class Main {
    public static void main(String[] args) {
        try {
            Documento doc1 = new Documento("Contenido original");
            Documento doc2 = doc1.clone();

            doc2.setContenido("Contenido clonado");

            System.out.println(doc1);
            System.out.println(doc2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
