import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame{
	Container c;
	JLabel eidLbl;
	JTextField eidTxt;
	JButton saveBtn,backBtn;
	DeleteFrame(){
		
		setContentPane(new JLabel(new ImageIcon("DEL.jpg")));		
		setBounds(550,180,400,350);
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
		
		ImageIcon DeleteImage = new ImageIcon(new ImageIcon("delete.png").getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT));
		ImageIcon BackImage = new ImageIcon(new ImageIcon("back.png").getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT));
		
		saveBtn=new JButton("DELETE",DeleteImage);
		saveBtn.setSize(170,40);
		saveBtn.setLocation(105,140);
		saveBtn.setFont(f);
		
		backBtn=new JButton("BACK",BackImage);
		backBtn.setSize(170,40);
		backBtn.setLocation(105,190);
		backBtn.setFont(f);
		
		
		c.add(eidLbl);
		c.add(eidTxt);
		c.add(saveBtn);
		c.add(backBtn);
		
		saveBtn.addActionListener(ae ->{
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
          			session.delete(e);
          			t.commit();
          			JOptionPane.showMessageDialog(null,"Record Deleted","Sucess",JOptionPane.PLAIN_MESSAGE);
          			System.out.println("\n \n \t \t \t Record Deleted  \t \t \t  \n \n");
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
				
		setSize(400,350);
		setLocationRelativeTo(null);
		setTitle("Delete Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
		
	}
}








