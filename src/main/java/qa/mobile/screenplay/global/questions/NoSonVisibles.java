package qa.mobile.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;


import java.util.List;

public class NoSonVisibles implements Question<Boolean> {

    private final List<String> elements;

    public NoSonVisibles(List<String> elements) {
        this.elements = elements;
    }

    public static NoSonVisibles los(List<String> elements) {
        return Instrumented.instanceOf(NoSonVisibles.class).withProperties(elements);
    }

    @Step("El actor {0} verifica que no son visibles los elementos: '#elements'")
    @Override
    public Boolean answeredBy(Actor actor) {
        boolean status = true;
        for (String element : elements) {
            if (Element.get(element).resolveFor(actor).isVisible())
                status = false;
        }
        return status;
    }
}
