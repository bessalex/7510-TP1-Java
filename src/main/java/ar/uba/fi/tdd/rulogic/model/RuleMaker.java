package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleMaker extends FactMaker implements DbElementMaker{
    private DbElementMaker next;

    private Pattern splitFactsPattern; // patron de split de parametros
    private Pattern pattern;   // Patron utilizado para separar Nombre del Fact con argumentos

    public RuleMaker(){
        super();
        this.pattern = Pattern.compile("(\\w+)\\s*[(]{1}\\s*((?:\\w+(?:(?:,\\s*)*))+)[)]{1}\\s*:-\\s*((?:(?:(?:\\w+\\s*[(]{1})+(?:\\w+(?:(?:,\\s*)*))+(?:[)]{1}))(?:,\\s*)*)+).");
        this.splitFactsPattern = Pattern.compile("\\)[ ]*,[ ]*");
    }

    public void setNext(DbElementMaker nextMaker){
        this.next = nextMaker;
    }

    public DbElementMaker getNext(){
        return this.next;
    }

    public DbElement make(String element, KnowledgeBase db) throws IllegalArgumentException {
        if (!this.isRule(element)) return this.next.make(element, db);

        ArrayList<String> parseRule = this.parserRule(element); // Obtengo nombre, Argumentos y Facts

        LinkedList<String> params = this.getParams(parseRule.get(1));
        ArrayList<String> facts = this.parserFacts(parseRule.get(2));

        Rule rule = new Rule(parseRule.get(0),params);

        for(String fact: facts){
            ArrayList<String> factDef = this.parser(fact);
            if (!db.contains(factDef.get(0)))
                throw new IllegalArgumentException("Rule con Fact inexistente en Rule : "+ factDef.get(0));

            LinkedList<String> arguments = this.getArguments(factDef.get(1));
            rule.addFact(factDef.get(0),arguments);
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
        rule.add(matcher.group(1));  // nombre de la fact
        rule.add(matcher.group(2)); // argumentos de la mísma
        rule.add(matcher.group(3)); // Facts por los que está conformada
        return rule;
    }

    private LinkedList<String> getParams(String paramsStr){
        LinkedList<String> params = new LinkedList<String>();
        Pattern pattern = Pattern.compile("[ ]*,[ ]*");
        String[] vParams = pattern.split(paramsStr);
        Collections.addAll(params,vParams);
        return params;
    }

    private ArrayList<String> parserFacts(String element){
        ArrayList<String> facts = new ArrayList<String>();

        String[] vFacts = this.splitFactsPattern.split(element);
        Collections.addAll(facts,vFacts);

        return facts;
    }

}
