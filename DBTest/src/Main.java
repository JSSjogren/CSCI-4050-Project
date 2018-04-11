import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.RegisteredCustomer;
import dawgdrivein.entity.SystemAdministrator;

public class Main {

	public static void main(String[] args)
	{
		RegisteredCustomer customer = new RegisteredCustomer("Jarod", "Sjogren", "jss29576@uga.edu", "sjogren7", 3, 1, true);
		customer.deleteCustomer();
	}
}
