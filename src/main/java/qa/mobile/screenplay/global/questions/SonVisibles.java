package qa.mobile.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import java.util.List;

public class SonVisibles implements Question<Boolean> {

    private final String type;
    private final List<String> elements;

    public SonVisibles(String type, List<String> elements) {
        this.type = type;
        this.elements = elements;
    }

    public static SonVisibles los(List<String> elements) {
        return Instrumented.instanceOf(SonVisibles.class).withProperties(null, elements);
    }

    public static SonVisibles los(String type, List<String> elements) {
        return Instrumented.instanceOf(SonVisibles.class).withProperties(type, elements);
    }

    @Step("El actor {0} verifica que son visibles los elementos: '#elements'")
    @Override
    public Boolean answeredBy(Actor actor) {
        boolean status = true;
        if (type != null) {
            for (String element : elements) {
                if (!Element.get(type + " " + element).resolveFor(actor).isVisible())
                    status = false;
            }
        } else {
            for (String element : elements) {
                if (!Element.get(element).resolveFor(actor).isVisible())
                    status = false;
            }
        }
        return status;
    }
}
