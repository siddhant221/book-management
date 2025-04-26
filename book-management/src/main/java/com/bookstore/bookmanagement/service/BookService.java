package com.bookstore.bookmanagement.service;

import com.bookstore.bookmanagement.dto.BookRequestDto;
import com.bookstore.bookmanagement.dto.BookResponseDto;
import com.bookstore.bookmanagement.exception.ResourceNotFoundException;
import com.bookstore.bookmanagement.model.Book;
import com.bookstore.bookmanagement.repository.BookRepo;
import com.bookstore.bookmanagement.utility.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor    //spring automatically generates constructor(BookRepository bookRepository)
/*by @RequiredArgsConstructor we are doing here is constructor injection
other way was writing @Autowired on the repository

    //this is field injection and it is not preferred because it is harder to test hidden and no final fields
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
*/
public class BookService
{
    private final BookRepo bookRepo;    //book repo is injected here
    private final BookMapper bookMapper;

    public BookResponseDto saveBook(BookRequestDto requestDto)
    {
        /*
        1. convert the incoming dto (pure api object)
        2. into a book entity that jpa can persist
        3. bookMapper.mapToEntity() copies the fields and handles any enum/string
        * */
        Book book = bookMapper.mapToEntity(requestDto);

        /*
        1. ask the repository to save the entity
        2. spring data jpa generates an INSERT or UPDATE
        3. the database assigns the auto-incremented id
        4. save() return the managed entity to the new id
        * */
        Book saved = bookRepo.save(book);

        /*
        1. convert the persisted entity back to response dto
        2. so client sees the genreated id and final stored value
        3. this keeps my api contract (DTO) separate from the internal entity.
        * */
        return bookMapper.mapToResponse(saved);
    }


    //get all the books
    public List<BookResponseDto> getAllBooks()
    {
        return bookRepo.findAll()
                .stream()
                .map(bookMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    //get all book by id
    public BookResponseDto getBookById(Long id )
    {
        Book book = bookRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book",id));
        return bookMapper.mapToResponse(book);
    }

    //update book(Long id, BookRequestDto dto)
    public BookResponseDto updateBook(Long id,BookRequestDto dto)
    {
        Book book = bookRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book",id));

        //update the book
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setType(dto.getType());
        book.setGenre(dto.getGenre());
        book.setRating(dto.getRating());
        book.setIsbnNumber(dto.getIsbnNumber());

        Book updatedBook = bookRepo.save(book);
        return bookMapper.mapToResponse(updatedBook);

    }

    //delete book
    public boolean deleteBookById(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            bookRepo.deleteById(id);
            return true;
        }
        return false;
    }


}
