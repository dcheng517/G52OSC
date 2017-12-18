import java.util.Comparator;

public class RemBTComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		Integer x = p1.getRemBT();
		Integer y = p2.getRemBT();
		return x.compareTo(y);
	}
}
