package ar.uba.fi.tdd.rulogic.model;

import java.util.*;

public class Rule  implements DbElement {
    protected String name;
    protected HashMap<String,Integer> params; //establece el valor y el orden que debería estar en la evaluación
    protected HashMap<String,LinkedList<Integer>> facts;


    public Rule(String name, HashMap<String,Integer> params){
        this.name = name;
        this.params = params;
        this.facts = new HashMap<String,LinkedList<Integer>>();
    }

    public void addValue(String FactName, LinkedList<String> Factparams ){
        LinkedList<Integer> orderArgs = new LinkedList<Integer>();

        for (Iterator<String> i = Factparams.iterator(); i.hasNext();) {
            orderArgs.add(this.params.get(i.next()));
        }

        this.facts.put(FactName,orderArgs);
    }


    public String getName() {
        return name;
    }

    public boolean evaluate(String name, HashMap<String, Integer> values, KnowledgeBase db) {
        return true;
    }

//        if (!this.name.equals(name)) return false;
//
//        if (this.params.size() != values.size()) return false;
//
//
//        for (Map.Entry<String, LinkedList<Integer>> entry: this.facts.entrySet()) {
//            LinkedList<DbElement> facts = db.getElementsByName(entry.getKey());
//
//            for (DbElement fact : facts){
//
//
//                fact.evaluate(fact.getName(),)
//            }
//
//
//            // Check if the current value is a key in the 2nd map
//            if (!values.containsKey(entry.getKey())) return false;
//
//            Integer value = entry.getValue();
//            if (!value.equals( values.get(entry.getKey()))) return false;
//
//        }
//
//        return true;
//    }


//    private HashMap<String, Integer> getValuesInOrder(HashMap<String, Integer> values, LinkedList<Integer> orders){
//        HashMap<String,Integer> orderValues = new HashMap<String, Integer>();
//
//        for( Integer order: orders){
//            orderValues.
//        }
//
//        return orderValues;
//    }

//    @Override
//    public int hashCode() {
//        return this.params.size() * this.name.length();
//    }

}
