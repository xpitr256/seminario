package demo

class Book {

    String title

    static constraints = {
        title blank:false
    }

    boolean isValidBook() {
        return !title.contains("a")
    }
}
