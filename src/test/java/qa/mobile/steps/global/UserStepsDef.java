package qa.mobile.steps.global;

import io.cucumber.java.es.Cuando;
import qa.mobile.screenplay.global.abilities.Identificarse;
import qa.mobile.screenplay.global.tasks.IngresarValor;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UserStepsDef {

    //region Cuando
    @Cuando("^el usuario ingresa su contraseña en el (.*)$")
    public void elUsuarioIngresaSuContraseniaEnEl(String elemento) {
        String pass = Identificarse.conSusDatos(theActorInTheSpotlight()).get("Password");
        theActorInTheSpotlight().attemptsTo(IngresarValor.en(pass, elemento));
    }
    //endregion
}
