package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class FactMakerTest {
    @InjectMocks
    private KnowledgeBase db;
    private Factory factory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.factory = new Factory();
        this.db = new KnowledgeBase();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void crearFact_OK_Test() {

        DbElement elem = this.factory.make("Mujer(Nadia).", db);
        Assert.assertEquals(elem.getName(), "Mujer");
    }

    @Test
    public void crearFact_NullPointerException_Test(){
        try {
            DbElement elem = this.factory.make("Fact((error)).",db);
            Assert.fail("Debe dar NullPointerException");
        }catch(Exception e){
            Assert.assertEquals(IllegalArgumentException.class,e.getClass());
        }
    }


    @Test
    public void crearFact_MultiplesArgumentos_Test(){
        DbElement elem = this.factory.make("River(Saracchi,Maidana,Pinola,Moreira).",db);
        Assert.assertEquals(elem.getName(), "River");
    }


    @Test
    public void isFact_OK_Test(){
        FactMaker factMaker = new FactMaker();
        Assert.assertTrue(factMaker.isFact("River (  Perez,  Ponzio, Rojas )."));
    }


    @Test
    public void isFact_False_Test(){
        FactMaker factMaker = new FactMaker();
        Assert.assertFalse(factMaker.isFact("River(Perez,  Ponzio  Rojas )."));
    }
}