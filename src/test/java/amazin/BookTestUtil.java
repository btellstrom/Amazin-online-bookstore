package amazin;

import amazin.model.Book;

public class BookTestUtil {

    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_UPDATED = "updatedDescription";
    public static final String NAME = "Book Name";
    public static final String NAME_UPDATED = "Updated Book Name";
    public static final String AUTHOR = "Book Author";
    public static final String AUTHOR_UPDATED = "Updated Book Author";
    public static final String PUBLISHER = "Book Publisher";
    public static final String PUBLISHER_UPDATED = "Updated Book Publisher";
    public static final String PICTURE = "Picture";
    public static final String PICTURE_UPDATED = "Updated Picture";
    public static final String ISBN = "1111111111111";
    public static final String ISBN_UPDATED = "2222222222222";
    public static final int INVENTORY = 0;
    public static final int INVENTORY_UPDATED = 12;
    public static final double PRICE = 24.25;
    public static final double PRICE_UPDATED = 20;

    public static Book createModel(Long id, String name, String description, String ISBN, String picture, String author,
            String publisher, int inventory, double price) {
        Book model = new Book(id, name, description, ISBN, picture, author, publisher, inventory, price);

        return model;
    }

    public static String createRedirectViewPath(String path) {
        StringBuilder redirectViewPath = new StringBuilder();
        redirectViewPath.append("redirect:");
        redirectViewPath.append(path);
        return redirectViewPath.toString();
    }
}