package qa.mobile.screenplay.global.elements;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public enum Campo {
    DE_BUSQUEDA(MobileBy.id("com.android.chrome:id/search_box_text"),""),
    BARRA_DE_URL(MobileBy.id("com.android.chrome:id/url_bar"),""),
    USERNAME(MobileBy.xpath("(//android.widget.EditText)[1]"),"Campo "),
    PASSWORD(MobileBy.xpath("(//android.widget.EditText)[2]"),"Campo "),
    A(MobileBy.xpath(""),"Campo "),


    ;
    By element;
    String description;

    Campo(By element, String description) {
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
