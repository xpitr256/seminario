package demo

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.CREATED

@Transactional
class BookService {
    def addABook(Book book) {
        if (book.isValidBook()) {
            book.save flush:true
        } else {
            throw new BookWithAViolatesDomainRuleError("Book's title cannot contains an 'a'")
        }
    }
}
