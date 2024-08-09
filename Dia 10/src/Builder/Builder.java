package Builder;

class Computadora {
    private String CPU;
    private String RAM;
    private String almacenamiento;

    public static class Builder {
        private String CPU;
        private String RAM;
        private String almacenamiento;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setAlmacenamiento(String almacenamiento) {
            this.almacenamiento = almacenamiento;
            return this;
        }

        public Computadora build() {
            Computadora computadora = new Computadora();
            computadora.CPU = this.CPU;
            computadora.RAM = this.RAM;
            computadora.almacenamiento = this.almacenamiento;
            return computadora;
        }
    }

    @Override
    public String toString() {
        return "Computadora [CPU=" + CPU + ", RAM=" + RAM + ", Almacenamiento=" + almacenamiento + "]";
    }
}
