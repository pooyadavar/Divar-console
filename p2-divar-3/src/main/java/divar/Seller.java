package divar;

import java.util.*;
import java.util.stream.IntStream;

public class Seller extends User {

    public static ArrayList<Chat> userChat = new ArrayList<>();

    private  static Advertizing advertizing = new Advertizing();
    private String phoneNumber;
    private String email;
    private double wallet;
    private int x;
    private int y;

    /**
     * Creates a new Seller object with the given parameters.
     * @param userName The username of the seller.
     * @param password The password of the seller.
     * @param phoneNumber The phone number of the seller.
     * @param email The email address of the seller.
     * @param wallet The initial balance of the seller's wallet.
     * @param x The x-coordinate of the seller's location.
     * @param y The y-coordinate of the seller's location.
     */

    public Seller(String userName, String password, String phoneNumber, String email, double wallet, int x, int y) {
        super(userName,password);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
        this.x = x;
        this.y = y;
    }

    public Seller(){

    }

    /**
     * Displays the seller menu and allows the user to perform various actions, such as viewing their profile, managing ads, using the chat system, etc.
     *
     * @param seller The current user.
     */
    public static void sellerMenu(Seller seller) {
        Run run = new Run();
        Chat chat = new Chat();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. profile :");
        System.out.println("2. available ads: ");
        System.out.println("3. add ads:");
        System.out.println("4. chats:");
        System.out.println("0. back");
        System.out.println("------------------------------------------");
        System.out.println("choose :");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                seller.showProfile(seller);
                break;

            case 2:
                seller.showAvailableAdvertizing(seller);
                break;

            case 3:
                int x=5;            //random Number for loop
                while(x!=2){
                    addAdvertisingMenu(seller);
                    System.out.println("Do you want add new advertizing? ");
                    System.out.println("1.Yes");
                    System.out.println("2.No & back");
                    x = scanner.nextInt();
                }
                sellerMenu(seller);
            case 4:
                seller.useChat(seller);
            case 0:
                run.printStartMenu();
                break;

        }

    }

    /**
     * Allows the user to interact with other users using a chat system.
     *
     * @param seller The current user.
     */
    private void useChat(Seller seller) {
        Scanner scanner =  new Scanner(System.in);
        int j=1;
        IntStream.range(0, userChat.size())
                .forEach(i -> System.out.println((i+1) + ". " + userChat.get(i).getSender().getUserName()));

        System.out.println("Choose a User :");
        int index = scanner.nextInt() - 1;
        seller.chat(seller,userChat.get(index));
    }

    /**
     * Displays the chat history between the current user and another user, and allows the current user to send a new message.
     *
     * @param seller The current user.
     * @param chat The chat object representing the chat history between the current user and another user.
     */
    private void chat(Seller seller, Chat chat) {
        Scanner scanenr= new Scanner(System.in);
        chat.getChatMassage().stream()
                .forEach(string -> {
                    String userName = chat.getReceiver().getUserName().equals(seller.getUserName())
                            ? chat.getSender().getUserName()
                            : chat.getReceiver().getUserName();
                    System.out.println(userName + " : " + string);
                });

        System.out.println("enter your massage:");
        String input = scanenr.nextLine();
        chat.setChatMassage(input);
        System.out.println("\n<< your massage sent >>");
        sellerMenu(seller);
    }

    /**
     * Displays a list of available advertisements to the current user.
     *
     * @param seller The current user.
     */
    private void showAvailableAdvertizing(Seller seller) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------< AVAILABLE ADVERTIZING >---------");

        IntStream.range(0, Data.advertizingArrayList.size())
                .forEach(i -> System.out.println((i + 1) + ". " + Data.advertizingArrayList.get(i)));

        if(Data.advertizingArrayList == null){
            System.out.println("your Request list is Empty!!");

        }
        System.out.println("0. back");
        int x = scanner.nextInt();
        if(x==0){
            sellerMenu(seller);
        }
    }

    /**
     * Displays the user's profile to the current user.
     *
     * @param seller The current user.
     */
    private void showProfile(Seller seller) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("user name : " + seller.getUserName());
        System.out.println("password : ********");
        System.out.println("Email : " + seller.email);
        System.out.println("phone number : " + seller.phoneNumber);
        System.out.println("wallet : "+ seller.getWallet());
        System.out.println("------------------------------------------");
        System.out.println("do you want show your password ?");
        System.out.println("1.yes");
        System.out.println("2.No");
        int num = scanner.nextInt();
        if (num == 1) {
            System.out.println("password : " + seller.getPassword());
            System.out.println("0. back :");
            int x = scanner.nextInt();
            if (x == 0) {
                sellerMenu(seller);
            }
        } else {
            sellerMenu(seller);
        }
    }

    /**
     * Allows the seller to add a new advertising.
     *
     * @param seller The current user.
     */
    private static void addAdvertisingMenu(Seller seller) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the (price) of the product: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the (advertizing name) of the product: ");
        String adsName = scanner.nextLine();
        System.out.println("Enter the (advertizing Seller) of the product: " + seller.getUserName());
        System.out.println("\nEnter the (advertizing Group) of the product:\n(Phone,HomeAppliances,Stationery,Car,Clothes)");
        String adsGroup = scanner.nextLine();
        Categories adsGroupEnum = Categories.valueOf(adsGroup);

        Advertizing advertizingObj  = new Advertizing(price,adsName,seller,adsGroupEnum,Status.Ready_To_Sell);
        Data.requesedAdvertizing.add(advertizingObj);
    }

    /**
     * Checks if the given username exists in the system.
     *
     * @param userName The username to check.
     * @return true if the username does not exist in the system, false otherwise.
     */
    public boolean check_userName(String userName){                                             // check username
        boolean temp = true;
        Data data=new Data();
        for(Seller seller : data.sellers){
            if(seller.getUserName().equals(userName)){
                temp = false;
            }
        }
        return  temp;
    }

    /**
     * Returns a string representation of the seller.
     * The string contains the username, password, phone number, email, and wallet.
     *
     * @return A string representation of the seller.
     */
    @Override
    public String toString() {
        return  "userName = " + getUserName() +
                "\npassword = " + getPassword() +
                "\nphoneNumber = " + phoneNumber +
                "\nemail = " + email +
                "\nwallet=" + wallet +
                "\n--------------------------";

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getWallet() {
        return  wallet;
    }

    public void setWallet(double wallet) {
        this.wallet =  wallet;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
