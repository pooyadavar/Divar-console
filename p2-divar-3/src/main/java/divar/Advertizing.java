package divar;

import java.util.ArrayList;
import java.util.HashMap;

public class Advertizing {

    private double price;
    private String adsName;
    private Seller seller;
    private Categories group;
    private Status status;

    HashMap<Customer,ArrayList<String>> selerChat;
    HashMap<Customer,ArrayList<String>> customerChat;


    public Advertizing(double price, String adsName,Seller seller , Categories group , Status status ) {
        this.price = price;
        this.adsName = adsName;
        this.seller = seller;
        this.group = group;
        this.status = status;

    }

    public Advertizing(){

    }

    @Override
    public String toString() {
        return
                "price :" + price +
                        "\nadsName :" + adsName +
                        "\nadsSeller :" + seller.getUserName() +
                        "\ngroup : " + group +
                        "\nstatus : " + status+
                        "\n----------------------------------------------"
                ;


    }

    public double getPrice() {
        return price;
    }

    public void printAdvertizing(){
        for (Advertizing advertizing1: Data.advertizingArrayList) {
            System.out.println(advertizing1);
        }

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAdsName() {
        return adsName;
    }

    public void setAdsName(String adsName) {
        this.adsName = adsName;
    }



    public Categories getGroup() {
        return group;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setGroup(Categories group) {
        this.group = group;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
