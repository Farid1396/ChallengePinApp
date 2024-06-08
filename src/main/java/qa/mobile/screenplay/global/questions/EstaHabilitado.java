package qa.mobile.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;


import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EstaHabilitado implements Question<Boolean> {

    private final String elemento;

    public EstaHabilitado(String elemento) {
        this.elemento = elemento;
    }

    public static EstaHabilitado el(String elemento) {
        return Instrumented.instanceOf(EstaHabilitado.class).withProperties(elemento);
    }

    @Step("El actor {0} verifica que el elemento: '#elemento' esta habilitado")
    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(elemento), isVisible()).forNoMoreThan(10).seconds());
        return Element.get(elemento).resolveFor(actor).isEnabled();
    }
}
