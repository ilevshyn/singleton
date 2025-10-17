package edu.io;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class AppConfig {

    private static volatile AppConfig instance; //volatile to ensure that threads read the same instance

    private HashMap<String, Object> config;

    public static synchronized AppConfig getInstance(){ //synchronized to ensure that only one thread may execute this method
        if(instance==null){
            instance=new AppConfig();
        }
        return instance;
    }

    public <T> void set(String key,T value){
        config.put(key,value);
    }

    public Object get(String key){
        return config.getOrDefault(key,null);
    }

    public <T> T get(String key, Class<T> c){
        return c.cast(config.getOrDefault(key,null));
    }

    public void load(String path){
        Yaml yaml = new Yaml();
        try(FileReader reader = new FileReader(path)){
            this.config = yaml.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas odczytu pliku " + path);
        }
    }

    public void save(String path){
        Yaml yaml = new Yaml();
        try(FileWriter writer = new FileWriter(path, false)){
            yaml.dump(this.config, writer);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas zapisywania pliku " + path);
        }
    }

    private AppConfig(){
        config=new HashMap<>();
    }
}
