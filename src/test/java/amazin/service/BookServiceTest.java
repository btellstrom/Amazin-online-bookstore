package amazin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amazin.BookTestUtil;
import amazin.model.Book;
import amazin.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
    @InjectMocks
    BookService service;

    @Mock
    BookRepository repository;

    private static final Long ID = 1L;

    @Test
    public void findByIdTest() {
        service.findById(ID);
        verify(repository).findById(ID);
    }

    @Test
    public void getAllTest() {
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    public void saveTest() {
        Book book = mock(Book.class);
        service.create(book);
        verify(repository).save(book);
    }

    @Test
    public void createTest() {
        Book formModel = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        service.create(formModel);

        ArgumentCaptor<Book> bookArgument = ArgumentCaptor.forClass(Book.class);
        verify(repository, times(1)).save(bookArgument.capture());
        verifyNoMoreInteractions(repository);

        Book model = bookArgument.getValue();

        assertNotNull(model.getId());
        assertEquals(formModel.getDescription(), model.getDescription());
        assertEquals(formModel.getAuthor(), model.getAuthor());
        assertEquals(formModel.getPublisher(), model.getPublisher());
        assertEquals(formModel.getISBN(), model.getISBN());
        assertEquals(formModel.getName(), model.getName());
        assertEquals(formModel.getInventory(), model.getInventory());
        assertEquals(formModel.getPrice(), model.getPrice(), 0.1);
    }

    @Test
    public void updateTest() {
        Book formModel = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME_UPDATED,
                BookTestUtil.DESCRIPTION_UPDATED, BookTestUtil.ISBN_UPDATED, BookTestUtil.PICTURE_UPDATED,
                BookTestUtil.AUTHOR_UPDATED, BookTestUtil.PUBLISHER_UPDATED, BookTestUtil.INVENTORY_UPDATED,
                BookTestUtil.PRICE_UPDATED);

        Book model = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);

        Optional<Book> optionalModel = Optional.of(model);
        when(repository.findById(formModel.getId())).thenReturn(optionalModel);

        Book actual = service.update(formModel);

        verify(repository, times(1)).save(formModel);
        verify(repository, times(1)).findById(formModel.getId());
        verifyNoMoreInteractions(repository);

        assertEquals(formModel.getDescription(), actual.getDescription());
        assertEquals(formModel.getAuthor(), actual.getAuthor());
        assertEquals(formModel.getPublisher(), actual.getPublisher());
        assertEquals(formModel.getISBN(), actual.getISBN());
        assertEquals(formModel.getName(), actual.getName());
        assertEquals(formModel.getInventory(), actual.getInventory());
        assertEquals(formModel.getPrice(), actual.getPrice(), 0.1);
    }

    @Test
    public void deleteTest() {
        Book model = BookTestUtil.createModel(BookTestUtil.ID, BookTestUtil.NAME, BookTestUtil.DESCRIPTION,
                BookTestUtil.ISBN, BookTestUtil.PICTURE, BookTestUtil.AUTHOR, BookTestUtil.PUBLISHER,
                BookTestUtil.INVENTORY, BookTestUtil.PRICE);
        Optional<Book> optionalModel = Optional.of(model);

        when(repository.findById(BookTestUtil.ID)).thenReturn(optionalModel);

        Optional<Book> optionalActual = service.delete(BookTestUtil.ID);

        assertTrue("Book must be present", optionalActual.isPresent());

        Book actual = optionalActual.get();

        verify(repository, times(1)).findById(BookTestUtil.ID);
        verify(repository, times(1)).delete(model);
        verifyNoMoreInteractions(repository);

        assertEquals(model, actual);
    }
}