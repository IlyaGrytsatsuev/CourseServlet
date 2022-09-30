import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class AdminList {
    private HashMap<String, String> users ;

    public AdminList(){
        users = new HashMap<>();
    }

    public boolean CheckUser(String name, String password){

        for (String key : users.keySet()) {
            if(key.equals(name) && users.get(key).equals(password))
                return true ;
        }
        return false;
    }

    public void readFile() throws Exception{
        String path = "/Users/gratchuvalsky/Desktop/AuthList.txt";
        File list = new File(path);
        Scanner sc = new Scanner(list);

        while (sc.hasNextLine()){
            String [] tmp = sc.nextLine().split(" ");
            users.put(tmp[0], tmp[1]);
        }
    }

}
