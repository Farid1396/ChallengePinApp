package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import qa.mobile.screenplay.global.abilities.Identificarse;

import java.util.concurrent.TimeUnit;

public class AbreLaApp implements Task {

    public static AbreLaApp correctamete() {
        return Instrumented.instanceOf(AbreLaApp.class).withProperties();
    }

    @Step("El {0} se loguea correctamente en el sitio")
    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driverAppium = BrowseTheWeb.as(actor).getDriver();
        System.out.println("<--------Automated Application Started-------->");
        System.out.println("<------------------ Chrome ------------------->");
        driverAppium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actor.attemptsTo(HacerClick.en("Botón usar sin una cuenta"));
        actor.attemptsTo(HacerClick.en("Botón más"));
        actor.attemptsTo(HacerClick.en("Botón entendido"));
    }

    private Identificarse credenciales(Actor actor) {
        return Identificarse.conSusDatos(actor);
    }
}
