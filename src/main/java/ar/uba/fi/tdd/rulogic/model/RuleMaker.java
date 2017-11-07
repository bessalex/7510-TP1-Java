package ar.uba.fi.tdd.rulogic.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleMaker implements DbElementMaker{
    private DbElementMaker next;

    private Pattern splitFactsPattern; // patron de split de parametros
    private Pattern patternTypeCheck; // patron para chequear tipo
    private Pattern pattern;   // Patron utilizado para separar Nombre del Fact con argumentos
    private Pattern splitPattern; // patron de split de parametros
    private Pattern patternFact;

    public RuleMaker(){  //(\\w+)[ ]*[(]{1}[ ]*((?:\\w+(?:(?:,[ ]*)*))+)[)]{1}[ ]*:-[ ]*((?:(?:(?:\\w+[ ]*[(]{1})+(?:\\w+(?:(?:,[ ]*)*))+(?:[)]{1}))(?:,[ ]*)*)+).
        this.pattern = Pattern.compile("(\\w+)\\s*[(]{1}\\s*((?:\\w+(?:(?:,\\s*)*))+)[)]{1}\\s*:-\\s*((?:(?:(?:\\w+\\s*[(]{1})+(?:\\w+(?:(?:,\\s*)*))+(?:[)]{1}))(?:,\\s*)*)+).");
        this.splitFactsPattern = Pattern.compile("\\),");
        this.patternFact = Pattern.compile("(\\w+)\\s*[(]{1}\\s*((\\w+(?:(,\\s*)*))+)\\s*");
        this.splitPattern = Pattern.compile("[ ]*,[ ]*");
    }

    public void setNext(DbElementMaker nextMaker){
        this.next = nextMaker;
    }

    public DbElementMaker getNext(){
        return this.next;
    }

    public DbElement make(String element){
        if (!this.isRule(element)) return this.next.make(element);

        ArrayList<String> parseRule = this.parserRule(element); // Obtengo nombre, Argumentos y Facts

        HashMap<String,Integer> params = this.getParams(parseRule.get(1));
        ArrayList<String> facts = this.parserFacts(parseRule.get(2));

        Rule rule = new Rule(parseRule.get(0),params);

        for(String fact: facts){
            ArrayList<String> factDef = this.parserFact(fact);
            LinkedList<String> factParams = this.getFactParams(factDef.get(1));
            rule.addValue(factDef.get(0),factParams);
        }

        return rule;
    }

    public boolean isRule(String element){
        return this.pattern.matcher(element).matches();
    }


    private ArrayList<String> parserRule(String element){
        ArrayList<String> rule = new ArrayList<String>();

        Matcher matcher = this.pattern.matcher(element);
        matcher.find();
        rule.add(new String(matcher.group(1)));  // nombre de la fact
        rule.add(new String (matcher.group(2))); // argumentos de la mísma
        rule.add(new String (matcher.group(3))); // Facts por los que está conformada
        return rule;
    }

    private HashMap<String,Integer> getParams(String paramsStr){
        HashMap<String,Integer> params = new HashMap<String, Integer>();
        Pattern pattern = Pattern.compile(",");
        String[] paramsVector = pattern.split(paramsStr);

        for(int i=0; i< paramsVector.length; i++){
            params.put(paramsVector[i],i);
        }
        return params;
    }

    private ArrayList<String> parserFacts(String element){
        ArrayList<String> facts = new ArrayList<String>();

        String[] factsVector = this.splitFactsPattern.split(element);

        for(int i=0; i< factsVector.length; i++){
            facts.add(factsVector[i]);
        }
        return facts;
    }

    private ArrayList<String> parserFact(String fact){
        ArrayList<String> factDef = new ArrayList<String>();

        Matcher matcher = this.patternFact.matcher(fact);
        matcher.find();
        factDef.add(new String(matcher.group(1)));  // nombre de la fact
        factDef.add(new String (matcher.group(2))); // argumentos de la mísma

        return factDef;
    }

    private LinkedList<String> getFactParams(String paramsStr) {

        LinkedList<String> factParams = new LinkedList<String>(); //

        String[] arguments = this.splitPattern.split(paramsStr);
        for (int i = 0; i < arguments.length; i++) {
            factParams.add(arguments[i]);

        }
        return factParams;
    }

}
