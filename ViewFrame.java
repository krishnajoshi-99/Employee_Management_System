import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.sql.*;

class ViewFrame extends JFrame {
	Container c;
	JTextArea ta;
	JScrollPane js;
	JButton backBtn,sortEidBtn,sortEnameBtn,sortEsalBtn;
	
	ViewFrame() {
		setContentPane(new JLabel(new ImageIcon("VIEW.jpg")));
		c = getContentPane();
		c.setLayout(null);

		Font f=new Font("Arial",Font.BOLD,25);
		Font f1=new Font("Arial",Font.PLAIN,20);
		
		ImageIcon BackImage = new ImageIcon(new ImageIcon("back.png").getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT));
		
		ta=new JTextArea();
		ta.setBounds(80, 40, 520,200);
		ta.setFont(f1);
		
		js=new JScrollPane();
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);		
		
		js.setBounds(80,40,520,200);
		js.getViewport().setBackground(Color.WHITE);
		js.getViewport().add(ta);

		sortEidBtn=new JButton("Sort by ID");
		sortEidBtn.setSize(200,40);
		sortEidBtn.setLocation(20,280);
		sortEidBtn.setFont(f);

		sortEnameBtn=new JButton("Sort by Name");
		sortEnameBtn.setSize(200,40);
		sortEnameBtn.setLocation(240,280);
		sortEnameBtn.setFont(f);

		sortEsalBtn=new JButton("Sort by Salary");
		sortEsalBtn.setSize(200,40);
		sortEsalBtn.setLocation(460,280);
		sortEsalBtn.setFont(f);	
	
		backBtn=new JButton("BACK",BackImage);
		backBtn.setSize(150,40);
		backBtn.setLocation(265,345);
		backBtn.setFont(f);

		c.add(js);
		c.add(backBtn);
		c.add(sortEidBtn);
		c.add(sortEnameBtn);
		c.add(sortEsalBtn);
		Session session;
	
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact=cfg.buildSessionFactory();
		session=sfact.openSession();
		System.out.println("\n \n \t \t \t DONE \t \t \t \n \n ");
		java.util.List<Employee> emp=new java.util.ArrayList<>();
		emp=session.createQuery("from Employee").list();
		for(Employee e:emp)
			ta.append(" ID: " +e.getEid()+ "        "+"NAME: " +e.getEname()+"        " +"SALARY: " +e.getEsal() +"\n");

		ta.setRequestFocusEnabled(true);
		ta.requestFocus();
		ta.grabFocus();

		js.setRequestFocusEnabled(true);
		js.requestFocus();
		js.grabFocus();
	
		sortEidBtn.addActionListener(ae -> { 
		ta.setText("");
		ta.transferFocus();
		js.requestFocus();
		java.util.List<Employee> emp1=new java.util.ArrayList<>();
		emp1=session.createQuery("from Employee order by eid").list();
		for(Employee e:emp1)
			ta.append(" ID: " +e.getEid()+ "        "+"NAME: " +e.getEname()+"        " +"SALARY: " +e.getEsal() +"\n");
		});	
		sortEnameBtn.addActionListener(ae -> {
			ta.setText(""); 
			java.util.List<Employee> emp2=new java.util.ArrayList<>();
			emp2=session.createQuery("from Employee order by ename").list();
			for(Employee e:emp2)
				ta.append(" ID: " +e.getEid()+ "        "+"NAME: " +e.getEname()+"        " +"SALARY: " +e.getEsal() +"\n");
		});
		sortEsalBtn.addActionListener(ae -> { 
			ta.setText("");
			java.util.List<Employee> emp3=new java.util.ArrayList<>();
			emp3=session.createQuery("from Employee order by esal").list();
			for(Employee e:emp3)
				ta.append(" ID: " +e.getEid()+ "        "+"NAME: " +e.getEname()+"        " +"SALARY: " +e.getEsal() +"\n");
		});

		backBtn.addActionListener(ae -> {MainFrame a=new MainFrame();	dispose();});
		
		setTitle("View Employee ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(693, 450);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
