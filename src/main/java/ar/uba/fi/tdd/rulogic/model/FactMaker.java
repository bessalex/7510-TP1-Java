package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactMaker implements DbElementMaker{
    private DbElementMaker next;

    private Pattern splitPattern; // patron de split de parametros
    private Pattern pattern;   // Patron utilizado para separar Nombre del Fact con argumentos

    public FactMaker(){
        this.pattern = Pattern.compile("(\\w+)[ ]*[(]{1}[ ]*((\\w+(?:(,[ ]*)*))+)[ ]*[)]{1}[ ]*.");
        this.splitPattern = Pattern.compile("[ ]*,[ ]*");
    }


    public void setNext(DbElementMaker nextMaker){
        this.next = nextMaker;
    }

    public DbElementMaker getNext(){
        return this.next;
    }

    public DbElement make(String element){
        if (!this.isFact(element)) return this.next.make(element);

        ArrayList<String> parseFact = this.parserFact(element); // Obtengo nombre y Argumentos

        HashMap<String,Integer> argumentsOrder = new HashMap<String,Integer>(); //

        String[] arguments = this.splitPattern.split(parseFact.get(1));
        for (int i = 0; i < arguments.length; i++) {
            argumentsOrder.put(arguments[i],i);
        }

        return new Fact(parseFact.get(0),argumentsOrder);

    }

    public boolean isFact(String element){
  //      System.out.println("isFact: " + element + "es?: " + this.pattern.matcher(element).matches());
        return pattern.matcher(element).matches();
    }

    private ArrayList<String> parserFact(String element){
        ArrayList<String> fact = new ArrayList<String>();

        Matcher matcher = this.pattern.matcher(element);
        matcher.find();
        fact.add(new String(matcher.group(1)));  // nombre de la fact
        fact.add(new String (matcher.group(2))); // argumentos de la mísma
        return fact;
    }

}
