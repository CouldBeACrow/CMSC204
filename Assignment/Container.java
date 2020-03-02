/**
 * 
 * @author jake
 *	class for container, implements containerInterface
 */
public class Container implements ContainerInterface {

	public static final int DEFAULT_SIZE = 0;
	
	MyStack<DonationPackage> stack;
	
	/**
	 * Constructor, creates a new stack
	 * @param size
	 * @return
	 */
	public Container(int size) {
		stack = new MyStack<DonationPackage>(size);
	}
	
	/**
	 * Default constructor, creates new stack with default size 
	 * @param
	 * @return
	 */
	public Container() {
		stack = new MyStack<DonationPackage>(DEFAULT_SIZE);
	}
	
	/**
	 * loads a donationPackage into the container
	 * @param dPackage
	 * @return true 
	 */
	@Override
	public boolean loadContainer(DonationPackage dPackage) throws ContainerException {
		if (stack.push(dPackage) == false) {
				throw new ContainerException("The Container is Full");
		}
		return true;
	}

	/**
	 * Removes a donationPackage from the container
	 * @param
	 * @return a (donationPackage)
	 */
	@Override
	public DonationPackage removePackageFromContainer() throws ContainerException {
		DonationPackage a = stack.pop();
		if (a == null) {
			throw new ContainerException("The Container is Empty");
		}
		return a;
	}

	/**
	 * Converts the container of donationpackages to an array of donationpackages
	 * @param
	 * @return arr
	 */
	@Override
	public DonationPackage[] toArrayPackage() {
		DonationPackage[] arr  = new DonationPackage[stack.size()];
		Object[] temp = stack.toArray();
		for (int i=0; i<stack.size(); i++) {
			arr[i] = (DonationPackage) temp[i];
		}
		return arr;
	}

}
