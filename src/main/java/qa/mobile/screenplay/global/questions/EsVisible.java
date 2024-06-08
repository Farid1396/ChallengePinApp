package qa.mobile.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EsVisible implements Question<Boolean> {

    private static String elemento;

    public EsVisible(String elemento) {
        EsVisible.elemento = elemento;
    }

    public static EsVisible el(String elemento) {
        return Instrumented.instanceOf(EsVisible.class).withProperties(elemento);
    }

    @Step("El actor {0} verifica que el elemento: '#elemento' es visible")
    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(elemento), isVisible()).forNoMoreThan(120).seconds());
        return Element.get(elemento).resolveFor(actor).isVisible();
    }
}
