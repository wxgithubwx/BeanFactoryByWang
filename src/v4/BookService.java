package v4;


public class BookService {
    IBookDao bookDao = (IBookDao)BeanFactory.getBean("bookDao");
    public void addBook(){
        bookDao.addBook();
    }
}
