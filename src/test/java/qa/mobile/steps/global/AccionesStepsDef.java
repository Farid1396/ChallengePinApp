package qa.mobile.steps.global;

import io.appium.java_client.MobileBy;
import io.cucumber.java.es.Cuando;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.*;
import qa.mobile.screenplay.global.questions.EsVisible;
import qa.mobile.screenplay.global.tasks.*;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static qa.mobile.screenplay.global.tasks.HacerScroll.isEndOfPage;
import static qa.mobile.screenplay.global.tasks.HacerScroll.scroll;

public class AccionesStepsDef {

    //region Cuando
    @Cuando("^el usuario hace click en (?:el|la) (.*)$")
    public void elUsuarioHaceClickEnEl(String elemento) {
        theActorInTheSpotlight().attemptsTo(HacerClick.en(elemento));
    }

    @Cuando("^se hace click en (?:el|la) (.*) y verifica visibilidad de (?:el|la) (.*)$")
    public void elUsuarioHaceClickEnElYVerificaVisibilidad(String elemento, String elemento1) {
        theActorInTheSpotlight().attemptsTo(HacerClick.en(elemento));
        theActorInTheSpotlight().should(seeThat(EsVisible.el(elemento1)));
    }

    @Cuando("^el usuario hace click si existe en (?:el|la) (.*)$")
    public void elUsuarioHaceClickSiExisteEnEL(String elemento) {
        theActorInTheSpotlight().attemptsTo(Esperar.segundos(3));
        theActorInTheSpotlight().attemptsTo(HacerClickSiExiste.en(elemento));
    }

    @Cuando("^el usuario hace doble click en (?:el|la) (.*)$")
    public void elUsuarioHaceDobleClickEnEl(String elemento) {
        theActorInTheSpotlight().attemptsTo(HacerDobleClick.en(elemento));
    }

    @Cuando("^el usuario ingresa '(.*)' en el (.*)$")
    public void elUsuarioIngresaEnElCampo(String valor, String elemento) {
        theActorInTheSpotlight().attemptsTo(IngresarValor.en(valor, elemento));
    }

    @Cuando("^el usuario ingresa y recuerda '(.*)' en el (.*)$")
    public void elUsuarioIngresaYRecuerdaEnElCampo(String valor, String elemento) {
        theActorInTheSpotlight().attemptsTo(IngresarValor.en(valor, elemento));
    }

    @Cuando("^el usuario ingresa y selecciona '(.*)' en el (.*)$")
    public void elUsuarioIngresaYSeleccionaEnElCampo(String valor, String campo) {
        theActorInTheSpotlight().attemptsTo(Esperar.segundos(3));
        theActorInTheSpotlight().attemptsTo(IngresarValor.en(valor, campo));
        theActorInTheSpotlight().attemptsTo(HacerClick.en("Opción '" + valor + "'"));
    }

    @Cuando("^el usuario selecciona la opcion '(.*)' en (?:el|la) (.*)$")
    public void elUsuarioSeleccionaLaOpcion(String opcion, String elementos) {
        theActorInTheSpotlight().attemptsTo(Esperar.segundos(1));
        theActorInTheSpotlight().attemptsTo(Seleccionar.el(elementos, opcion));
    }

    @Cuando("^el usuario ingresa y selecciona la opcion '(.*)' en (?:el|la) (.*) del (.*)$")
    public void seleccionaLaOpcionDeLaLista(String opcion, String elementos, String campo) {
        theActorInTheSpotlight().attemptsTo(SeleccionarElementoListaFiltrado.en(opcion, elementos, campo));
    }

    @Cuando("^el usuario espera '(.*)' (?:segundo|segundos)$")
    public void elUsuarioEspera(Integer elemento) {
        theActorInTheSpotlight().attemptsTo(Esperar.segundos(elemento));
    }

    @Cuando("el usuario abre el combo y selecciona la (.*) en (?:el|la) (.*)$")
    public void elUsuarioAbreElComboYSeleccionaLaOpcionDisponibleEnElComboEstatus(String opcion, String elemento) {
        theActorInTheSpotlight().attemptsTo(SeleccionarLa.opcion(elemento, opcion));
    }

    @Cuando("^el usuario ingresa en el (.*) el texto '(.*)' y hace enter$")
    public void elUsuarioIngresaElTextoYHaceEnter(String elemento, String valor) {
        theActorInTheSpotlight().attemptsTo(IngresarYEnviarValor.en(valor, elemento));
    }

    @Cuando("^el usuario ingresa y recuerda en el (.*) el texto '(.*)' y hace enter$")
    public void elUsuarioIngresaYRecuerdaElTextoYHaceEnter(String elemento, String valor) {
        theActorInTheSpotlight().attemptsTo(IngresarYEnviarValor.en(valor, elemento));
    }

    @Cuando("^el usuario ingresa en el (.*) el texto '(.*)' y selecciona del dropdown$")
    public void elUsuarioIngresaElTextoYSeleccionaDelDropDown(String elemento, String valor) {
        theActorInTheSpotlight().attemptsTo(IngresaYSelecciona.en(valor, elemento));
    }

    @Cuando("^el usuario selecciona la primera opcion del dropdown (.*)$")
    public void elUsuarioSeleccionLaPrimeraOpcionDelDropDown(String elemento) {
        theActorInTheSpotlight().attemptsTo(SeleccionarPrimeraOpcion.en(elemento));
    }

    @Cuando("^el usuario hace scroll down hasta el (.*)$")
    public void elUsuarioHaceScrollDownHastaEl(String elemento) {
        theActorInTheSpotlight().attemptsTo(HacerScrollDown.al(elemento));
    }

    @Cuando("^el usuario hace scroll up hasta el (.*)$")
    public void elUsuarioHaceScrollUpHastaEl(String elemento) {
        theActorInTheSpotlight().attemptsTo(HacerScrollUp.al(elemento));
    }

    @Cuando("el usuario ingresa '(.*)' en la (.*)$")
    public void elUsuarioIngresaEnLa(String valor, String elemento) {
        theActorInTheSpotlight().attemptsTo(IngresarValor.en(valor, elemento));
    }

    @Cuando("el usuario hace un scrolleo$")
    public void elUsuarioHaceUnScrolleo() {
        getDriver().findElements(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector()).setAsVerticalList().scrollToEnd(1)"));
    }
}
