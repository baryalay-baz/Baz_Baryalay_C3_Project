import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    public RestaurantServiceTest() {
        addRestaurants();
    }
    private void addRestaurants(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Spagetti Bolognese",400);
        restaurant.addToMenu("Fish and Chips", 300);
        service.addRestaurant(restaurant);
    }
    private void addOrderItems() throws duplicateItemException{
        //Adding order items

        service.clearOrder();

        String sOrderItems[] = {"Vegetable lasagne","Spagetti Bolognese", "Fish and Chips"};
        for(String ItemName:sOrderItems){
            service.addOrderItem(ItemName, restaurant);
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        //ARRANGE


        //ACT
        Restaurant rest = service.findRestaurantByName("Amelie's cafe");

        //ASSERT
        assertEquals(rest.getName(),restaurant.getName());

        //DONE BY BAZ
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() {
        //WRITE UNIT TEST CASE HERE

        //ARRANGE


        //ACT


        //ASSERT
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("wrong name"));


        //DONE BY BAZ

    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        //ARRANGE


        //ACT
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");

        //ASSERT
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() {

        //ARRANGE

        //ACT

        //ASSERT
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        //ARRANGE

        //ACT
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));

        //ASSERT
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>


    ///>>>>>>>>>>>>>>>>>>> TESTS DONE BY BAZ <<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING REMOVING ORDER ITEMS><<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void check_if_orderitem_is_already_available_in_the_order_items_list() throws duplicateItemException {
        //ARRANGE
        addOrderItems();

        //ACT

        //ASSERT
        assertThrows(duplicateItemException.class,()->service.addOrderItem("Fish and Chips",restaurant));
        //DONE BY BAZ
    }
    @Test
    public void check_if_adding_orderitem_increases_the_list_size_by_1() throws duplicateItemException {
        //ARRANGE
        addOrderItems();

        //ACT
        int initialNumberOfItems = service.getOrderItems().size();
        service.addOrderItem("Sweet corn soup",restaurant);


        //ASSERT
        assertEquals(initialNumberOfItems + 1,service.getOrderItems().size());

        //DONE BY BAZ
    }
    @Test
    public void check_if_removing_order_item_decreases_the_list_size_by_1() throws itemNotFoundException, duplicateItemException {
        //ARRANGE
        addOrderItems();

        //ACT
        int initialNumberOfItems = service.getOrderItems().size();
        service.removeOrderItem("Fish and Chips");

        //ASSERT
        assertEquals(initialNumberOfItems - 1,service.getOrderItems().size());

        //DONE BY BAZ
    }
    @Test
    public void check_if_item_to_be_removed_is_not_found_in_orderlist_throws_exception() throws duplicateItemException {
        //ARRANGE
        addOrderItems();

        //ACT

        //ASSERT
        assertThrows(itemNotFoundException.class,()->service.removeOrderItem("incorrect item name"));

    }
    //<<<<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING REMOVING ORDER ITEMS - END>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>ADMIN: ORDER PRICE <<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void check_if_total_price_matches_expected_price() {
        //ARRANGE

        //ACT
        int nExpectedPrice = 969; //Total Price of Items added by addOrderItems()

        //ASSERT
        assertEquals(nExpectedPrice, service.getOrderPrice());

        //ASSERTING BY SENDING ITEM NAMES AS PARAMETERS TO OVERLOADED getOrderPrice Method
        String sOrderItems[] = {"Vegetable lasagne","Spagetti Bolognese", "Fish and Chips"};
        assertEquals(nExpectedPrice, service.getOrderPrice(sOrderItems,restaurant));
    }

}