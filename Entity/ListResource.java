package Entity;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ListResource implements ListModel<Resource> {

	ResourcePool values;
	public ListResource(ResourcePool ts){
			values=ts;
		}
	@Override
	public Resource getElementAt(int index) {
		return values.get(index);
	}
	@Override
	public int getSize() {
		return values.size();
	}
	public void addListDataListener(ListDataListener l) {}
	public void removeListDataListener(ListDataListener l) {}

}
