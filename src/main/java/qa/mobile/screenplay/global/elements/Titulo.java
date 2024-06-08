package qa.mobile.screenplay.global.elements;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public enum Titulo {
    DE_LA_PAGINA_PRINCIPAL(MobileBy.xpath("//android.view.View[@content-desc='Practice Test Automation']/android.widget.Image"),"Título "),
    LOGEO_CORRECTO(MobileBy.xpath("(//android.widget.TextView)[1]"),"Título "),
    A(MobileBy.xpath(""),"Título "),

    ;
    By element;
    String description;

    Titulo(By element, String description) {
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
