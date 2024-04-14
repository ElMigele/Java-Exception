import java.util.Scanner;

public class View {
    Scanner scanner;
    
    public View(){
        scanner = new Scanner(System.in);
    }   

    public String GetString(String s){
        System.out.println(s);
        return scanner.nextLine();
    }

    public boolean GetConfirm(){
        System.out.println("Continue work? enter 'y' to continue");
        String enter = scanner.nextLine();
        return enter.equals("y");
    }
}