package ar.uba.fi.tdd.rulogic.model;

import java.util.HashMap;
import java.util.Map;

public class Fact implements DbElement {
    protected HashMap<String,Integer> values; //establece el valor y el orden que debería estar en la evaluación
    protected String name;


    public Fact(String name, HashMap<String,Integer> values) {
        this.name = name;
        this.values = values;
    }

    public boolean contains(String value){
        return this.values.containsKey(value);
    }


    public String getName() {
        return name;
    }

    public boolean evaluate(String name, HashMap<String, Integer> values, KnowledgeBase db){

        if (!this.name.equals(name)) return false;

        if (this.values.size() != values.size()) return false;

        for (Map.Entry<String, Integer> entry: this.values.entrySet()) {
            // Check if the current value is a key in the 2nd map
            if (!values.containsKey(entry.getKey())) return false;

            Integer value = entry.getValue();
            if (!value.equals( values.get(entry.getKey()))) return false;

        }

        return true;
    }




//    @Override
//    public int hashCode() {
//        return this.values.size() * this.name.length();
//    }
}
