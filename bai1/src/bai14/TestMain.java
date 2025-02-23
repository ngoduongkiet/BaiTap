package bai14;

public class TestMain {
	 public static void main(String[] args) {
	        Employee e1 = new Employee(8, "Peter", "Tan", 2500);
	        System.out.println(e1);
	        e1.setSalary(999);
	        System.out.println("ID là: " + e1.getId());
	        System.out.println("Tên là: " + e1.getFirstName());
	        System.out.println("Họ là: " + e1.getLastName());
	        System.out.println("Lương là: " + e1.getSalary());
	        System.out.println("Tên đầy đủ: " + e1.getName());
	        System.out.println("Lương hàng năm: " + e1.getAnnualSalary());
	        System.out.println("Lương sau tăng 10%: " + e1.raiseSalary(10));
	        System.out.println(e1);
	    }
}
