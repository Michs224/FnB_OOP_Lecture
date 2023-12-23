package fnb_oop;

public class session {
	private static user currentUser;
	
	public static user getCurrentUser() {
		return currentUser;
	}
	
	public static void setCurrentUser(user currUser) {
		currentUser = currUser;
	}
}
