package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class HacerClick implements Task {

    private final String element;

    public HacerClick(String element) {
        this.element = element;
    }

    public static HacerClick en(String element) {
        return Instrumented.instanceOf(HacerClick.class).withProperties(element);
    }

    @Step("El {0} hace click en el ' #element '")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(element), isVisible()).forNoMoreThan(60).seconds());
        actor.attemptsTo(WaitUntil.the(Element.get(element), isClickable()).forNoMoreThan(30).seconds());
        actor.attemptsTo(Click.on(Element.get(element)));
    }
}
