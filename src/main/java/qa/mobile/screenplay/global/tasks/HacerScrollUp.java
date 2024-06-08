package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.mobile.screenplay.global.elements.Element;

public class HacerScrollUp implements Task {

    private final String campo;

    public HacerScrollUp(String campo) {
        this.campo = campo;
    }

    public static HacerScrollUp al(String element) {
        return Instrumented.instanceOf(HacerScrollUp.class).withProperties(element);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement fixedElement = Element.get(campo).resolveFor(actor);
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", fixedElement);
    }
}
