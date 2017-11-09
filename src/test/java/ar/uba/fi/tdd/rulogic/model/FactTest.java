package ar.uba.fi.tdd.rulogic.model;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class FactTest {
    @InjectMocks
    private Fact fact;
    private LinkedList<String> args;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        args = new LinkedList<String>();
        Collections.addAll(args,"Javier","Pedro");
        this.fact = new Fact("2doNombre",args);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName_nombre_Correcto_test() {
        Assert.assertEquals("2doNombre",this.fact.getName());
    }

    @Test
    public void getName_nombre_Incorrecto_test() {
        Assert.assertNotEquals("doNombre",this.fact.getName());
    }

    @Test
    public void getArguments_correctos_test() {
        LinkedList<String> args = this.fact.getArguments();
        Assert.assertTrue(this.args.equals(args));
    }

    @Test
    public void getArguments_Incorrectos_test() {
        LinkedList<String> errArgs = new LinkedList<String>();
        Collections.addAll(errArgs,"Pablo","Cesar");
        LinkedList<String> args = this.fact.getArguments();
        Assert.assertFalse(errArgs.equals(args));
    }

    @Test
    public void evaluate_Cant_Param_distinto_test() {
        KnowledgeBase db = new KnowledgeBase();
        LinkedList<String> queryArgs = new LinkedList<String>();
        Collections.addAll(queryArgs,"German", "Adrian", "Ramón");

        Assert.assertFalse(this.fact.evaluate("2doNombre",queryArgs,db));
    }


    @Test
    public void evaluate_nombre_Erróneo_test() {
        KnowledgeBase db = new KnowledgeBase();
        LinkedList<String> queryArgs = new LinkedList<String>();
        Collections.addAll(queryArgs,"Javier","Pedro");

        Assert.assertFalse(this.fact.evaluate("2Nombre",queryArgs,db));
    }


    @Test
    public void evaluate_OK_test() {
        KnowledgeBase db = new KnowledgeBase();
        LinkedList<String> queryArgs = new LinkedList<String>();
        Collections.addAll(queryArgs,"Javier","Pedro");

        Assert.assertTrue(this.fact.evaluate("2doNombre",queryArgs,db));
    }
}
