package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CambiarAlIframe implements Task {

    private final Integer index;

    public CambiarAlIframe(Integer i) {
        this.index = i;
    }

    public static CambiarAlIframe conIndice(Integer index) {
        return Instrumented.instanceOf(CambiarAlIframe.class).withProperties(index);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@title='reCAPTCHA'])["+ index +"]")));
    }
}
