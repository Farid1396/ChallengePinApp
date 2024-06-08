#language: es
@Escenario:E2E
Característica: Ejecución E2E

  Escenario: Logeo exitoso
    Dado el usuario se encuentra en Chrome
    Cuando el usuario hace click en el campo de busqueda
    Y el usuario ingresa 'https://practicetestautomation.com/practice-test-login/' en la campo barra de url
    Y el usuario hace click en la opción del link
    Entonces el usuario observa que el título de la página principal es visible
    Cuando el usuario hace un scrolleo
    Y el usuario ingresa 'student' en el campo username
    Y el usuario ingresa 'Password123' en el campo password
    Y el usuario hace click en el botón submit
    Entonces el usuario observa que el título logeo correcto es visible
    Y el usuario observa que el botón logout es visible

  Escenario: Usuario incorrecto
    Dado el usuario se encuentra en Chrome
    Cuando el usuario hace click en el campo de busqueda
    Y el usuario ingresa 'https://practicetestautomation.com/practice-test-login/' en la campo barra de url
    Y el usuario hace click en la opción del link
    Entonces el usuario observa que el título de la página principal es visible
    Cuando el usuario hace un scrolleo
    Y el usuario ingresa 'incorrectUser' en el campo username
    Y el usuario ingresa 'Password123' en el campo password
    Y el usuario hace click en el botón submit
    Entonces el usuario observa que el título logeo correcto es visible

  Escenario: Contraseña incorrecta
    Dado el usuario se encuentra en Chrome
    Cuando el usuario hace click en el campo de busqueda
    Y el usuario ingresa 'https://practicetestautomation.com/practice-test-login/' en la campo barra de url
    Y el usuario hace click en la opción del link
    Entonces el usuario observa que el título de la página principal es visible
    Cuando el usuario hace un scrolleo
    Y el usuario ingresa 'student' en el campo username
    Y el usuario ingresa 'incorrectPassword' en el campo password
    Y el usuario hace click en el botón submit
    Entonces el usuario observa que el mensaje de contraseña incorrecta es visible
