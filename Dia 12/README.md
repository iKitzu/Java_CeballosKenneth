# Patrones Estructurales

Los patrones estructurales se centran en cómo organizar y componer las clases y objetos para formar estructuras más grandes y complejas. Estos patrones ayudan a crear sistemas más flexibles y fáciles de mantener al definir cómo deben relacionarse las entidades. A continuación, se presentan algunos de los patrones estructurales más comunes en Java:

## 1. Patrón Adapter (Adaptador)

### Descripción

El patrón Adapter convierte la interfaz de una clase en otra interfaz que el cliente espera. Esto permite que clases con interfaces incompatibles trabajen juntas sin necesidad de modificar el código fuente original de las clases.

### Ejemplo

Imagina que tienes un sistema antiguo que utiliza una API de comunicación con una interfaz específica, pero necesitas integrarlo con una nueva API que tiene una interfaz diferente. Puedes crear un adaptador que traduzca las llamadas de la nueva API a la interfaz esperada por el sistema antiguo.

## 2. Patrón Bridge (Puente)

### Descripción

El patrón Bridge desvincula una abstracción de su implementación, permitiendo que ambas puedan variar de forma independiente. Esto es útil cuando las abstracciones y sus implementaciones evolucionan de manera separada.

### Ejemplo

Considera un control remoto universal que debe controlar diferentes tipos de dispositivos como televisores o reproductores de DVD. El patrón Bridge puede separar la lógica del control remoto (abstracción) de la implementación específica de cada dispositivo.

## 3. Patrón Composite (Composición)

### Descripción

El patrón Composite permite componer objetos en estructuras de árbol para representar jerarquías parte-todo. Esto facilita tratar tanto a los objetos individuales como a las composiciones de objetos de manera uniforme.

### Ejemplo

Un menú de navegación puede tener elementos de menú que son a su vez otros submenús. Con el patrón Composite, puedes tratar tanto los elementos de menú individuales como los submenús de la misma manera.

## 4. Patrón Decorator (Decorador)

### Descripción

El patrón Decorator permite añadir funcionalidades adicionales a un objeto de manera dinámica. Proporciona una alternativa flexible a la subclasificación para extender la funcionalidad de los objetos.

### Ejemplo

Supón que tienes un componente gráfico como un cuadro de texto y deseas añadir diferentes estilos como bordes o colores sin cambiar el componente original. Puedes usar el patrón Decorator para envolver el componente original y añadirle características adicionales.

## 5. Patrón Facade (Fachada)

### Descripción

El patrón Facade proporciona una interfaz simplificada a un conjunto de interfaces en un subsistema. Esto hace que el subsistema sea más fácil de usar al ofrecer una interfaz de nivel superior que oculta la complejidad interna.

### Ejemplo

Imagina un sistema de gestión de pedidos que requiere interacción con múltiples subsistemas como inventario, facturación y envío. Un patrón Facade puede proporcionar una interfaz única y sencilla para realizar operaciones de pedidos.

## 6. Patrón Flyweight (Peso Ligero)

### Descripción

El patrón Flyweight minimiza el uso de memoria al compartir la mayor cantidad posible de datos entre objetos similares. Es útil cuando se necesitan crear un gran número de objetos, pero muchos de ellos tienen un estado interno compartido.

### Ejemplo

En un editor de texto, cada carácter puede ser representado por un objeto Flyweight en lugar de crear un objeto separado para cada instancia de carácter.

## 7. Patrón Proxy (Proxy)

### Descripción

El patrón Proxy proporciona un sustituto o marcador de posición para otro objeto para controlar el acceso a este. Los proxies pueden ser utilizados para gestionar el acceso a objetos costosos o para realizar otras funciones como la carga diferida.

### Ejemplo

Puedes usar un proxy para controlar el acceso a un recurso remoto o para cargar de manera diferida un objeto costoso.