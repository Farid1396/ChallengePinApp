package qa.mobile.screenplay.global.elements;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public enum Boton {
    SUBMIT(MobileBy.xpath("(//android.widget.Button)[1]"),"Botón "),
    LOGOUT(MobileBy.xpath("//android.view.View[@content-desc='Log out']/android.widget.TextView"),"Botón "),
    USAR_SIN_UNA_CUENTA(MobileBy.id("com.android.chrome:id/signin_fre_dismiss_button"),"Botón "),
    MAS(MobileBy.id("com.android.chrome:id/more_button"),"Botón "),
    ENTENDIDO(MobileBy.id("com.android.chrome:id/ack_button"),"Botón "),
    A(MobileBy.xpath(""),"Botón "),

    ;
    By element;
    String description;

    Boton(By element, String description) {
        this.element = element;
        this.description = description;
    }

    public static Target get(String elementName) {
        Target element;
        try {
            element = Target.the(valueOf(elementName).description).located(valueOf(elementName).element);
        } catch (Exception x) {
            element = null;
        }
        return element;
    }
}
