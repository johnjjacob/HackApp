
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Window {
	
	public String[] states = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC",  
			"DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",  
			"MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE",  
			"NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC",  
			"SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"};

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = new Window();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		
		Display display = Display.getDefault();
		
		int displayHeight = display.getPrimaryMonitor().getBounds().height;
		int displayWidth = display.getPrimaryMonitor().getBounds().width;
		
		Shell shell = new Shell();
		shell.setSize(displayWidth/2, displayHeight/2);
		shell.setText("COVID Application");
		
		
		
		
		Label lblChooseDate = new Label(shell, SWT.NONE);
		lblChooseDate.setBounds(10, 0, 92, 14);
		lblChooseDate.setText("Choose State");
		
		Tree tree = new Tree(shell, SWT.BORDER);
		tree.setBounds(10, 25, 141, 434);
		
		for(int i = 0; i < states.length; i++)
		{
			TreeItem trtmState = new TreeItem(tree, SWT.NONE);
			trtmState.setText(states[i]);
		}

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		
	}
}
