package demo

import grails.gorm.transactions.*

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class BookController {

    BookService bookService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Book.list(params), model:[bookCount: Book.count()]
    }

    def show(Book book) {
        if(book == null) {
            render status:404
        }
        else {
            return [book: book]
        }
    }

    @Transactional
    def save(Book book) {

        //Common controller validations: empty values, non-zero values, etc..
        if (book.hasErrors()) {
            respond book.errors, view:'create'
        } else {
            try {
                bookService.addABook(book)
                respond([book:book], status: CREATED)
            } catch (BookWithAViolatesDomainRuleError error) {
                render status:BAD_REQUEST, message: error.message
            }
        }
    }
}