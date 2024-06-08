package qa.mobile.screenplay.global.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.DoubleClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class HacerDobleClick implements Task {

    private final String element;

    public HacerDobleClick(String element) {
        this.element = element;
    }

    public static HacerDobleClick en(String element) {
        return Instrumented.instanceOf(HacerDobleClick.class).withProperties(element);
    }

    @Step("El {0} hace doble click en el ' #element '")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(Element.get(element), isClickable()).forNoMoreThan(30).seconds());
        actor.attemptsTo(DoubleClick.on(Element.get(element)));

    }
}
