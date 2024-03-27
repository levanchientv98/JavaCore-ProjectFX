package asm04.dao;


import asm04.common.BinaryFileService;
import asm04.model.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final String FILE_PATH = "C:/Data-D/Java-Project/PRO192x_ASM_chienlvFX21311/src/asm04/store/list-customer.txt";

    public static void save(List<Customer> customers) throws IOException{
        BinaryFileService.writeFileCustomer(FILE_PATH,customers);
    }

    public static List<Customer> listCustomer(){
        List<Customer> listDataCustomer = new ArrayList<>() ;
        listDataCustomer = BinaryFileService.readListCustomer(FILE_PATH);
        return listDataCustomer;
    }
}
