package Entity;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ListTask implements ListModel<Task> {

	TaskPool values;
	public ListTask(TaskPool ts){
			values=ts;
		}
	@Override
	public Task getElementAt(int index) {
		return values.get(index);
	}
	@Override
	public int getSize() {
		return values.size();
	}
	public void addListDataListener(ListDataListener l) {}
	public void removeListDataListener(ListDataListener l) {}
}


