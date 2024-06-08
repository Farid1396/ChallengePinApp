package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import qa.mobile.screenplay.global.elements.Element;

public class RegistrarArchivo implements Task {

    private final String elemento;
    private final String archivo;

    public RegistrarArchivo(String archivo, String elemento) {
        this.elemento = elemento;
        this.archivo = archivo;
    }

    public static RegistrarArchivo con(String archivo, String elemento) {
        return Instrumented.instanceOf(RegistrarArchivo.class).withProperties(archivo, elemento);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Element.get(elemento).resolveFor(actor).sendKeys(archivo);
    }
}
