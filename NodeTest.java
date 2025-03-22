import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

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

    @org.junit.jupiter.api.Test
    void testNodeDefaultConstructor() {
        Node n = new Node();
        assertEquals("", n.getTerm().getTerm());
        assertEquals(0, n.getTerm().getWeight());
        assertEquals(26, n.getReferences().length);
    }

    @org.junit.jupiter.api.Test
    void testNodeConstructor() {
        Node n = new Node("Tiger", 20);
        assertEquals("Tiger", n.getTerm().getTerm());
        assertEquals(20, n.getTerm().getWeight());
        assertEquals(26, n.getReferences().length);
    }

    @org.junit.jupiter.api.Test
    void testNodeSetTerm() {
        Node n = new Node("Tiger", 20);
        n.setTerm(new Term("Donkey", 99));
        assertEquals("Donkey", n.getTerm().getTerm());
        assertEquals(99, n.getTerm().getWeight());
        assertEquals(26, n.getReferences().length);
    }

    @org.junit.jupiter.api.Test
    void testNodeGetWords() {
        Node n = new Node("Tiger", 20);
        n.setWords(10);
        assertEquals(10, n.getWords());
    }

    @org.junit.jupiter.api.Test
    void testNodeGetPrefixes() {
        Node n = new Node("Tiger", 20);
        n.setPrefixes(10);
        assertEquals(10, n.getPrefixes());
    }
}