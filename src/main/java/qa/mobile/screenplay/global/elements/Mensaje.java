package qa.mobile.screenplay.global.elements;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public enum Mensaje {
    USUARIO_INCORRECTO(MobileBy.xpath("//android.widget.TextView[1]"),"Mensaje "),
    DE_CONTRASENA_INCORRECTA(MobileBy.xpath("//android.widget.TextView[1]"),"Mensaje "),
    A(MobileBy.xpath(""),"Mensaje "),

    ;
    By element;
    String description;

    Mensaje(By element, String description) {
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
