
import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

 
/**
 * @author khandan Monshi, revised by Professor Kartchner
 *
 */
public class DonationManagerSTUDENTTest {
	DonationManager manager;

	@Before
	public void setUp() throws Exception {
	 
		manager = new DonationManager();
		
	}
 
	@After
	public void tearDown() throws Exception {
		 
		manager = null;
	}
 
	/** 
	 * Student test that a new DonationPackage is created and 
	 * the manager correctly calls load the container 
	 */
	@Test
	public void testManagerLoadcontainer()   {
		try {
			manager.managerLoadContainer(new DonationPackage("Pencils", 20));
			manager.managerLoadContainer(new DonationPackage("Apples", 50));
		}
		catch (ContainerException e) {
			System.out.println("Should not throw exception");
			fail("Should not throw exception"); 
		}
	}
	 
	/**
	 * Student test that a new Volunteer is created and 
	 * the manager correctly queues the volunteer
	 */
	@Test
	public void testManagerQueueVolunteer() {
		try {
			manager.managerQueueVolunteer(new Volunteer("Jacob"));
			manager.managerQueueVolunteer(new Volunteer("Dave"));
		}
		catch (VolunteerException e) {
			System.out.println("Should not throw volunteerException");
			fail("Student has not implemented testManagerQueueVolunteer"); 	 
		}	
	}

	/**
	 * Student test that a new Recipient is created and 
	 * the manager correctly queues the recipient
	 */
	@Test
	public void testManagerQueueRecipient() {
		try {
			manager.managerQueueRecipient(new Recipient("Nick"));
			manager.managerQueueRecipient(new Recipient("Clive"));
		}
		catch (RecipientException e) {
			System.out.println("Should not throw recipientException");
			fail("Should not throw recipientException");
		}
	}

	/**
	 * Student test that the manager correctly calls donatePackage,
	 * testing the situations noted in the assignment document
	 */
	@Test
	public void testDonatePackage() {
	    Volunteer v1;
	    Recipient r1;
	    DonationPackage d1,d2;
	    
	    v1 = new Volunteer("Jacob"); 
		r1 =  new Recipient("Recipient_Person");
		
		d1 =  new DonationPackage("Water",10);
		d2 =  new DonationPackage("Food",20);
		
		try {
			manager.managerLoadContainer(d1);
			manager.managerLoadContainer(d2);	
			manager.managerQueueVolunteer(v1);    	
			manager.managerQueueRecipient(r1); 
			assertEquals(manager.donatePackage(),0);
			assertEquals(manager.donatePackage(),2);
			
		} catch (ContainerException | VolunteerException | RecipientException e) {
			 
			System.out.println("Threw " + e);
		}
	} 

}
