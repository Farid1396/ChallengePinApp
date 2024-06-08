package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import qa.mobile.screenplay.global.elements.Element;

public class HacerClickSiExiste implements Task {

    private final String elemento;

    public HacerClickSiExiste(String elem) {
        this.elemento = elem;
    }

    public static HacerClickSiExiste en(String elem) {
        return Instrumented.instanceOf(HacerClickSiExiste.class).withProperties(elem);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade elem;
        elem = Element.get(elemento).resolveFor(actor);
        //Only if Element is visible, it will click it
        if (elem.isVisible()) {
            actor.attemptsTo(Click.on(elem));
        }
    }

}
