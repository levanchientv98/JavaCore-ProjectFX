package asm01;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.parseInt;

public class Asm01 {
    //    Create padding for content
    public static void printCont(String content, int width, int height) {
        for (int i = 0; i < height - 2; i++) {
            System.out.print("|");
            if (i == (height - 2) / 2) {
                int padding = width / 2;
                for (int j = 0; j < 2; j++) {
                    System.out.print(" ");
                }
                System.out.print(content);
                for (int j = padding + content.length(); j < 56; j++) {
                    System.out.print(" ");
                }
            } else {
                for (int j = 0; j < width - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
    }

    //    Create border for the title
    public static void printBox(int width, int height) {
        // Print top border
        System.out.print("+");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        // Print middle rows
        printCont("NGAN HANG SO | FX21311@v1.0.0", width, height);

        // Print middle border
        System.out.print("+");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        // Print middle rows
        printCont("1: Nhập CCCD", width, height);
        printCont("0: Thoát", width, height);

        // Print bottom border
        System.out.print("+");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    //    Table Function
    public static void tableFunction(String code) {
        String numberFunciton2;
        Scanner scanner = new Scanner(System.in);
        while (isValidInput(code) == true) {
            System.out.println("\t| 1. Kiểm tra nơi sinh");
            System.out.println("\t| 2. Kiểm tra tuổi, giới tính");
            System.out.println("\t| 3. Kiểm tra số ngẫu nhiên");
            System.out.println("\t| 0. Thoát");
            System.out.print("Chức năng: ");
            numberFunciton2 = scanner.nextLine();
            switch (numberFunciton2) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    checkAddress(code);
                    break;
                case "2":
                    System.out.println("Giới tính: " + checkGender(code) + " | " + checkYearOfBirth(code));
                    break;
                case "3":
                    System.out.println("Số ngẫu nhiên: " + checkRandomNumber(code));
                    break;
                default:
                    System.out.println("Chọn lại chức năng trong khoảng 0 -> 3 ");
                    tableFunction(code);
                    break;

            }
        }
    }

    //    Funcition directional
    public static void directional() {
        String code;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vui lòng nhập số CCCD:  ");
        code = scanner.nextLine();
        tableFunction(code);
        while (isValidInput(code) == false) {
            System.out.print("Số CCCD không hợp lệ. Vui lònng nhập lại hoặc ‘No’ để thoát: ");
            code = scanner.next();
            tableFunction(code);
            switch (code) {
                case "No":
                    System.exit(0);
                    break;
            }
        }
    }

    //  Generate random string of numbers and characters
    public static String randomString(int lenght) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lenght; i++) {
            int index = random.nextInt(str.length());
            sb.append(str.charAt(index));
        }
        return sb.toString();
    }

    //  Function create security code
    public static void creatSecurity() {
        String numberFunciton;
        String numberCode;
        String strCode;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t| 1. Mã bảo mật EASY: ");
        System.out.println("\t| 2. Mã bảo mật HARD: ");
        System.out.print("Chức năng: ");
        numberFunciton = scanner.nextLine();
        switch (numberFunciton) {
            case "1":
                System.out.print("Nhập mã xác thực: ");
                int randomNum = ThreadLocalRandom.current().nextInt(101, 1000);
                System.out.println(randomNum);
                numberCode = scanner.nextLine();
                while (!numberCode.equals(String.valueOf(randomNum))) {
                    System.out.println("Mã xác thực không đúng vui lòng nhập lại. ");
                    numberCode = scanner.nextLine();
                }
                ;
                break;
            case "2":
                System.out.print("Nhập mã xác thực: ");
                String randomStr = randomString(6);
                System.out.println(randomStr);
                strCode = scanner.next();
                while (!strCode.equals(randomStr)) {
                    System.out.println("Mã xác thực không đúng vui lòng nhập lại. ");
                    strCode = scanner.next();
                }
                ;
                break;
            default:
                System.out.println("Chọn lại chức năng trong khoảng 1 -> 2 ");
                creatSecurity();
                break;
        }
    }

    // create function input function
    public static void inputFunciton() {
        String numberFunciton;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chức năng: ");
        numberFunciton = scanner.nextLine();
        switch (numberFunciton) {
            case "0":
                break;
            case "1":
                creatSecurity();
                directional();
                break;
            default:
                System.out.println("Chọn lại chức năng trong khoảng 0 -> 1 ");
                inputFunciton();
                break;
        }
    }

    //    Function check CCCD - Address
    public static void checkAddress(String code) {
        Map<String, String> c = new HashMap<>();
        c.put("001", "Hà Nội");
        c.put("002", "Hà Giang");
        c.put("004", "Cao Bằng");
        c.put("006", "Bắc Kạn");
        c.put("008", "Tuyên Quang");
        c.put("010", "Lào Cai");
        c.put("011", "Điện Biên");
        c.put("012", "Lai Châu");
        c.put("014", "Sơn La");
        c.put("015", "Yên Bái");
        c.put("017", "Hòa Bình");
        c.put("019", "Thái Nguyên");
        c.put("020", "Lạng Sơn");
        c.put("022", "Quảng Ninh");
        c.put("024", "Bắc Giang");
        c.put("025", "Phú Thọ");
        c.put("026", "Vĩnh Phúc");
        c.put("027", "Bắc Ninh");
        c.put("030", "Hải Dương");
        c.put("031", "Hải Phòng");
        c.put("033", "Hưng Yên");
        c.put("034", "Thái Bình");
        c.put("035", "Hà Nam");
        c.put("036", "Nam Định");
        c.put("037", "Ninh Bình");
        c.put("038", "Thanh Hóa");
        c.put("040", "Nghệ An");
        c.put("042", "Hà Tĩnh");
        c.put("044", "Quảng Bình");
        c.put("045", "Quảng Trị");
        c.put("046", "Thừa Thiên Huế");
        c.put("048", "Đà Nẵng");
        c.put("049", "Quảng Nam");
        c.put("051", "Quảng Ngãi");
        c.put("052", "Bình Định");
        c.put("054", "Phú Yên");
        c.put("056", "Khánh Hòa");
        c.put("058", "Ninh Thuận");
        c.put("060", "Bình Thuận");
        c.put("062", "Kon Tum");
        c.put("064", "Gia Lai");
        c.put("066", "Đắk Lắk");
        c.put("067", "Đắk Nông");
        c.put("068", "Lâm Đồng");
        c.put("070", "Bình Phước");
        c.put("072", "Tây Ninh");
        c.put("074", "Bình Dương");
        c.put("075", "Đồng Nai");
        c.put("077", "Bà Rịa - Vũng Tàu");
        c.put("079", "Hồ Chí Minh");
        c.put("080", "Long An");
        c.put("082", "Tiền Giang");
        c.put("083", "Bến Tre");
        c.put("084", "Trà Vinh");
        c.put("086", "Vĩnh Long");
        c.put("087", "Đồng Tháp");
        c.put("089", "An Giang");
        c.put("091", "Kiên Giang");
        c.put("092", "Cần Thơ");
        c.put("093", "Hậu Giang");
        c.put("094", "Sóc Trăng");
        c.put("095", "Bạc Liêu");
        c.put("096", "Cà Mau");

        String addressId = code.substring(0, 3);
        if (c.containsKey(addressId)) {
            System.out.println("Nơi sinh: " + c.get(addressId));
        }
    }

    //    Function check gender
    public static String checkGender(String code) {
        String genderId = code.substring(3, 4);
        String gender = parseInt(genderId) % 2 == 0 ? "Nam" : "Nữ";
        return gender;
    }

    //   Function check year of birth
    public static String checkYearOfBirth(String code) {
        String yearOB = "";
        String yearOBId = code.substring(4, 6);
        String genderId = code.substring(3, 4);
        if (genderId.equals("0") || genderId.equals("1")) {
            yearOB = "19" + yearOBId;
        } else if (genderId.equals("2") || genderId.equals("3")) {
            yearOB = "20" + yearOBId;
        } else if (genderId.equals("4") || genderId.equals("5")) {
            yearOB = "21" + yearOBId;
        } else if (genderId.equals("6") || genderId.equals("7")) {
            yearOB = "22" + yearOBId;
        } else if (genderId.equals("8") || genderId.equals("9")) {
            yearOB = "23" + yearOBId;
        }
        return yearOB;
    }

    //   Function random number check
    public static String checkRandomNumber(String code) {
        String randomNumberId = code.substring(6, 12);
        return randomNumberId;
    }

    //    Check condition CCCD
    public static boolean isValidInput(String input) {
        // Nếu input không đủ 12 kí tự, và ko phải từ 0 đến 9 trả về false
        if (!input.matches("[0-9]{12}")) {
            return false;
        }
        for (int i = 0; i < 1; i++) {
            if (input.charAt(i) != '0') {
                return false;
            }
        }
        // Kiểm tra tính hợp lệ của CCCD
        String checkId = input.substring(1, 3);
        String regex = "(00|03|05|07|09|13|16|18|21|23|28|29|32|39|41|43|47|50|53|55|57|59|61|63|65|69|71|73|76|78|81|85|88|90|97|98|99)";
        if (checkId.matches(regex)) {
            return false;
        }
//       Kiểm tra xem các kí tự còn lại có đều là số từ 0 đến 9 hay không
//        for (int i = 3; i < 12; i++) {
//        if (!Character.isDigit(input.charAt(i))) {
//            return false;
//        }
//    }
        return true;
    }

    //  Main
    public static void main(String[] args) {
        // Create Title software
        printBox(40, 3);
        inputFunciton();

    }

}
