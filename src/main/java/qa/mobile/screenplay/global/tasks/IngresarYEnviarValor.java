package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import qa.mobile.screenplay.global.elements.Element;


import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IngresarYEnviarValor implements Task {
    private final String valor;
    private final String campo;

    public IngresarYEnviarValor(String valor, String campo) {
        this.valor = valor;
        this.campo = campo;
    }

    public static IngresarYEnviarValor en(String valor, String campo) {
        return Instrumented.instanceOf(IngresarYEnviarValor.class).withProperties(valor, campo);
    }

    @Step("El actor {0} ingresa el valor: '#valor' en el campo: '#campo' y presiona enter")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(campo), isVisible()).forNoMoreThan(60).seconds());
        actor.attemptsTo(WaitUntil.the(Element.get(campo), isClickable()).forNoMoreThan(30).seconds());
        actor.attemptsTo(Enter.theValue(valor).into(Element.get(campo)));
        actor.attemptsTo(Hit.the(Keys.ENTER).into(Element.get(campo)));
    }
}
