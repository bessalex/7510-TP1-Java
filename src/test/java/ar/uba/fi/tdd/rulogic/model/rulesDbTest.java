package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

public class rulesDbTest {

    @InjectMocks
    private KnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.knowledgeBase = new KnowledgeBase();
        this.knowledgeBase.load("src/main/resources/rules.db");
    }

    @Test
    public void fact_padre_juan_pepe_TRUE_test() {
        Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));
    }


    @Test
    public void fact_padre_pepe_juan_FALSE_test() {
        Assert.assertFalse(this.knowledgeBase.answer("padre(pepe,juan)."));
    }


    @Test
    public void fact_padre_juan_FALSE_test() {
        Assert.assertFalse(this.knowledgeBase.answer("padre(juan)."));
    }


    @Test
    public void rule_tio_nicolas_alejandro_roberto_TRUE_test() {
        Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas, alejandro, roberto)."));
    }


    @Test
    public void rule_tio_roberto_alejandro_nicolas_FALSE_test() {
        Assert.assertFalse(this.knowledgeBase.answer("tio(roberto, alejandro, nicolas)."));
    }

}
