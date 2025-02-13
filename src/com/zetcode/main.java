package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class main {

	public static String state;
	public static HttpResponse<String> response;
	

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("COVID-19 Area Data Finder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1900, 350);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter State");
		JTextField input = new JTextField(15);
		JButton search = new JButton("Search");
		JButton abbrev = new JButton("Abbreviations");

		panel.add(label);
		panel.add(input);
		panel.add(search);
		panel.add(abbrev);

		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.setVisible(true);

		String[] statelist = { "ak", "al", "ar", "as", "az", "ca", "co", "ct", "dc", "de", "fl", "ga", "gu", "hi", "ia",
				"id", "il", "in", "ks", "ky", "la", "ma", "md", "me", "mi", "mn", "mo", "mp", "ms", "mt", "nc", "nd",
				"ne", "nh", "nj", "nm", "nv", "ny", "oh", "ok", "or", "pa", "pr", "ri", "sc", "sd", "tn", "tx",
				"ut", "va", "vi", "vt", "wa", "wi", "wv", "wy" };

		abbrev.addActionListener((e) -> {
			JOptionPane.showMessageDialog(frame,
					"ALASKA = ak, ALABAMA = al, ARKANSAS = ar, AMERICAN SAMOA = as, ARIZONA = az, CALIFORNIA = ca, COLORADO = co, CONNECTICUT = ct, DISTRICT OF COLUMBIA = dc, DELAWARE = de, FLORIDA = fl,\n GEORGIA = ga, GUAM = gu, HAWAII = hi, IOWA = ia, IDAHO = id, ILLINOIS = il, INDIANA = in, KANSAS = ks, KENTUCKY = ky, LOUISIANA = la, MASSACHUSETTS = ma, MARYLAND = md, MAINE = me,\n MICHIGAN = mi, MINNESOTA = mn, MISSOURI = mo, NORTHERN MARIANA IS = mp, MISSISSIPPI = ms, MONTANA = mt, NORTH CAROLINA = nc, NORTH DAKOTA = nd, NEBRASKA = ne, NEW HAMPSHIRE = nh,\n NEW JERSEY = nj, NEW MEXICO = nm, NEVADA = nv, NEW YORK = ny, OHIO = oh, OKLAHOMA = ok, OREGON = or, PENNSYLVANIA = pa, PUERTO RICO = pr, RHODE ISLAND = ri, SOUTH CAROLINA = sc,\n SOUTH DAKOTA = sd, TENNESSEE = tn, TEXAS = tx, UTAH = ut, VIRGINIA = va, VIRGIN ISLANDS = vi, VERMONT = vt, WASHINGTON = wa, WISCONSIN = wi, WEST VIRGINIA = wv, WYOMING = wy");
				

		});

		search.addActionListener((e) -> {
			state = input.getText();

			boolean isState = false;
			for (int s = 0; s < statelist.length; s++) {
				if (state.equalsIgnoreCase(statelist[s])) {
					isState = true;
				}

			}
			

			if (isState) {
				String url = "https://api.covidtracking.com/v1/states/" + state + "/current.csv";

				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

				try {
					response = client.send(request, HttpResponse.BodyHandlers.ofString());
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// System.out.println(response.body());

				String rawData = response.body();

				Data dataset = new Data();

				read(rawData, dataset);

				for (int i = 0; i < dataset.header.size(); i++) {

					if (dataset.dataVal.get(i) != "" && i != 39 && i != 48 && i != 54 && i != 40) {
						if (!dataset.dataVal.get(i).equals("0")) {
							System.out.println(dataset.header.get(i) + " " + dataset.dataVal.get(i));
						}

					}

				}

				List<String> data = new ArrayList<>();
				List<String> column = new ArrayList<>();
				int j = 0;

				for (int i = 0; i < dataset.header.size(); i++) {
					if (dataset.dataVal.get(i) != "" && i != 39 && i != 48 && i != 54 && i != 40) {
						if (!dataset.dataVal.get(i).equals("0")) {

							column.add(dataset.header.get(i));
							j++;
						}
					}
				}

				String[] fcolumn = new String[j];

				for (int m = 0; m < j; ++m) {

					fcolumn[m] = column.get(m);

				}

				j = 0;

				for (int i = 0; i < dataset.header.size(); i++) {
					if (dataset.dataVal.get(i) != "" && i != 39 && i != 48 && i != 54 && i != 40) {
						if (!dataset.dataVal.get(i).equals("0")) {

							data.add(dataset.dataVal.get(i));
							j++;
						}
					}
				}

				String[][] fdata = new String[1][j];

				for (int p = 0; p < j; ++p) {
					fdata[0][p] = data.get(p);

				}

				JTable jt = new JTable(fdata, fcolumn);
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jt.setPreferredScrollableViewportSize(new Dimension(500, 70));
				jt.setFillsViewportHeight(true);
				// jt.setRowHeight(0, jt.getRowHeight() + 300);
				jt.setRowHeight(jt.getRowHeight() + 300);
				jt.setRowMargin(jt.getRowMargin() + 100);
				// jt.setFont(new Font("Serif", Font.CENTER_BASELINE, 5));
				JScrollPane sp = new JScrollPane(jt);
				frame.add(sp);
			}

			else {
				
				JOptionPane.showMessageDialog(frame, "Please use a valid state abbreviation");
			}
			frame.setVisible(true);

		});

		frame.setVisible(true);
	}

	public static void read(String data, Data d)
	{
		
		
		String[] values = data.split("\n");
		String[] headers = values[0].split(",");
		String[] vals = values[1].split(",");


		
		for(int i = 0; i < headers.length; i++)
		{
			d.header.put(i, headers[i]);
		}
		
		for(int i = 0; i < vals.length; i++)
		{
			d.dataVal.put(i, vals[i]);
		}
		
		
		
	}
}
