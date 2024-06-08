package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

public class Actualizar implements Task {

    public Actualizar() { }

    public static Actualizar laPagina() {

        return Tasks.instrumented(Actualizar.class);
    }

    @Override
    @Step("{0} refreshes the browser")
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
    }
}
