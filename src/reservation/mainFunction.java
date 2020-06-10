package reservation;

public class mainFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    reservation rsv = new reservation();
    try {
		rsv.doMenu();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
