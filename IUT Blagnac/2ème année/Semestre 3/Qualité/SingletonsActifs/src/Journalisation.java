import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.HashMap;

public class Journalisation {
	private String log;
	private static HashMap<TypesOperation, Journalisation> instance = new HashMap<TypesOperation, Journalisation>();
	
	private Journalisation(){}

	public static Journalisation getInstance(TypesOperation pfLog) {
		if(!instance.containsKey(pfLog)) {
			instance.put(pfLog, new Journalisation());
			instance.get(pfLog).log += " Journal " + pfLog + "\n";
		}
		return instance.get(pfLog);
	}
	
	public void ajouterLog(String pfLog) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date date = new Date(ts.getTime());
		LocalTime lt = LocalTime.now();
		log += "[" + date + " : " + lt.getHour() + "h " + lt.getMinute() + "] " + pfLog + "\n";
	}
	
	public String getLog() {
		return log;
	}

}
