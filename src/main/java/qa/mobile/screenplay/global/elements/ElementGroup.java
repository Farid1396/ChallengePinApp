package qa.mobile.screenplay.global.elements;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElementGroup {
    private static Map<String, List<Map<String, String>>> pageObj;

    public static void readXPathFromFile(String nameOfFile) {
        try {
            String groupName = nameOfFile + ".yml";
            InputStream inputStream = new FileInputStream(new File("src/test/resources/grupos/" + groupName));
            Yaml yaml = new Yaml();
            pageObj = (Map<String, List<Map<String, String>>>) yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.print("No se pudo encontrar el archivo !!!");
        }
    }

    public static void addXPathFromFile(String nameOfFile) {
        try {
            Map<String, List<Map<String, String>>> tmpPageObj;
            String groupName = nameOfFile + ".yml";
            InputStream inputStream = new FileInputStream(new File("src/test/resources/grupos/" + groupName));
            Yaml yaml = new Yaml();
            tmpPageObj = (Map<String, List<Map<String, String>>>) yaml.load(inputStream);
            pageObj.putAll(tmpPageObj);
        } catch (FileNotFoundException e) {
            System.out.print("No se pudo encontrar el archivo !!!");
        }
    }

    private static String getFirstWord(String phrase) {
        String[] words = phrase.split(" ");
        String firstWord = words[0];
        return firstWord;
    }

    private static String getSubElement(String phrase) {
        String[] words = phrase.split(" ");
        int j;
        String keyWord = "";
        String underscore = "";
        for (j = 1; j < words.length; j++) {
            keyWord = keyWord + underscore + words[j];
            underscore = "_";
        }

        return keyWord;
    }

    public static String getXPathElement(String phrase) {
        String firstW = getFirstWord(phrase);
        String subElemW = getSubElement(phrase);
        List<Map<String, String>> listaDeSubElementos = pageObj.get(firstW);
        int t;
        String xpath = null;
        for (t = 0; t < listaDeSubElementos.size(); t++) {
            Map<String, String> par = listaDeSubElementos.get(t);
            xpath = par.get(subElemW);
            if (xpath != null && !"".equals(xpath)) {
                return xpath;
            }
        }
        return xpath;
    }

    /*******************************************************************
     * Funcion para obtener los parametros dentro de un alias de xpath
     * Por ejemplo: Supongamos que tenemos el siguiente alias
     * tabla 'Asociar Usuarios' columna '2'
     * Los parametros de este alias serian "Asociar Usuarios" y "2"
     ********************************************************************/
    public static ArrayList<String> getParamaters(String xpathAliasStr) {
        char delimiter = '\'';
        boolean isOpenChar = false;
        ArrayList<String> parameters = new ArrayList<String>();
        String param = "";
        for (int i = 0; i < xpathAliasStr.length(); i++) {
            if (xpathAliasStr.charAt(i) == delimiter) {
                isOpenChar = !isOpenChar;
                if (isOpenChar == true) {
                    continue;
                } else {
                    parameters.add(param.trim());
                    param = "";
                }
            }

            if (isOpenChar) {
                param = param + xpathAliasStr.charAt(i);
            }
        }
        return parameters;
    }

    /*********************************************************
     * Funcion para obtener el alias de xpath sin parametros
     * Por ejemplo: Supongamos que tenemos el siguiente alias
     * tabla 'Asociar Usuarios' columna '2'
     * Esta funcion retornaria "tabla columna".
     **********************************************************/
    public static String getPhrase(String xpathAliasStr) {
        char delimiter = '\'';
        boolean isAddingChars = true;
        String phrase = "";
        int whiteSpace = 0;
        for (int i = 0; i < xpathAliasStr.length(); i++) {
            if (xpathAliasStr.charAt(i) == delimiter) {
                isAddingChars = !isAddingChars;
                continue;
            }

            if (isAddingChars) {
                if (xpathAliasStr.charAt(i) == ' ') {
                    if (whiteSpace == 0) {
                        phrase = phrase + xpathAliasStr.charAt(i);
                    }
                    whiteSpace++;
                } else {
                    whiteSpace = 0;
                    phrase = phrase + xpathAliasStr.charAt(i);
                }
            }
        }
        phrase = phrase.trim();
        return phrase;
    }

    public static String getXPathElementWithQuotes(String pAlias) {

        ArrayList<String> parameters;
        String phrase;
        String xpathStr;

        parameters = getParamaters(pAlias);
        phrase = getPhrase(pAlias);

        xpathStr = getXPathElement(phrase);

        int j = 1;
        for (String paramTxt : parameters) {
            xpathStr = xpathStr.replace("%s" + j, paramTxt);
            j++;
        }

        return xpathStr;
    }
}
