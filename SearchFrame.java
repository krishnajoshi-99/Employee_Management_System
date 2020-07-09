import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class SearchFrame extends JFrame{
	Container c;
	JLabel eidLbl;
	JTextField eidTxt;
	JButton backBtn,searchBtn;
	SearchFrame(){
		setContentPane(new JLabel(new ImageIcon("SEARCH1.jpg")));
		c=getContentPane();
		c.setLayout(null);
		
		Font f=new Font("Arial",Font.BOLD,20);
		Font f1=new Font("Arial",Font.PLAIN,20);
		
		eidLbl=new JLabel("Employee ID:");
		eidLbl.setBounds(125,30,200,30);
		eidLbl.setFont(f);
		
		eidTxt=new JTextField(10);
		eidTxt.setBounds(40,65,307,30);
		eidTxt.setFont(f1);
		
		ImageIcon SearchImage = new ImageIcon(new ImageIcon("Search.png").getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT));
		ImageIcon BackImage = new ImageIcon(new ImageIcon("back.png").getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT));
		
		searchBtn=new JButton("SEARCH",SearchImage);
		searchBtn.setSize(170,40);
		searchBtn.setLocation(105,110);
		searchBtn.setFont(f);
		
		backBtn=new JButton("BACK",BackImage);
		backBtn.setSize(170,40);
		backBtn.setLocation(105,160);
		backBtn.setFont(f);
		
		c.add(eidLbl);
		c.add(eidTxt);
		c.add(searchBtn);
		c.add(backBtn);
		
		searchBtn.addActionListener(ae ->{
			Configuration cfg= new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();
			Session session = sfact.openSession();
			Transaction t =null;
			try{
				System.out.println("begin");
				t=session.beginTransaction();
				if(eidTxt.getText().equals("")){
			 		JOptionPane.showMessageDialog(null,"Please Enter Employee id ","Insane warning",JOptionPane.WARNING_MESSAGE);	
		        		eidTxt.requestFocus();
					return;
			  	}	
			  	int value = Integer.parseInt(eidTxt.getText()); 
              	if(value<0){
              		JOptionPane.showMessageDialog(null,"Id should be positive","Insane error",JOptionPane.ERROR_MESSAGE);
              		eidTxt.setText("");
					eidTxt.requestFocus();
					return;
				}			  
          		Employee e =(Employee)session.get(Employee.class,value);
          		if(e != null){
          			t.commit();
          			JOptionPane.showMessageDialog(null,"ID: "+e.getEid()+"     Name: "+e.getEname()+"     Salary: "+e.getEsal(),"Employee Details",JOptionPane.PLAIN_MESSAGE);
          			System.out.println("\n \n \t \t \t Record Exists  \t \t \t  \n \n");
				System.out.println("end");
          		}
          		else{
          			JOptionPane.showMessageDialog(null,"Record does not Exists","Insane warning",JOptionPane.WARNING_MESSAGE);
          			eidTxt.setText("");
          			eidTxt.requestFocus();
          		}
			}
			catch(NumberFormatException e){
				t.rollback();
				JOptionPane.showMessageDialog(null,"EmpId should  only have Digits","Insane error",JOptionPane.ERROR_MESSAGE);
				eidTxt.setText("");
				eidTxt.requestFocus();
			}
			catch(Exception e){
				t.rollback();
				System.out.println(e);
			}
			finally{
				session.close();
			}
		});
		backBtn.addActionListener(ae ->{MainFrame a=new MainFrame();  dispose();});
		
		setSize(400,280);
		setLocationRelativeTo(null);
		setTitle("Search Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setVisible(true);
		
	}
}