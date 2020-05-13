package book;

import book.model.Book;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;
import user.UserDao;
import user.model.User;

public class Main
{
    public static void main(String[] args)
    {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);

        BookGenerator bookGenerator = new BookGenerator();

        for (int i = 0; i < 1000; i++)
        {
            Book tempBook = bookGenerator.getGeneratedBook();
            bookDao.persist(tempBook);
        }

        bookDao.findAll().forEach(System.out::println);
    }
}
