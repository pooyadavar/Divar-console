package divar;

import java.util.ArrayList;

public class Chat {
    private User sender;
    private User receiver;
    private ArrayList<String> chatMassage= new ArrayList<>();

    public Chat(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public ArrayList<String> getChatMassage() {
        return chatMassage;
    }

    public void setChatMassage(String string) {
        chatMassage.add(string);
    }


    public Chat(){

    }



}
