package asm04.model;

public abstract class User {
    private String customerId;
    private String name;


    //-------------------begin getter and setter--------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) throws Exception {
        if (!isValidCCCD(customerId)){
            throw new Exception("Số CCCD không hợp lệ.");
        }
        this.customerId = customerId;
    }
    //-------------------end getter and setter--------------------

    //    Check condition CCCD
    public boolean isValidCCCD(String input) {
        // Nếu input không đủ 12 kí tự, và ko phải từ 0 đến 9 trả về false
        if (!input.matches("[0-9]{12}")) {
            return false;
        }
        // Kiểm tra kí tự đầu tiên phải kí tự "0"
        for (int i = 0; i < 1; i++) {
            if (input.charAt(i) != '0') {
                return false;
            }
        }
        // Kiểm tra tính hợp lệ của CCCD
        String checkId = input.substring(1, 3);
        String regex = "(00|03|05|07|09|13|16|18|21|23|28|29|32|39|41|43|47|50|53|55|57|59|61|63|65|69|71|73|76|78|81|85|88|90|97|98|99)";
        return !checkId.matches(regex);
    }

    //    Constructor User
    public User( String customerId,String name) {
        this.customerId = customerId;
        this.name = name;

    }

}
