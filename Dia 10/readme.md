Los patrones creacionales son fundamentales en la programación orientada a objetos, ya que permiten manejar la creación de objetos de manera flexible y eficiente. Estos patrones definen formas de instanciar objetos, asegurando que el proceso de creación sea adecuado para el contexto en el que se utilizarán.

1. Patrones Creacionales en Java
Los patrones creacionales más comunes incluyen:

- Singleton
- Factory Method
- Abstract Factory
- Builder
- Prototype

### 1. **Singleton**

El patrón **Singleton** asegura que una clase tenga una única instancia y proporciona un punto de acceso global a esa instancia. Es útil cuando se necesita un único objeto que gestione recursos compartidos, como una conexión de base de datos.


### 2. **Factory Method**

El patrón **Factory Method** proporciona una interfaz para crear objetos en una superclase, pero permite a las subclases alterar el tipo de objetos que se crean. Es útil cuando las subclases necesitan cambiar el tipo de objetos que se instancian.


### 3. **Abstract Factory**

El patrón **Abstract Factory** proporciona una interfaz para crear familias de objetos relacionados o dependientes sin especificar sus clases concretas. Es útil cuando el sistema debe ser independiente de cómo se crean, componen y representan sus productos.


### 4. **Builder**

El patrón **Builder** se utiliza para construir un objeto complejo paso a paso. El patrón permite crear diferentes representaciones de un objeto utilizando el mismo código de construcción.


### 5. **Prototype**

El patrón **Prototype** se utiliza para crear nuevos objetos copiando instancias existentes, en lugar de crear nuevas instancias desde cero. Es útil cuando el costo de crear una nueva instancia es prohibitivo.
