package qa.mobile.screenplay.global.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;
import qa.mobile.screenplay.global.elements.Element;

import java.util.ArrayList;
import java.util.List;

public class LosTextos implements Question<List<String>> {

    private final String elements;

    public LosTextos(String elemento) {
        this.elements = elemento;
    }

    public static LosTextos son(String elemento) {
        return Instrumented.instanceOf(LosTextos.class).withProperties(elemento);
    }

    @Step("El actor {0} verifica los textos de los elementos: '#elements'")
    @Override
    public List<String> answeredBy(Actor actor) {
        List<WebElementFacade> listadoElementos = Element.get(elements).resolveAllFor(actor);
        List<String> list = new ArrayList<>();
        for (WebElementFacade element : listadoElementos) {
            list.add(element.getText());
        }
        return list;
    }
}
