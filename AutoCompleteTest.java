import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutoCompleteTest {

    @org.junit.jupiter.api.Test
    void testIsValidWord() {
        assertTrue(Autocomplete.isValidWord("Elephant"));
        assertFalse(Autocomplete.isValidWord(""));
        assertFalse(Autocomplete.isValidWord("Cat2"));
        assertFalse(Autocomplete.isValidWord("Cat!"));
        assertFalse(Autocomplete.isValidWord("C@t"));
        assertFalse(Autocomplete.isValidWord("$@t"));
        assertFalse(Autocomplete.isValidWord(" "));
    }

    @org.junit.jupiter.api.Test
    void testAutocompleteSuggestions() {
        Autocomplete auto = new Autocomplete();
        auto.buildTrie("test1.txt", 3);

        List<ITerm> suggestions = auto.getSuggestions("a");
        assertEquals(4, suggestions.size());

        // Because the references are alphabetical from a -> z
        // we expect the suggestions to be alphabetical as well
        assertEquals("a", suggestions.get(0).getTerm());
        assertEquals("an", suggestions.get(1).getTerm());
        assertEquals("answer", suggestions.get(2).getTerm());
        assertEquals("any", suggestions.get(3).getTerm());

        suggestions = auto.getSuggestions("the");
        assertEquals(3, suggestions.size());
        assertEquals("the", suggestions.get(0).getTerm());
        assertEquals("their", suggestions.get(1).getTerm());
        assertEquals("there", suggestions.get(2).getTerm());
    }

    @org.junit.jupiter.api.Test
    void testAutocompleteNoSuggestions() {
        Autocomplete auto = new Autocomplete();
        auto.buildTrie("test1.txt", 3);

        List<ITerm> suggestions = auto.getSuggestions("mal");
        assertEquals(0, suggestions.size());
    }
}