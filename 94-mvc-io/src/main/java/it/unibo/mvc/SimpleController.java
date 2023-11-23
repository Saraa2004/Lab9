package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String string;
    private final List<String> printedStrings = new LinkedList<>();

    @Override
    public void setString(String input) {
        Objects.requireNonNull(input, "This method doesn't accept null values");
        string = input;
    }

    @Override
    public String getString() {
        return this.string;
    }

    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(printedStrings);
    }

    @Override
    public void printString() {
        if (this.string == null) {
            throw new IllegalStateException("The string is unset");
        }
        System.out.println(this.string);
        printedStrings.add(this.string);                
    }
}
