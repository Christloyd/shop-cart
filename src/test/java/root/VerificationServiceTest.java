package root;

import com.exercice.http.Cart;
import com.exercice.services.VerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VerificationServiceTest {

    private VerificationService verificationService;

    @BeforeEach
    public void setUp() {
        verificationService = new VerificationService();
    }

    @Test
    public void testVerificationWithInvalidCartName() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(3, "", 10.0));
        Exception exception = assertThrows(Exception.class, () -> verificationService.Verification(carts));
        assertTrue(exception.getMessage().contains("Name is required FROM 3"), "Incorrect error message for invalid cart name");
    }

    @Test
    public void testVerificationWithInvalidCartPrice() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(4, "Product C", -5.0));
        Exception exception = assertThrows(Exception.class, () -> verificationService.Verification(carts));
        assertTrue(exception.getMessage().contains("Invalid price: -5.0 FROM 4"), "Incorrect error message for invalid cart price");
    }


    @Test
    public void testVerificationWithEmptyCarts() throws Exception {
        List<Cart> carts = new ArrayList<>();
        double expectedTotal = 0.0;
        double actualTotal = verificationService.Verification(carts);
        assertEquals(expectedTotal, actualTotal, 0.01, "Incorrect total price for empty carts");
    }
}
