package amazin.service;

import amazin.model.Book;
import amazin.model.Item;
import amazin.model.ShoppingCart;
import amazin.repository.BookRepository;
//import amazin.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lauramachado on 2019-03-18.
 */
@Service
public class ShoppingCartService {

    @Autowired
    BookRepository bookRepository;
//
//    @Autowired
//    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart createCart(){
        ShoppingCart cart = new ShoppingCart();
//        shoppingCartRepository.save(cart);

        return cart;
    }

    public void addBook(ShoppingCart cart, long bookId){
        int amount = 1;
        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()){
            Item item = new Item(book.get(), amount);
            cart.addItem(item);
        }
    }

    public void incrementItem(Item i, int amount){
        i.setQuantity(i.getQuantity()+amount);
    }

    public double calculateTotal(ArrayList<Item> items){

        double total = 0;

        for(Item item: items){
            total+=(item.getBook().getPrice()*item.getQuantity());
        }

        return total;
    }

    public int itemExists(ShoppingCart cart, Long bookId) {

        ArrayList<Item> items = cart.getItems();
        for(int i=0; i < items.size(); i++){
            if(items.get(i).getBook().getId().equals(bookId)){
                return i;
            }
        }

        return -1;
    }

}
