/**
 * 
 * @author jake
 * Donation manager class, implements DonationManageInterface
 */
public class DonationManager implements DonationManageInterface {

	// CONTAINER
	Container container = new Container(5);
	VolunteerLine volunteerLine = new VolunteerLine(5);
	RecipientLine recipientLine = new RecipientLine(5);
	
	/**
	 *  Manager method to call loadContainer
	 *  @param dPackage
	 *  @return true
	 */
	@Override
	public boolean managerLoadContainer(DonationPackage dPackage) throws ContainerException {
		
		try {
			container.loadContainer(dPackage);
		}
		catch (ContainerException e) {
			throw new ContainerException("The Container is Full");
		}
		
		return true;
		
		
	}
	
	
	
	@Override
	/**
	 *  Manager method to call addNewVolunteer
	 *  @param Volunteer v
	 *  @return true
	 */
	public boolean managerQueueVolunteer(Volunteer v) throws VolunteerException {
		
		try {
			volunteerLine.addNewVolunteer(v);
		}
		catch (VolunteerException e) {
			throw new VolunteerException("Volunteer Line is Full");
		}
		return true;
	}

	@Override
	/**
	 *  Manager method to call addNewRecipient
	 *  @param Recipient r
	 *  @return true
	 */
	public boolean managerQueueRecipient(Recipient r) throws RecipientException {
		
		try {
			recipientLine.addNewRecipient(r);
		}
		catch (RecipientException e) {
			throw new RecipientException("Recipient Line is Full");
		}
		return true;
	}
	
	/**
	 * Handles the donation of a package, which involves modifying the volunteer and recipient line
	 * @param 
	 * @return 0
	 */
	@Override
	public int donatePackage() throws VolunteerException, ContainerException, RecipientException {
		if (container.removePackageFromContainer() == null) {
			throw new ContainerException("Container is Empty");
		}
		if (volunteerLine.addNewVolunteer(volunteerLine.volunteerTurn()) == false) {
			throw new VolunteerException("Volunteer Queue is Empty");
		}
		if (recipientLine.recipientTurn() == null) {
			throw new RecipientException("Recipient Queue is empty");
		}
		
		return 0;
	}

	/**
	 * Manager method to call container toArrayPackage
	 * @param
	 * @return arr
	 */
	@Override
	public DonationPackage[] managerArrayPackage() {
		return container.toArrayPackage();
	}

	/**
	 * Manager method to call container toArrayVolunteer
	 * @param
	 * @return arr
	 */
	@Override
	public Volunteer[] managerArrayVolunteer() {
		return volunteerLine.toArrayVolunteer();
	}

	/**
	 * Manager method to call container toArrayRecipient
	 * @param
	 * @return arr
	 */
	@Override
	public Recipient[] managerArrayRecipient() {
		return recipientLine.toArrayRecipient();
	}

}
