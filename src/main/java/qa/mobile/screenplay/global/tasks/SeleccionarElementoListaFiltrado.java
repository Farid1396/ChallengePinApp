package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class SeleccionarElementoListaFiltrado implements Task {

    private final String opcion;
    private final String elements;
    private final String campo;

    public SeleccionarElementoListaFiltrado(String opcion, String elements, String campo) {
        this.opcion = opcion;
        this.elements = elements;
        this.campo = campo;
    }

    public static SeleccionarElementoListaFiltrado en(String opcion, String elements, String campo) {
        return Instrumented.instanceOf(SeleccionarElementoListaFiltrado.class).withProperties(opcion, elements, campo);
    }

    @Step("El {0} selecciona el elemento: ' #elements ' según el nombre: '#nombre' del campo")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(campo), isClickable()).forNoMoreThan(30).seconds());
        actor.attemptsTo(Esperar.segundos(3));
        actor.attemptsTo(Click.on(Element.get(campo)));
        actor.attemptsTo(Enter.theValue(opcion).into(Element.get(campo)));

        actor.attemptsTo(Esperar.segundos(4));

        List<WebElementFacade> listadoElementos = Element.get(elements).resolveAllFor(actor);

        for (WebElementFacade element : listadoElementos) {
            if (element.getText().equals(opcion)) {
                actor.attemptsTo(Click.on(element));
            }
        }
    }
}
