package qa.mobile.screenplay.global.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class Identificarse implements Ability {
    private static HashMap<String, String> usuario;

    private Identificarse(String usuarioRegistrado) {
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/users/" + usuarioRegistrado + ".properties");
            Properties prop = new Properties();
            prop.load(ip);
            usuario = new HashMap<>();
            Set<String> propertyNames = prop.stringPropertyNames();
            for (String Property : propertyNames) {
                usuario.put(Property, prop.getProperty(Property));
            }
            ip.close();
        } catch (Exception ex) {
            usuario = null;
        }
    }

    public static Identificarse como(String usuario) {
        return new Identificarse(usuario);
    }

    public static Identificarse conSusDatos(Actor actor) {
        if (actor.abilityTo(Identificarse.class) == null) {
            throw new RuntimeException("El actor " + actor.getName() + "no tiene la habilidad de identificarse de esa forma");
        }
        return actor.abilityTo(Identificarse.class);
    }

    public String get(String caracteristica) {
        if (usuario.get(caracteristica) == null) {
            throw new RuntimeException("El actor no posee la caracteristica solicitada: " + caracteristica);
        }
        return usuario.get(caracteristica);
    }
}
