package root;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercice.http.Cart;

@SpringBootTest
class CartNatixisApplicationTests {
	@Test
    public void testGetTotal() {
        Cart cart = new Cart(1, "Product A", 10.0);
        cart.setQuantity(2);
        double expectedTotal = 20.0;
        assertEquals(expectedTotal, cart.getTotal(), 0.01, "Incorrect total price calculation");
    }

    @Test
    public void testToString() {
        Cart cart = new Cart(2, "Product B", 5.5);
        cart.setQuantity(3);
        String expectedString = "Cart [id=2, name=Product B, quantity=3, price=5.5, total=16.5]";
        assertEquals(expectedString, cart.toString(), "Incorrect toString representation");
    }
}
