package qa.mobile.screenplay.global.elements;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public enum Opcion {
    DEL_LINK(MobileBy.id("com.android.chrome:id/line_2"),""),
    A(MobileBy.xpath(""),"Opción "),

    ;
    By element;
    String description;

    Opcion(By element, String description) {
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
