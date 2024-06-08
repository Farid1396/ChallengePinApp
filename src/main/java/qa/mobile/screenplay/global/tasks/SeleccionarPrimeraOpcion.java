package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import qa.mobile.screenplay.global.elements.Element;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class SeleccionarPrimeraOpcion implements Task {
    private final String campo;

    public SeleccionarPrimeraOpcion(String campo) {
        this.campo = campo;
    }

    public static SeleccionarPrimeraOpcion en(String campo) {
        return Instrumented.instanceOf(SeleccionarPrimeraOpcion.class).withProperties(campo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(campo), isClickable()).forNoMoreThan(30).seconds());
        actor.attemptsTo(Click.on(Element.get(campo)));
        try {
            Thread.sleep(3 * 1000L);
        } catch (Exception e) {
            //tiempo de espera
        }
        actor.attemptsTo(Hit.the(Keys.ARROW_DOWN).into(Element.get(campo)));
        try {
            Thread.sleep(2 * 1000L);
        } catch (Exception e) {
            //tiempo de espera
        }
        actor.attemptsTo(Hit.the(Keys.ENTER).into(Element.get(campo)));
    }
}
