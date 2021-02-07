
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Window {

	public String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL",
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

		shell.setSize(411, 446);

		shell.setText("COVID Application");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 554, 382);

		ToolBar toolBar = new ToolBar(composite, SWT.BORDER | SWT.VERTICAL);
		toolBar.setLocation(100, 0);
		toolBar.setSize(35, 22);

		ToolItem item = new ToolItem(toolBar, SWT.DROP_DOWN);
		item.setWidth(10);
		item.setSelection(true);
		item.setText(states[0]);

		DropdownSelectionListener listenerOne = new DropdownSelectionListener(item);

		item.addSelectionListener(listenerOne);

		toolBar.pack();

		Label lblChooseAState = new Label(composite, SWT.NONE);
		lblChooseAState.setBounds(0, 0, 98, 23);
		lblChooseAState.setText("Choose a state:");
		
		

		for (int i = 1; i < 50; i++) {
			listenerOne.add(states[i]);
		}

		shell.pack();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}
}

class DropdownSelectionListener extends SelectionAdapter {
	private ToolItem dropdown;

	private Menu menu;

	public DropdownSelectionListener(ToolItem dropdown) {
		this.dropdown = dropdown;
		menu = new Menu(dropdown.getParent().getShell());
	}

	public void add(String item) {
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText(item);
		menuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				MenuItem selected = (MenuItem) event.widget;
				dropdown.setText(selected.getText());
			}
		});
	}

	public void widgetSelected(SelectionEvent event) {
		if (event.detail == SWT.ARROW) {
			ToolItem item = (ToolItem) event.widget;
			Rectangle rect = item.getBounds();
			Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
			menu.setLocation(pt.x, pt.y + rect.height);
			menu.setVisible(true);

			System.out.println("dropdown: " + dropdown.getText() + " Pressed");

			/**
			 * Place the code to connect front end to backend to here
			 */

		} else {
			System.out.println(dropdown.getText() + " Pressed");

			/**
			 * Place the code to refresh the current state here
			 */
		}
	}
}
