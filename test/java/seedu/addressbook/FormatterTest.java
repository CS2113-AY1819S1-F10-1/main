package seedu.addressbook;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.ui.Formatter;

public class FormatterTest {
    private static Formatter formatter = new Formatter();
    private static String LS = System.lineSeparator();


    @Test
    public void formatStringChain(){

        assertEquals(" A" + LS + " B" + LS + " C" + LS, formatter.format("A", "B", "C"));
        assertEquals(" A" + LS + " B" + LS + " C" + LS + " D" + LS, formatter.format("A", "B\nC", "D"));
    }

    @Test
    public void formatPerson() throws Exception{
        TestDataHelper helper = new TestDataHelper();
        Person toBePrinted = helper.adam();
        final String expected = " \t1. Adam Brown  Email: adam@gmail.com   Tags: [tag1][tag2]"+ LS + " " + LS;
        List<Person> persons = new ArrayList<>();
        persons.add(toBePrinted);
        assertEquals(expected, formatter.format(persons));
    }


    @Test
    public void formatPersons() throws Exception{
        TestDataHelper helper = new TestDataHelper();
        List<Person> persons = helper.generatePersonList(false);
        String expected = " \t1. Person 1 Phone: 1 Email: 1@email Address: House of 1  Tags: [tag1][tag2]"+ LS + " " + LS;
        assertEquals(expected, formatter.format(persons));

        /**List of 3 people, all fields non-private**/
        persons = helper.generatePersonList(false, false , false);
        expected = " \t1. Person 1 Phone: 1 Email: 1@email Address: House of 1  Tags: [tag1][tag2]"+ LS
                + " \t2. Person 2 Phone: 2 Email: 2@email Address: House of 2  Tags: [tag2][tag3]"+ LS
                + " \t3. Person 3 Phone: 3 Email: 3@email Address: House of 3  Tags: [tag4][tag3]"+ LS + " " + LS;
        assertEquals(expected, formatter.format(persons));

        /**List of 3 people, all fields non-private**/
        persons = helper.generatePersonList(false, true , false);
        expected = " \t1. Person 1 Phone: 1 Email: 1@email Address: House of 1  Tags: [tag1][tag2]"+ LS
                + " \t2. Person 2     Tags: [tag2][tag3]"+ LS
                + " \t3. Person 3 Phone: 3 Email: 3@email Address: House of 3  Tags: [tag4][tag3]"+ LS + " " + LS;
        assertEquals(expected, formatter.format(persons));
    }
}
