package divar;

import java.util.Scanner;

public class Guest {
    public static void guestMenu() {
        Advertizing advertizing = new Advertizing();
        Scanner scanner = new Scanner(System.in);
        Run run = new Run();
        System.out.println("-----------<ADVERTIZING>-----------");
        advertizing.printAdvertizing();
        System.out.println("0. back");
        int x = scanner.nextInt();
        if(x==0){
            run.printStartMenu();
        }

    }
}
