package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactMaker implements DbElementMaker{
    private DbElementMaker next;

    private Pattern splitPattern; // patron de split de parametros
    private Pattern pattern;   // Patron verificar si es facts
    private Pattern groupsPatter;   // Patron verificar si es facts

    public FactMaker(){
        this.pattern = Pattern.compile("(\\w+)[ ]*[(]{1}[ ]*((\\w+(?:(,[ ]*)*))+)[ ]*[)]{1}[ ]*.");
        this.splitPattern = Pattern.compile("[ ]*,[ ]*");
        this.groupsPatter = Pattern.compile("(\\w+)[ ]*[(]{1}[ ]*((\\w+(?:(,[ ]*)*))+)[ ]*([)]{1})?[ ]*(.)?");
    }


    public void setNext(DbElementMaker nextMaker){
        this.next = nextMaker;
    }

    public DbElementMaker getNext(){
        return this.next;
    }

    public DbElement make(String element, KnowledgeBase db) throws IllegalArgumentException {
        if (!this.isFact(element)) throw new IllegalArgumentException("Elemento de formato desconocido ("+ element+")");

        ArrayList<String> parseFact = this.parser(element); // Obtengo nombre y Argumentos
        LinkedList<String> arguments = this.getArguments(parseFact.get(1));

        return new Fact(parseFact.get(0),arguments);

    }

    public boolean isFact(String element){
  //      System.out.println("isFact: " + element + "es?: " + this.pattern.matcher(element).matches());
        return pattern.matcher(element).matches();
    }

    protected ArrayList<String> parser(String element){
        ArrayList<String> fact = new ArrayList<String>();

        Matcher matcher = this.groupsPatter.matcher(element);
        matcher.find();
        fact.add(matcher.group(1));  // nombre de la fact
        fact.add(matcher.group(2)); // argumentos de la mísma
        return fact;
    }

    protected LinkedList<String> getArguments(String strArguments){
        LinkedList<String> arguments = new LinkedList<String>();
        String[] vArguments = this.splitPattern.split(strArguments);
        Collections.addAll(arguments,vArguments);
        return arguments;
    }

}
