package asm04.common;

import asm04.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryFileService {

    // Function read file by file path
    public static List<String> readFile(String filePath) {
        // Create File object
        File file = new File(filePath);

        // Create BufferedReader object
        BufferedReader reader = null;

        // Create a list to store the read data
        List<String> data = new ArrayList<>();

        try {
            // Create BufferedReader object
            reader = new BufferedReader(new FileReader(file));

            // Read each line of data from the file
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    data.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File path wrong: " + e.getMessage());
        } finally {
            // Close the BufferedReader object
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }

    public static void writeFile(String filePath, List<String> data) throws FileNotFoundException, IOException {
        // Tạo đối tượng File
        File file = new File(filePath);
        // Tạo đối tượng FileWriter
        FileWriter writer = new FileWriter(file);
        // Duyệt qua danh sách dữ liệu
        for (String line : data) {
            // Ghi dòng dữ liệu vào tệp
            writer.write(line + "\n");
        }
        // Đóng đối tượng FileWriter
        writer.close();
    }

    // Function write file by file path
    public static void writeFileCustomer(String filePath, List<Customer> data)  {
        // Tạo đối tượng File
        File file = new File(filePath);
        // Tạo đối tượng FileWriter

        // Tạo đối tượng FileWriter
        try (FileWriter writer = new FileWriter(file)) {
            // Duyệt qua danh sách dữ liệu
            for (Customer line : data) {
                // Ghi dòng dữ liệu vào tệp
                writer.write(line.getCustomerId() + "," + line.getName() + "\n");
            }
            writer.write("\n");
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            // Xử lý ngoại lệ
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    //  Function read file list customer
    public static List<Customer> readListCustomer(String filePath){
        List<Customer> listCustomer = new ArrayList<>() ;
        for(String customerInfo : readFile(filePath)){
         String[] newCustomer = customerInfo.split(",");
          Customer customer = new Customer(newCustomer[0],newCustomer[1]);
          listCustomer.add(customer);
        }

        return listCustomer;
    }


}
