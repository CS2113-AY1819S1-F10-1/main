package classrepo.data.person.details;

import classrepo.data.exception.IllegalValueException;
import classrepo.data.person.Printable;
import classrepo.formatter.Formatter;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address implements Printable {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person address cannot contain $";
    public static final String ADDRESS_VALIDATION_REGEX = "^((?!\\$).)*$";
    private static final String FIELD_NAME = "Address";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = address;
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String getPrintableString(boolean showPrivate) {
        return Formatter.getPrintableField(showPrivate, isPrivate, FIELD_NAME, value);
    }
}
