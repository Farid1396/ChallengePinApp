# **Challenge**
La finalidad de este proyecto es automatizar el front end de la aplicacion PinApp.dev,
utilizando Serenity BDD, Cucumber4 y el patrón Screenplay basado en el comportamiento de los actores.

## Instalaciones y configuraciones necesarias
* Asegurarse de tener correctamente instalados Java 8 y Maven 3.6.3.
* Al escribir los comandos: java -version y mvn -v se deberían ver las versiones correspondientes.

## Ubicando nuestro proyecto
* Dirigirse al directorio raíz del proyecto a través de la consola o mediante la interfaz gráfica y luego abrir la consola en dicha ubicación

## Ejecutando nuestra suite
Se puede ejecutar la Suite de dos maneras
1. Para correr la suite de manera completa, mediante el siguiente comando `mvn clean verify`
2. Para correr una historia de usuario o caso de prueba especifico se deberá agregar `"-Dcucumber.options=--tags '@nuestroTag'"
   * Reemplazar @nuestroTag por el tag que corresponda a al servicio, método, historia o test case.
      * Historia: @US:CAD-XXXX
      * TC: @TC:CAD-XXXX
   * Se puede utilizar combinaciones de tags o considerar los que no tienen un tag especifico mediante `and, or y not`
      * @TC:CAD-XXXX or @TC:CAD-XXXX
      * @US:CAD-XXXX and not @UnderReview
3. De manera opcional, se puede configurar el archivo serenity.properties, ubicado en la raiz del proyecto, para que se corran las pruebas de manera headless (sin abrir el browser), cambiando el property firefox.headless = true.

## Visualizando el reporte
* Al finalizar la ejecución, se puede observar el reporte generado ejecutando el comando `start target/site/serenity/index.html`
