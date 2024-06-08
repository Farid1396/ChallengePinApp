package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import qa.mobile.screenplay.global.elements.Element;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class SeleccionarPrimeraOpcionDisponible implements Task {

    private final String campo;
    private static final String LABEL_STATUS = "label status del componente";
    private static final String STATUS_DISPONIBLE = "Available";

    public SeleccionarPrimeraOpcionDisponible(String campo) {
        this.campo = campo;
    }

    public static SeleccionarPrimeraOpcionDisponible en(String campo) {
        return Instrumented.instanceOf(SeleccionarPrimeraOpcionDisponible.class).withProperties(campo);
    }

    private boolean estaDisponible(Actor actor) {
        boolean disponible = false;
        WebElementFacade webElement = Element.get(LABEL_STATUS).resolveFor(actor);
        String status;
        status = (webElement.getText()==null) ? null : webElement.getText().trim();
        if (STATUS_DISPONIBLE.equalsIgnoreCase(status)) {
            disponible = true;
        }
        return disponible;
    }

    private void espera(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (Exception e) {
            //tiempo de espera
        }
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        int i;

        actor.attemptsTo(WaitUntil.the(Element.get(campo), isClickable()).forNoMoreThan(30).seconds());

        //Prueba encontrar un elemento con status disponible
        //al menos 10 veces, pasado ese numero queda elegido el que esta
        for (i=1;i<=40;i++) {
            //****** abro para que se despliegue el dropdown ******
            actor.attemptsTo(Click.on(Element.get(campo)));

            //***** elijo el elemento *****
            espera(3);
            actor.attemptsTo(Hit.the(Keys.ARROW_DOWN).into(Element.get(campo)));

            //***** Hago enter para confirmar en el dropdown. ******
            espera(2);
            actor.attemptsTo(Hit.the(Keys.ENTER).into(Element.get(campo)));

            //***** Chequeo que el elmento este disponible ******
            espera(2);
            boolean disponible = estaDisponible(actor);
            if (disponible) {
                //En caso de encontrar un elemento disponible,
                //ya esta, salgo del bucle
                break;
            }
        }
    }
}
