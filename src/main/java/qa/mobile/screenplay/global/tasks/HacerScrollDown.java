package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.mobile.screenplay.global.elements.Element;

public class HacerScrollDown implements Task {

    private final String campo;

    public HacerScrollDown(String campo) {
        this.campo = campo;
    }

    public static HacerScrollDown al(String element) {
        return Instrumented.instanceOf(HacerScrollDown.class).withProperties(element);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement fixedElement = Element.get(campo).resolveFor(actor);
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", fixedElement);
    }
}
