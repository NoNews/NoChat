package ru.alexbykov.nochat.models;

/**
 * @author Alex Bykov
 *         Date: 23.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatDate {

    private String date;

    public NoChatDate(String date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoChatDate that = (NoChatDate) o;

        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
