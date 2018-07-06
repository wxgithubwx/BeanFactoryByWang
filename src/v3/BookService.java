package v3;


public class BookService {
    public void addBook(){
        BookDaoFactory.getBookDao().addBook();
    }
}
