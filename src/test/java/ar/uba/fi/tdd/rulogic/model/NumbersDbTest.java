package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

public class NumbersDbTest {

    @InjectMocks
    private KnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.knowledgeBase = new KnowledgeBase();
        this.knowledgeBase.load("src/main/resources/numbers.db");
    }

    @Test
    public void fact_add_one_one_two_TRUE_test() {
        Assert.assertTrue(this.knowledgeBase.answer("add(one, one, two)."));
    }


    @Test
    public void fact_add_two_one_one_FALSE_test() {
        Assert.assertFalse(this.knowledgeBase.answer("add(two, one, one)."));
    }

    @Test
    public void fact_add_one_two_zero_TRUE_test() {
        Assert.assertTrue(this.knowledgeBase.answer("add(one, two, zero)."));
    }

    @Test
    public void fact_add_zero_two_one_FALSE_test() {
        Assert.assertFalse(this.knowledgeBase.answer("add(zero, two, one)."));
    }


    @Test
    public void rule_subtract_one_one_two_FALSE_test() {
        Assert.assertFalse(this.knowledgeBase.answer("subtract(one, one, two)."));
    }


    @Test
    public void rule_subtract_two_one_one_TRUE_test() {
        Assert.assertTrue(this.knowledgeBase.answer("subtract(two, one, one)."));
    }

}
