import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TermTest {

    @org.junit.jupiter.api.Test
    void testInvalidTermValueThrowsException() {
        try {
            new Term(null, 10);
            fail("An exception should be thrown when the term value is null");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }
    }

    @org.junit.jupiter.api.Test
    void testInvalidTermWeightThrowsException() {
        try {
            new Term("Tiger", -10);
            fail("An exception should be thrown when the term weight is negative");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Term t = new Term("Elephant", 10);
        assertEquals("10\tElephant", t.toString());
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        Term t = new Term("Elephant", 10);
        assertEquals(10, t.getWeight());
    }

    @org.junit.jupiter.api.Test
    void getTerm() {
        Term t = new Term("Elephant", 10);
        assertEquals("Elephant", t.getTerm());
    }

    @org.junit.jupiter.api.Test
    void setWeight() {
        Term t = new Term("Elephant", 10);
        t.setWeight(99);
        assertEquals(99, t.getWeight());
    }

    @org.junit.jupiter.api.Test
    void setTerm() {
        Term t = new Term("Elephant", 10);
        String returnValue = t.setTerm("Tiger");
        assertEquals("Tiger", returnValue);
        assertEquals("Tiger", t.getTerm());
    }

    @org.junit.jupiter.api.Test
    void testComparingTermsWithSameWeight() {
        assertEquals(0, new Term("Elephant", 10).compareTo(new Term("Elephant", 10)));
        assertTrue(new Term("Cat", 10).compareTo(new Term("Elephant", 10)) < 0);
        assertTrue(new Term("Elephant", 10).compareTo(new Term("Cat", 10)) > 0);
    }

    @org.junit.jupiter.api.Test
    void testComparingTermsWithDifferentWeight() {
        assertTrue(new Term("Elephant", 10).compareTo(new Term("Elephant", 20)) < 0);
        assertTrue(new Term("Elephant", 10).compareTo(new Term("Elephant", 5)) > 0);
    }

    @org.junit.jupiter.api.Test
    void testSortByWeight() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("Elephant", 10));
        terms.add(new Term("Tiger", 100));
        terms.add(new Term("Lion", 50));
        terms.add(new Term("Zebra", 24));
        terms.add(new Term("Cheetah", 101));

        Collections.sort(terms, ITerm.byReverseWeightOrder());

        assertEquals("Cheetah", terms.get(0).getTerm());
        assertEquals("Tiger", terms.get(1).getTerm());
        assertEquals("Lion", terms.get(2).getTerm());
        assertEquals("Zebra", terms.get(3).getTerm());
        assertEquals("Elephant", terms.get(4).getTerm());
    }

    @org.junit.jupiter.api.Test
    void testSortByPrefix() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("Cattle", 10));
        terms.add(new Term("Cat", 100));
        terms.add(new Term("Caterpillar", 50));
        terms.add(new Term("Catty", 24));
        terms.add(new Term("Zilly", 2));

        Collections.sort(terms, ITerm.byPrefixOrder(4));
        assertEquals("Cat",      terms.get(0).getTerm());
        assertEquals("Caterpillar",       terms.get(1).getTerm());

        // Since Catty and Cattle both start with the same prefix for r=4, they
        // can be in either order
        assertTrue(terms.get(2).getTerm().equals("Catty") || terms.get(2).getTerm().equals("Cattle"));
        assertTrue(terms.get(3).getTerm().equals("Catty") || terms.get(3).getTerm().equals("Cattle"));

        assertEquals("Zilly",       terms.get(4).getTerm());
    }

    @org.junit.jupiter.api.Test
    void testInvalidNodeThrowsException() {
        try {
            new Node(null, 10);
            fail("An exception should be thrown when the Node value is null");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }

        try {
            new Node("Cat", -10);
            fail("An exception should be thrown when the Node weight is negative");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }
    }
}