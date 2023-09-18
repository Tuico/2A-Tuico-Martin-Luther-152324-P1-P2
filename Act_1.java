import java.util.Scanner;

public class Act_1 {

    public static void main(String[] args){
        String username;
        Scanner input = new Scanner(System.in);
        Info name = new Info();

        System.out.print("Please ENTER your name: ");
        username = input.nextLine();
        name.Info(username);
    }
}
