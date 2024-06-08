package qa.mobile.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class EstaPresente implements Question<Boolean> {

    private static String elemento;

    public EstaPresente(String elemento) {
        EstaPresente.elemento = elemento;
    }

    public static EstaPresente el(String elemento) {
        return Instrumented.instanceOf(EstaPresente.class).withProperties(elemento);
    }

    @Step("El actor {0} verifica que el elemento: '#elemento' esta presente")
    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(elemento), isPresent()).forNoMoreThan(10).seconds());
        return Element.get(elemento).resolveFor(actor).isPresent();
    }
}
