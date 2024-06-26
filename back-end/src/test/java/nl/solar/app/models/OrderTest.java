package nl.solar.app.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the Order model.
 * This class tests the Order model by creating a dummy order and checking if the values are correct.
 *
 * @author Julian Kruithof
 */
public class OrderTest {

    @Test
    public void testCreateDummyOrder() {
        final LocalDateTime MINIMUM_START = LocalDateTime.now().minusMonths(2);
        final LocalDateTime MAXIMUM_END = LocalDateTime.now().plusMonths(4);

        Warehouse warehouse = new Warehouse(1000, "Wibauthuis", "Wibautstraat 3b, 1091 GH Amsterdam");
        Order order = Order.createDummyOrder(warehouse);

        assertEquals(0, order.getId(), "Order id should be 0, since it will be generated by the database");
        assertEquals("Ordered ... for Wibauthuis", order.getTag(), "tag message should contain warehouse name");
        assertEquals(warehouse, order.getWarehouse(), "Warehouse should be the same as the one passed in");

        assertEquals(0, order.getItems().size(), "Order should not contain any products");
        assertThat(order.getOrderDate(), is(greaterThanOrEqualTo(MINIMUM_START)));
        assertThat(order.getOrderDate(), is(lessThanOrEqualTo(MAXIMUM_END)));

        assertThat(order.getDeliverDate(), is(greaterThanOrEqualTo(order.getOrderDate().toLocalDate())));
        assertThat(order.getDeliverDate(), is(lessThanOrEqualTo(MAXIMUM_END.toLocalDate())));
    }
}
