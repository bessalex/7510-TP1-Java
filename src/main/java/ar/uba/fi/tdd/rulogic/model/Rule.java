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

    public LinkedList<String> getArguments() {
        return arguments;
    }

    public boolean evaluate(String name, LinkedList<String> queryArguments, KnowledgeBase db) {

        if (!this.name.equals(name)) return false;

        if (this.arguments.size() != queryArguments.size()) return false;

        // Por cada Fact contenida en la Rule
        for (Map.Entry<String, LinkedList<Integer>> entry: this.facts.entrySet()) {
            // Obtengo los argumentos de la fact
            LinkedList<DbElement> elementList = db.getElementList(entry.getKey());
            // obtengo la lista de orden en que colocar los argumentos
            LinkedList<Integer> orderList = entry.getValue();

            // Para cada elemento de la lista de Facts
            for (DbElement element : elementList){
                LinkedList<String> orderQuery = new LinkedList<String>();
                for (Integer order : orderList){
                    orderQuery.add(queryArguments.get(order));
                }

                if (!element.evaluate(element.getName(),orderQuery, db)) return false;
            }
        }

        return true;
    }


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
