package v2;


public class BookService {
    IBookDao  dao1=new BookDaoImpl();
    IBookDao  dao2=new BookDaoImpl2();
    public void addBook(){
        //dao1.addBook();
        dao2.addBook();
    }
}
