package Builder;

public class Main {
    public static void main(String[] args) {
        Computadora computadora = new Computadora.Builder()
                .setCPU("Intel i7")
                .setRAM("16GB")
                .setAlmacenamiento("1TB SSD")
                .build();

        System.out.println(computadora);
    }
}
