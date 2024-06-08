package qa.mobile.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;


public class EstaDeshabilitado implements Question<Boolean> {

    private final String elemento;

    public EstaDeshabilitado(String elemento) {
        this.elemento = elemento;
    }

    public static EstaDeshabilitado el(String elemento) {
        return Instrumented.instanceOf(EstaDeshabilitado.class).withProperties(elemento);
    }

    @Step("El actor {0} verifica que el elemento: '#elemento' esta deshabilitado")
    @Override
    public Boolean answeredBy(Actor actor) {
        return Element.get(elemento).resolveFor(actor).isDisabled();
    }
}
