package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SeleccionarLa implements Task {

    private final String element;
    private final String opcion;

    public SeleccionarLa(String element, String opcion) {
        this.element = element;
        this.opcion = opcion;
    }

    public static SeleccionarLa opcion(String element, String opcion) {
        return Instrumented.instanceOf(SeleccionarLa.class).withProperties(element, opcion);
    }

    @Step("El actor {0} filtra la busqueda sobre: '#element' aplicando el criterio: '#opcion'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        theActorInTheSpotlight().attemptsTo(HacerClick.en(element));
        theActorInTheSpotlight().attemptsTo(Esperar.segundos(2));
        theActorInTheSpotlight().attemptsTo(HacerClick.en(opcion));
    }
}
