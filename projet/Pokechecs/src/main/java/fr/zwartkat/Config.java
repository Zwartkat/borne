package fr.zwartkat;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Config {

    public static Map<String, Map<String,Object>> read(String filename){
        try(InputStream inputStream = Config.class.getResourceAsStream(filename)){
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
