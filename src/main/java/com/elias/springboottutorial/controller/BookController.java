package com.elias.springboottutorial.controller;

import com.elias.springboottutorial.dto.BookDto;
import com.elias.springboottutorial.entity.Book;
import com.elias.springboottutorial.service.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<BookDto> findAll() {
        List<Book> books = bookService.getAllBooks();

        // add one book
        Book b = new Book();
        b.setAuthor("Elias");
        books.add(b);

        // Set used for all books
        List<BookDto> booksDto = books
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        for(BookDto bookDto:booksDto)
            bookDto.setUsed(true);

        return booksDto;
    }

/*    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }*/

    private BookDto convertToDto(Book book){
        BookDto bookDto = modelMapper.map(book,BookDto.class);
        return bookDto;
    }

    private Book convertToEntity(BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        return  book;
    }

    /*@GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }*/
}
