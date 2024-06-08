package qa.mobile.steps.global;

import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import qa.mobile.screenplay.global.questions.*;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidaccionesStepsDef {

    //region Entonces
    @Entonces("^el usuario observa (?:que en|que|en) (?:la|el) (.*) (?:es|están|está) (?:visible|visibles)$")
    public void elUsuarioObervaQueElElementoEsVisible(String elemento) {
        theActorInTheSpotlight().should(seeThat(EsVisible.el(elemento)));
    }

    @Entonces("^el usuario observa que (?:el|la|los|las) (.*) esta presente")
    public void elUsuarioObervaQueElElementoEstaPresente(String elemento) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(EstaPresente.el(elemento)));
    }

    @Entonces("^no se visualiza (?:el|la) (.*) en la pantalla$")
    public void noSeVisualizaElElementoEnLaPantalla(String elemento) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(NoEsVisible.el(elemento)));
    }

    @Entonces("^se visualizan (?:el|los) siguiente(?:|s) bot(?:ón|ones)$")
    public void seVisualizanLosSiguientesBotones(List<String> elementos) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(SonVisibles.los("Botón", elementos)));
    }

    @Entonces("^se visualiza(?:|n) (?:el|los) siguiente(?:|s) elemento(?:|s) de la (?:llamada|tabla)")
    public void seVisualizanLosSiguientesElementosDeLaLlamada(List<String> elementos) {
        theActorInTheSpotlight().should(seeThat(SonVisibles.los("Label", elementos)));
    }

    @Entonces("^se visualiza(?:|n) (?:el|los) siguiente(?:|s) campos(?:|s)$")
    public void seVisualizanLosSiguientesElementos(List<String> elementos) {
        theActorInTheSpotlight().should(seeThat(SonVisibles.los("Campo", elementos)));
    }

    @Entonces("^se visualizan las columnas de la (.*)$")
    public void seVisualizanLasColumnasDeLaTabla(String tabla, List<String> texto) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(LosTextos.son(tabla), equalTo(texto)));
    }

    @Entonces("visualiza que (?:el|la) (.*) esta habilitado$")
    public void visualizaQueElElementoEstaEstaHabilitado(String elemento) {
        theActorInTheSpotlight().should(seeThat(EstaHabilitado.el(elemento)));
    }

    @Entonces("visualiza que (?:el|la) (.*) esta deshabilitado$")
    public void visaulizaQueElElementoEstaDeshabilitado(String elemento) {
        theActorInTheSpotlight().should(seeThat(EstaDeshabilitado.el(elemento)));
    }
}
