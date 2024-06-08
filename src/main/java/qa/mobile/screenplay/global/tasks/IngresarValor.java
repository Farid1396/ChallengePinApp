package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class IngresarValor implements Task {
    private final String valor;
    private final String campo;

    public IngresarValor(String valor, String campo) {
        this.valor = valor;
        this.campo = campo;
    }

    public static IngresarValor en(String valor, String campo) {
        return Instrumented.instanceOf(IngresarValor.class).withProperties(valor, campo);
    }

    @Step("El {0} ingresa el valor: '#valor' en el campo: ' #campo '")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(campo), isClickable()).forNoMoreThan(30).seconds());
        actor.attemptsTo(Enter.theValue(valor).into(Element.get(campo)));
    }
}
