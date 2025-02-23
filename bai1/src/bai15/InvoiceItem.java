package bai15;

public class InvoiceItem {
	 private String id;
	    private String desc;
	    private int qty;
	    private double unitPrice;

	    public InvoiceItem(String id, String desc, int qty, double unitPrice) {
	        this.id = id;
	        this.desc = desc;
	        this.qty = qty;
	        this.unitPrice = unitPrice;
	    }

	    public String getId() {
	        return id;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public int getQty() {
	        return qty;
	    }

	    public void setQty(int qty) {
	        this.qty = qty;
	    }

	    public double getUnitPrice() {
	        return unitPrice;
	    }

	    public void setUnitPrice(double unitPrice) {
	        this.unitPrice = unitPrice;
	    }

	    public double getTotal() {
	        return unitPrice * qty;
	    }

	    @Override
	    public String toString() {
	        return "InvoiceItem[id=" + id + ",desc=" + desc + ",qty=" + qty + ",unitPrice=" + unitPrice + "]";
	    }

	    public static void main(String[] args) {
	        InvoiceItem inv1 = new InvoiceItem("A101", "Bút đỏ", 888, 0.08);
	        System.out.println(inv1);

	        inv1.setQty(999);
	        inv1.setUnitPrice(0.99);
	        System.out.println(inv1);
	        System.out.println("id là: " + inv1.getId());
	        System.out.println("mô tả là: " + inv1.getDesc());
	        System.out.println("số lượng là: " + inv1.getQty());
	        System.out.println("giá là: " + inv1.getUnitPrice());

	        System.out.println("Tổng số là: " + inv1.getTotal());
	    }
	}


