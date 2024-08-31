# S.O.L.I.D

Los principios S.O.L.I.D son un conjunto de directrices para diseñar software de manera que sea más fácil de mantener, extender y comprender. Estos principios fueron popularizados por Robert C. Martin, también conocido como "Uncle Bob", y son fundamentales en el desarrollo orientado a objetos. A continuación, se describe cada uno de los cinco principios:

## 1. Single Responsibility Principle (SRP) - Principio de responsabilidad única

Este principio establece que **una clase debe tener una, y solo una, razón para cambiar**. Esto significa que cada clase debería tener una única responsabilidad o propósito en el sistema. Si una clase tiene múltiples responsabilidades, se incrementa la posibilidad de errores cuando se realizan cambios, ya que un cambio en una responsabilidad puede afectar a otras.

**Ejemplo:** Si tienes una clase `Usuario`, esta debería manejar solo la lógica relacionada con la gestión de usuarios. Si la clase también gestiona la conexión a la base de datos, sería mejor dividir esta funcionalidad en dos clases: una para `Usuario` y otra para la `ConexiónBaseDatos`.

## 2. Open/Closed Principle (OCP) - Principio de abierto/cerrado

Este principio sostiene que **el software debe estar abierto para extensión, pero cerrado para modificación**. Esto significa que el comportamiento de una clase debería poder extenderse sin modificar su código fuente. La idea es evitar cambios directos en clases existentes, lo que puede introducir errores en un sistema que ya funciona, y en su lugar, permitir la adición de nuevas funcionalidades a través de la herencia o la composición.

**Ejemplo:** En lugar de modificar una clase `Calculadora` para agregar una nueva operación, se podría crear una nueva clase que extienda `Calculadora` e implemente la operación adicional. De esta manera, el código existente no se modifica y la nueva funcionalidad se añade de forma segura.

## 3. Liskov Substitution Principle (LSP) - Principio de sustitución de Liskov

El principio de sustitución de Liskov establece que **una subclase debe ser reemplazable por su superclase sin alterar el correcto funcionamiento del programa**. En otras palabras, los objetos de una subclase deben poder usarse en lugar de objetos de la superclase sin que el código que los utiliza se vea afectado.

**Ejemplo:** Si tienes una clase `Vehículo` con un método `mover()`, y una subclase `Coche`, entonces el objeto de `Coche` debe poder sustituir al objeto de `Vehículo` sin que el comportamiento del método `mover()` cambie inesperadamente.

## 4. Interface Segregation Principle (ISP) - Principio de segregación de interfaces

Este principio sugiere que **los clientes no deben verse obligados a depender de interfaces que no utilizan**. En lugar de tener una única interfaz grande y general, es mejor dividirla en interfaces más pequeñas y específicas. Esto evita que las clases que implementan una interfaz tengan que definir métodos que no necesitan.

**Ejemplo:** Si tienes una interfaz `Operaciones` que incluye métodos como `sumar()`, `restar()`, `multiplicar()` y `dividir()`, pero una clase solo necesita implementar `sumar()` y `restar()`, entonces deberías dividir `Operaciones` en interfaces más específicas como `OperacionesSumaResta` y `OperacionesMultiplicacionDivision`.

## 5. Dependency Inversion Principle (DIP) - Principio de inversión de dependencias

El principio de inversión de dependencias indica que **los módulos de alto nivel no deben depender de módulos de bajo nivel; ambos deben depender de abstracciones**. Además, **las abstracciones no deben depender de los detalles; los detalles deben depender de las abstracciones**. Este principio promueve la creación de sistemas desacoplados, donde los detalles de implementación pueden cambiar sin afectar a la estructura general del código.

**Ejemplo:** En lugar de que una clase `Pedido` dependa directamente de una clase `BaseDatos`, debería depender de una interfaz `BaseDatosInterface`. Esto permite que `Pedido` trabaje con cualquier implementación de `BaseDatosInterface`, facilitando cambios o sustituciones de la clase `BaseDatos` sin modificar `Pedido`.
