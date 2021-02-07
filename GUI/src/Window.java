
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData; 

public class Window implements MouseListener{

	private String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL",
			"IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
			"NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV",
			"WY" };

	/**
	 * Launch the application.
	 * 
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
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label lblChooseAState = new Label(shell, SWT.NONE);
		lblChooseAState.setLayoutData(new RowData(155, 33));
		lblChooseAState.setText("Choose A State");
		
		
		Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		for(int i = 0; i < states.length; i++)
		{
			combo.add(states[i]);
		}
		
		/*
		 * predefined selection
		 */
//		combo.select(0);
		
		combo.addMouseListener(this);
		
		
		shell.setSize(252, 56);

		shell.setText("COVID Application");

		shell.pack();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
		
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
	}
	
	
	
	
}

