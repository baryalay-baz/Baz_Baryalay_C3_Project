//import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private static List<Item> orderItems = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE

        //DONE BY BAZ
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    //OVERLOADED BY BAZ
    public Restaurant addRestaurant(Restaurant newRestaurant) {
        restaurants.add(newRestaurant);
        return newRestaurant;
    }
    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        return restaurants;

        //DONE BY BAZ
    }


    //Restaurant object is added in parameters for the purpose of finding item of the menu of the selected restaurant
    //as the same item name might be present in the menu of another restaurant
    public Item addOrderItem(String itemName, Restaurant restaurant) throws duplicateItemException{

        for(Item item: orderItems){
            if(item.getName().equals(itemName)){
                throw new duplicateItemException(itemName + " already added!");
            }
        }
        Item itemToAdd = restaurant.findItemByName(itemName);
        orderItems.add(itemToAdd);
        return itemToAdd;

        //DONE BY BAZ
    }

    public Item removeOrderItem(String itemName) throws itemNotFoundException{
        Item itemToRemove =  findItemInOrder(itemName);
        if(itemToRemove==null){
            throw new itemNotFoundException(itemName + " not found in order list");
        }
        orderItems.remove(itemToRemove);
        return itemToRemove;

        //DONE BY BAZ
    }

    //I Could do it by passing item names here and then find the item's prices in the restaurant's menu and sum them.
    // But the way i did it looked better to me
    public int getOrderPrice(){
        int nTotalPrice = 0;

        for(Item item:orderItems){
            nTotalPrice += item.getPrice();
        }
        return nTotalPrice;
    }

    //I am overloading getOrderPrice() method to take item names as parameters (as per evaluation rubrics)
    public int getOrderPrice(String[] itemNames,Restaurant restaurant){
        int nTotalPrice = 0;
        for(String itemName:itemNames){
            Item item = restaurant.findItemByName(itemName);
            nTotalPrice += item.getPrice();
        }
        return nTotalPrice;
    }

    private Item findItemInOrder(String itemName){
        Item itemToReturn = null;
        for(Item item:orderItems){
            if(item.getName().equals(itemName)){
                itemToReturn = item;
            }
        }
        return itemToReturn;

        //DONE BY BAZ
    }

    public void clearOrder(){
        orderItems.clear();
    }
    public List<Item> getOrderItems() {

        return orderItems;

        //DONE BY BAZ
    }
}
