package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.Controller;
import Entity.Task;
import Controller.TaskControl;

public class DeleteTaskWindow implements Oracle{
	private JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteTaskWindow window = new DeleteTaskWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public DeleteTaskWindow() {
	}
	
	public DeleteTaskWindow(Task result) {
		t=result;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize(Controller c) {
		
		String str="";
		int flag=0;
		if(t.getParent()!=null){
			str=t.getName()+" has parent:";
			str=str+t.getParent().getName()+" ";
			str=str+"\n";
			flag=1;
		}
		if(t.getChildren().size()!=0){
			str=t.getName()+" has children:";
			for(int i=0;i<t.getChildren().size();i++){
				str=str+t.getChildren().get(i).getName()+" ";
			}
			str=str+"\n";
			flag=1;
		}
		if(t.getPredecessor().size()!=0){
			str=t.getName()+" has predecessors:";
			for(int i=0;i<t.getPredecessor().size();i++){
				str=str+t.getPredecessor().get(i).getName()+" ";
			}
			str=str+"\n";
			flag=1;
		}
		if(t.getSuccessor().size()!=0){
			str=t.getName()+" has successors:";
			for(int i=0;i<t.getSuccessor().size();i++){
				str=str+t.getSuccessor().get(i).getName()+" ";
			}
			str=str+"\n";
			flag=1;
		}
		str=str+"\nDo you still want to delete this task?";
		String[] buttons={"Yes","No"};
		int choice=JOptionPane.showOptionDialog(null,str,"Confirm Deletion",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[1]);
		
		if(flag==0 && choice==0){
			((TaskControl) c).delTaskFromTaskPool(t);
		}
		else if(flag==1 && choice==0){
			
			if(t.getParent()!=null)
				(t.getParent()).removeChild(t);
			
			else if(t.getChildren().size()!=0){
				for(int i=0;i<t.getChildren().size();i++){
					for(int j=0;j<t.getChildren().get(i).getResource().size();j++){
						t.getChildren().get(i).getResource().get(j).removeTaskReference(t.getChildren().get(i));
					}
					((TaskControl) c).delTaskFromTaskPool(t.getChildren().get(i));					
				}
			}
			else if(t.getPredecessor().size()!=0){
				for(int i=0;i<t.getPredecessor().size();i++){
					t.getPredecessor().get(i).removeSuccessor(t);
				}
			}
			else if(t.getSuccessor().size()!=0){
				for(int i=0;i<t.getSuccessor().size();i++){
					t.getSuccessor().get(i).removePredecessor(t);
				}
			}
			for(int j=0;j<t.getResource().size();j++){
				t.getResource().get(j).removeTaskReference(t);
			}
			((TaskControl) c).delTaskFromTaskPool(t);
		}
	}
	
	private Task t;
	
	public Object ask(String cmd, Controller control) {
		initialize(control);
	return null;
}
}
