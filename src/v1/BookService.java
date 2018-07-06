package v1;

public class BookService {
    BookDao dao1=new BookDao();
    BookDao2 dao2=new BookDao2();
    public void addBook(){
        //dao1.addBook();
        dao2.addBook();
    }
}
