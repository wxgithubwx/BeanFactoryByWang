package v3;

/**
 * 工厂类
 */
public class BookDaoFactory {
    public static IBookDao getBookDao(){
        /*return new BookDaoImpl();*/
        return new BookDaoImpl2();
        }
    }
