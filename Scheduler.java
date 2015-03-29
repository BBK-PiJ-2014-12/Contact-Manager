import java.util.TimerTask;


public class Scheduler extends TimerTask{
	ContactManagerImpl cm;
	
	public Scheduler(ContactManagerImpl cm) {
		this.cm = cm;
	}
	
	@Override
	public void run() {
		cm.revaluateMeetings();
	}

}
