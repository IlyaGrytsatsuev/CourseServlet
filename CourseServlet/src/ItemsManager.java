import  java.util.*;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ItemsManager {
    private LinkedHashMap<Integer, String> items;
    private LinkedHashMap<Integer, Integer> itemsNum;
    private LinkedHashMap<Integer, Integer> prices;
    private LinkedHashMap<Integer, String> file_paths;
    private ArrayList<Integer> id_numbers;
    private String DirPath = "ServletImages/";
    private String Absolute_path = "/Users/gratchuvalsky/Desktop/apache-tomcat-8.5.82/webapps/CourseServlet/";

    public ItemsManager(){
        items  = new LinkedHashMap<>();
        itemsNum = new LinkedHashMap<>();
        prices = new LinkedHashMap<>();
        file_paths = new LinkedHashMap<>();
        id_numbers = new ArrayList<>();
    }

    public synchronized void edit(int key, int val){
        itemsNum.put(key,val);
        
    }

    public synchronized void Add(String title, int price, int amount){

        if(items.size() == 0){
            items.put(0, title);
            itemsNum.put(0, amount);
            prices.put(0, price);
            file_paths.put(0,DirPath + title);
            id_numbers.add(0);
        }
        else {
            items.put(id_numbers.get(id_numbers.size() - 1) + 1, title);
            itemsNum.put(id_numbers.get(id_numbers.size() - 1) + 1, amount);
            prices.put(id_numbers.get(id_numbers.size() - 1) + 1, price);
            file_paths.put(id_numbers.get(id_numbers.size() - 1) + 1, DirPath + title);
            id_numbers.add(id_numbers.get(id_numbers.size() - 1) + 1);
        }
    }

    public synchronized void delete(int key){
        items.remove(key);
        itemsNum.remove(key);
        prices.remove(key);
        File file = new File(Absolute_path + file_paths.get(key));
        file.delete();
        file_paths.remove(key);
        id_numbers.remove(Integer.valueOf(key));
    }

    public synchronized void readFile() throws Exception{
        String path = "/Users/gratchuvalsky/Desktop/Items.txt";
        File list = new File(path);
        if(list.length() != 0) {
            items  = new LinkedHashMap<>();
            itemsNum = new LinkedHashMap<>();
            prices = new LinkedHashMap<>();
            file_paths = new LinkedHashMap<>();
            id_numbers = new ArrayList<>();

            Scanner sc = new Scanner(list);

            int id = 0;
            int num = 0;
            int price = 0;

            while (sc.hasNextLine()) {
                String[] tmp = sc.nextLine().split(" ");
                //tmp[0].replaceAll(":", "");
                id = Integer.parseInt(tmp[0]);
                price = Integer.parseInt(tmp[2]);
                num = Integer.parseInt(tmp[3]);
                items.put(id, tmp[1]);
                itemsNum.put(id, num);
                prices.put(id, price);
            }
            sc.close();

            String path1 = "/Users/gratchuvalsky/Desktop/IdNumbers.txt";
            File list1 = new File(path1);
            Scanner sc1 = new Scanner(list1);

            while (sc1.hasNextInt()) {
                id_numbers.add(sc1.nextInt());
            }
            sc1.close();


            String path3 = "/Users/gratchuvalsky/Desktop/FilePaths.txt";
            File list3 = new File(path3);

                Scanner sc3 = new Scanner(list3);
                //id = 0;

                while (sc3.hasNextLine()) {
                    String[] tmp = sc3.nextLine().split(" ");
                    //tmp[0].replaceAll(":", "");
                    id = Integer.parseInt(tmp[0]);
                    file_paths.put(id, tmp[1]);
                }
                sc3.close();
        }
        else{
            items = new LinkedHashMap<>();
            itemsNum = new LinkedHashMap<>();
            prices = new LinkedHashMap<>();
            file_paths = new LinkedHashMap<>();
            id_numbers = new ArrayList<>();
        }
    }

        public synchronized void writeFile() throws Exception{
        String path = "/Users/gratchuvalsky/Desktop/Items.txt";
        FileWriter fstream = new FileWriter(path);
        PrintWriter out = new PrintWriter(fstream);

        if(items.size() == 0){
            out.write("");
        }
        else {
            for (int key : items.keySet()) {
                out.write(key + " " + items.get(key) + " " + prices.get(key) + " " + itemsNum.get(key) + "\n");
            }
        }
        fstream.close();

        String path1 = "/Users/gratchuvalsky/Desktop/IdNumbers.txt";
        FileWriter fstream1 = new FileWriter(path1);
        PrintWriter out1 = new PrintWriter(fstream1);
        if(items.size() == 0){
            out1.write("");
        }
        else {
            for (int i = 0; i < id_numbers.size(); i++) {
                out1.write(id_numbers.get(i) + "\n");
            }
        }

        fstream1.close();

            String path3 = "/Users/gratchuvalsky/Desktop/FilePaths.txt";
            FileWriter fstream3 = new FileWriter(path3);
            PrintWriter out3 = new PrintWriter(fstream3);

            if(items.size() == 0){
                out3.write("");
            }
            else {
                for (int key : items.keySet()) {
                    out3.write(key + " " + file_paths.get(key) + "\n");
                }
            }
            fstream3.close();
    }

    public LinkedHashMap<Integer, String> getItems(){
        return items;
    }

    public LinkedHashMap<Integer, Integer> getItemsNum(){
        return itemsNum;
    }

    public LinkedHashMap<Integer, Integer> getItemsPrices(){
        return prices;
    }
        
        public LinkedHashMap<Integer, String> getFilePaths(){
            return file_paths;
        }

    }
