package ar.uba.fi.tdd.rulogic.model;

import java.util.*;

public class Rule  implements DbElement {
    protected String name;
    protected LinkedList<String> arguments; //establece el valor y el orden que debería estar en la evaluación
    protected HashMap<String,LinkedList<Integer>> facts;


    public Rule(String name, LinkedList<String> arguments){
        this.name = name;
        this.arguments = arguments;
        this.facts = new HashMap<String,LinkedList<Integer>>();
    }

    public void addFact(String name, LinkedList<String> parameters ){
        LinkedList<Integer> orderParameters = new LinkedList<Integer>();
        int order;

        for(String parameter: parameters){
            order = this.arguments.indexOf(parameter);
            if (order < 0) throw new NoSuchElementException("Parámetro no encontrado en argumentos de Rule");
            orderParameters.add(order);
        }

        this.facts.put(name,orderParameters);
    }


    public String getName() {
        return name;
    }

    public boolean evaluate(String name, LinkedList<String> arguments, KnowledgeBase db) {
        return true;
    }

//        if (!this.name.equals(name)) return false;
//
//        if (this.params.size() != arguments.size()) return false;
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
//            if (!arguments.containsKey(entry.getKey())) return false;
//
//            Integer value = entry.getValue();
//            if (!value.equals( arguments.get(entry.getKey()))) return false;
//
//        }
//
//        return true;
//    }


//    private HashMap<String, Integer> getValuesInOrder(HashMap<String, Integer> arguments, LinkedList<Integer> orders){
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
