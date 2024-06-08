package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class Esperar implements Task {

    private final int segundos;

    public Esperar(int segundos) {
        this.segundos = segundos;
    }

    public static Esperar segundos(int segundos) {
        return Instrumented.instanceOf(Esperar.class).withProperties(segundos);
    }

    @Step("El {0} espera #segundos segundos")
    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (Exception e) {
            //tiempo de espera
        }
    }
}
