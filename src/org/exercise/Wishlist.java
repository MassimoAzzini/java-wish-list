package org.exercise;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Wishlist {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        List<Gift> wishList = new ArrayList<>();

        boolean end = false;

        while(!end){
            System.out.println("Do you want to add a new gift(Y/N)?");
            String choice = scan.nextLine();

            switch (choice.trim().toLowerCase()){
                case "y":
                    String giftName;

                    do {
                        System.out.println("Name of the gift?");
                        giftName = scan.nextLine().trim();
                        if (giftName.isEmpty()) System.out.println("You have to write something");

                    } while (giftName.isEmpty());


                    Gift gift = new Gift(giftName);
                    wishList.add(gift);
                    break;

                case "n":
                    end = true;
                    break;

                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }

            System.out.println("You have added " + wishList.size() + " gifts");
        }

        wishList.sort(Comparator.comparing(Gift::getName));
        System.out.println(wishList);

//        Iterator<Gift> iterator = wishList.iterator();
//        while (iterator.hasNext()){
//            Gift thisGift = iterator.next();
//            System.out.println(thisGift.toString());
//        }

        try (FileWriter fileWriter = new FileWriter("./resources/wishList.txt")){
            Iterator<Gift> iterator = wishList.iterator();
            while (iterator.hasNext()){
                Gift thisGift = iterator.next();
                fileWriter.write(thisGift.toString());
            }
        } catch (IOException e){
            System.out.println("Unable to open file");
        }

        scan.close();
    }
}
