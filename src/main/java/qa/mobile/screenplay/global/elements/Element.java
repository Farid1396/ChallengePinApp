package qa.mobile.screenplay.global.elements;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Normalizer;

import static net.serenitybdd.screenplay.targets.Target.the;

/**
 * Clase que gestiona las páginas y los elementos asociados a la página, utilizadas en el proyecto
 * Tiene 2 métodos públicos: get, para los elementos y getPage para las paginas, ambos reciben un string con el nombre
 *
 * @author Federico Pflüger
 * @version 2.0
 * @since 11/12/2020
 */
public class Element {

    /**
     * Método para obtener un elemento a partir del nombre del mismo, trabaja con 4 nomenclaturas
     * 1 Ej: botón guardar, busca el elemento GUARDAR dentro de la clase Botón.
     * 2 Ej: botón 'guardar', busca el elemento BOTON dentro de la clase ElementConstructor y le envía el String "guardar" para construirlo
     * 3 Ej: botón 'guardar' de la sección, busca el elemento BOTON_DE_LA_SECCION dentro de la clase ElementConstructor y le envía el String "guardar" para construirlo
     * 4 Ej: botón 'guardar' de la sección 'actualizar', busca el elemento BOTON_DE_LA_SECCION dentro de la clase ElementConstructor2 y le envía el String "guardar" y el string "actualizar" para construirlo
     * 5 Ej: botón 'guardar' de la sección 'actualizar' con los datos completos, busca el elemento BOTON_DE_LA_SECCION_CON_LOS_DATOS_COMPLETOS dentro de la clase ElementConstructor2 y le envía el String "guardar" y el string "actualizar" para construirlo
     * (En todos los casos se Normalizan los nombres de los elementos y no modifican los valores de los string que se envían)
     *
     * @param element nombre del elemento solicitado
     * @return Target del elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    public static Target get(@NotNull String element) {
        if (element.startsWith("!")) {
            //En caso de que no contenga "'" sigue normalmente
            if (!element.contains("'")) {
                String elemWithoutExclamation = element.substring(1);
                String xpath = ElementGroup.getXPathElement(elemWithoutExclamation);
                Target targetElem = null;
                if (xpath != null) {
                    targetElem = the(elemWithoutExclamation).located(By.xpath(xpath));
                }
                return targetElem;
            } else {
                //Sino se tiene que procesar los parametros
                String elemWithoutExclamation = element.substring(1);
                String xpath = ElementGroup.getXPathElementWithQuotes(elemWithoutExclamation);
                Target targetElem = null;
                if (xpath != null) {
                    targetElem = the(elemWithoutExclamation).located(By.xpath(xpath));
                }
                return targetElem;
            }
        } else if (element.startsWith("@")) {
            String elemWithoutExclamation = element.substring(1);
            String css = ElementGroup.getXPathElement(elemWithoutExclamation);
            Target targetElem = null;
            if (css != null) {
                targetElem = the(elemWithoutExclamation).located(By.cssSelector(css));
            }
            return targetElem;
        } else {
            return (element.contains("'")) ? getElementConstructor(element) : getElement(element);
        }
    }

    /**
     * Método privado para obtener un elemento a partir del nombre del mismo, caso 1
     *
     * @param element nombre del elemento solicitado
     * @return Target del elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Target getElement(String element) {
        element = normalice(element);
        String className = element.split(" ")[0];
        return getTarget(getClassForName(className), getElementName(element, className));
    }

    /**
     * Método privado para obtener un elemento a partir del nombre del mismo, caso 2 y 3 (inicio de 4 y 5)
     *
     * @param element nombre del elemento solicitado
     * @return Target del elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Target getElementConstructor(@NotNull String element) {
        String name = element.substring(element.indexOf("'") + 1, element.indexOf("'", element.indexOf("'") + 1));
        String e = element.replaceFirst(" '" + name + "'", "");
        element = (element.equals(e)) ? element.replace(" '" + name + "'", "") : e;
        return (element.contains("'")) ? getElementConstructor(element, name) : getTarget(getClassForName("ElementConstructor"), getElementName(normalice(element), ""), name);
    }

    /**
     * Método privado para obtener un elemento a partir del nombre del mismo, caso 4 y 5
     *
     * @param element nombre del elemento solicitado
     * @return Target del elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Target getElementConstructor(@NotNull String element, String name) {
        String section = element.substring(element.indexOf("'") + 1, element.indexOf("'", element.indexOf("'") + 1));
        element = normalice(element.replace(" '" + section + "'", ""));
        return getTarget(getClassForName("ElementConstructor2"), getElementName(element, ""), name, section);
    }

    /**
     * Método para obtener una página a partir del nombre de la misma
     * Ej: pantalla Login / página Login, descarta la primera palabra y busca la clase Login (genera el formato CamelCase para el nombre de la clase)
     *
     * @param name nombre de la página solicitada
     * @return clase que extiende de PageObject asociada a la página solicitada
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    public static Class<? extends PageObject> getPage(String name) {
        name = normalice(name);
        name = getPageClassName(name);
        return getPageClass(name);
    }

    /**
     * Método privado para obtener el nombre completo de la clase incluyendo el paquete de la misma (para las páginas solicitadas)
     * Ej: Descarta la primera palabra y genera el formato CamelCase con las siguientes palabras
     *
     * @param name nombre de la página solicitada
     * @return nombre completo de la clase solicitada
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    @NotNull
    private static String getPageClassName(String name) {
        StringBuilder camelCaseName = new StringBuilder();

        //eliminar la primera palabra (pantalla / pagina)
        name = name.replace(name.split(" ")[0] + " ", "");

        for (String s : name.split(" ")) {
            camelCaseName.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase());
        }

        return Element.class.getName().replace("Element", "pages.") + camelCaseName;
    }

    /**
     * Método privado para obtener la clase de la página solicitada en función del nombre completo de la misma
     *
     * @param className nombre completo de la página solicitada
     * @return clase asociada a la página solicitada
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Class<? extends PageObject> getPageClass(String className) {
        Class<? extends PageObject> pageClass;
        try {
            pageClass = (Class<? extends PageObject>) Class.forName(className);
        } catch (ClassNotFoundException x) {
            throw new RuntimeException("No se encontró la clase: " + className + " para generar el objeto");
        } catch (Exception x) {
            throw new RuntimeException("La clase: " + className + " no extiende de pageObject");
        }
        return pageClass;
    }

    /**
     * Método privado para construir el nombre del elemento a solicitar reemplazando los espacios y símbolos con "_"
     * y pasa el nombre a mayúsculas
     *
     * @param element   nombre completo del elemento solicitado
     * @param className nombre de la clase asociada al elemento solicitado
     * @return el nombre del elemento solicitado con la estructura de nombres a utilizar
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    @NotNull
    private static String getElementName(String element, String className) {
        element = (!className.equals("")) ? element.replace(className + " ", "") : element;
        element = element.replace(".", "_");
        element = element.replace("/", "_");
        element = element.replace("\\", "_");
        element = element.replace(" ", "_");
        element = element.replace("(", "CON_");
        element = element.replace(")", "_");
        element = element.replace("__", "_");
        element = element.substring(element.length() - 1).equalsIgnoreCase("_") ? element.substring(0, element.length() - 1) : element;
        return element.replace("__", "_").toUpperCase();
    }

    /**
     * Método privado para obtener la clase del elemento solicitado en función del nombre completo de la misma
     * Ej: Genera el formato CamelCase con el nombre de la clase (1 palabra) en caso de que no se corresponda con la clase ElementConstructor
     *
     * @param className nombre de la clase del elemento solicitado
     * @return Nombre completo de la clase asociada al elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Class<?> getClassForName(String className) {
        className = (!className.equals("ElementConstructor") && !className.equals("ElementConstructor2")) ? className.substring(0, 1).toUpperCase() + className.substring(1).toLowerCase() : className;
        Class<?> c;
        try {
            c = Class.forName(Element.class.getName().replace("Element", "") + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró la clase: " + className + " para generar el objeto");
        }
        return c;
    }

    /**
     * Método privado para normalizar los nombres de los elementos, eliminando tildes y otros símbolos de puntuación
     *
     * @param name string a normalizar
     * @return el string normalizado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    @NotNull
    private static String normalice(String name) {
        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        return name.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    /**
     * Método privado para obtener el elemento solicitado en función del nombre completo de la clase y del elemento solicitado sin argumentos adicionales
     *
     * @param className Clase donde se debe buscar el elemento
     * @param element   nombre del elemento solicitado
     * @return Target del elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Target getTarget(@NotNull Class<?> className, String element) {
        Target elementTarget;
        try {
            Method method = className.getMethod("get", String.class);
            elementTarget = (Target) method.invoke(className, element);
        } catch (IllegalAccessException x) {
            throw new RuntimeException("Se encontró la clase : " + className + ", pero no se puede acceder a la misma");
        } catch (NoSuchMethodException | InvocationTargetException x) {
            throw new RuntimeException("En la clase: " + className.getName().substring(className.getName().lastIndexOf(".") + 1) + ", no se encuentra: " + element);
        }
        assert (elementTarget != null) : "No se encuentra el elemento: '" + element + "' dentro de la clase: '" + className.getName().substring(className.getName().lastIndexOf(".") + 1) + "'";
        return elementTarget;
    }

    /**
     * Método privado para obtener el elemento solicitado en función del nombre completo de la clase, del elemento solicitado y en nombre a reemplazar en el xpath
     *
     * @param clase   Clase donde se debe buscar el elemento
     * @param element nombre del elemento solicitado
     * @param name    para la construcción del xpath del elemento
     * @return Target del elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Target getTarget(@NotNull Class<?> clase, String element, String name) {
        Target elementTarget;
        try {
            Method method = clase.getMethod("get", String.class, String.class);
            elementTarget = (Target) method.invoke(clase, element, name);
        } catch (IllegalAccessException x) {
            throw new RuntimeException("Se encontró la clase : " + clase + ", pero no se puede acceder a la misma");
        } catch (NoSuchMethodException | InvocationTargetException x) {
            throw new RuntimeException("En la clase: " + clase.getName().substring(clase.getName().lastIndexOf(".") + 1) + ", no se encuentra: " + element);
        }
        assert (elementTarget != null) : "No se encuentra el elemento: '" + element + "' dentro de la clase: '" + clase.getName().substring(clase.getName().lastIndexOf(".") + 1) + "'";
        return elementTarget;
    }

    /**
     * Método privado para obtener el elemento solicitado en función del nombre completo de la clase, del elemento solicitado  y en nombre a reemplazar en el xpath
     *
     * @param clase   Clase donde se debe buscar el elemento
     * @param element nombre del elemento solicitado
     * @param name    para la construcción del xpath del elemento
     * @param section para la construcción del xpath del elemento
     * @return Target del elemento solicitado
     * @author Federico Pflüger
     * @since 11/12/2020
     */
    private static Target getTarget(@NotNull Class<?> clase, String element, String name, String section) {
        Target elementTarget;
        try {
            Method method = clase.getMethod("get", String.class, String.class, String.class);
            elementTarget = (Target) method.invoke(clase, element, name, section);
        } catch (IllegalAccessException x) {
            throw new RuntimeException("Se encontró la clase : " + clase + ", pero no se puede acceder a la misma");
        } catch (NoSuchMethodException | InvocationTargetException x) {
            throw new RuntimeException("En la clase: " + clase.getName().substring(clase.getName().lastIndexOf(".") + 1) + ", no se encuentra: " + element);
        }
        assert (elementTarget != null) : "No se encuentra el elemento: '" + element + "' dentro de la clase: '" + clase.getName().substring(clase.getName().lastIndexOf(".") + 1) + "'";
        return elementTarget;
    }
}
