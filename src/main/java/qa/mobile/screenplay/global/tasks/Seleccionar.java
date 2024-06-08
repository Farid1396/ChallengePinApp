package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotPresent;

public class Seleccionar implements Task {

    private final String elements;
    private final int indice;
    private final String nombre;

    public Seleccionar(String elements, int indice, String nombre) {
        this.elements = elements;
        this.indice = indice;
        this.nombre = nombre;
    }

    public static Seleccionar el(String elements, int indice) {
        return Instrumented.instanceOf(Seleccionar.class).withProperties(elements, indice, null);
    }

    public static Seleccionar el(String elements, String nombre) {
        return Instrumented.instanceOf(Seleccionar.class).withProperties(elements, 0, nombre);
    }

    @Step("El {0} selecciona el elemento: ' #elements ' según el numero de orden: '#indice' o el nombre: '#nombre'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(Element.get("Popup BLOCK_UI"), isNotPresent()).forNoMoreThan(30).seconds());
        List<WebElementFacade> listadoElementos = Element.get(elements).resolveAllFor(actor);
        if (nombre == null) {
            try {
                actor.attemptsTo(Click.on(listadoElementos.get(indice - 1)));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else {
            for (WebElementFacade elemento : listadoElementos) {
                if (elemento.getText().equals(nombre))
                    actor.attemptsTo(Click.on(elemento));
            }
        }
    }
}
