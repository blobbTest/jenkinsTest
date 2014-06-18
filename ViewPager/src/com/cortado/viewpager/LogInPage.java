package com.cortado.viewpager;

import com.cortado.viewpager.R;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author andre.boddenberg@gmx.de
 * 
 * The LogInPage class provides a simple Fragment, which shows a login mask.<br>
 * The mask consists of two Textviews "User Name" and "Password", two corresponding EditTexts "login_user" and "login_pw"<br>
 * and a "Send" button.<br><br>
 * 
 * The "Send" button has no real login function. It only checks the entered texts <br>
 * and fires a dialog with a case specific message.
 * 	 	
 * @param etName EdigtText of the "User Name"	
 * @param etPW   EditText of the "Password"
 */
public class LogInPage extends Fragment
{
	private EditText etName;
	private EditText etPw;
	
	private static final String UserAndPwDialog   = "User: ";
	private static final String onlyUserDialog    = "Please enter the \"Password\".";
	private static final String onlyPwDialog      = "Please enter the \"User Name\".";
	private static final String nothingDialog	 =  "Please enter a \"User Name\" and its \"Password\".";
		
	/**
	 * "static LoginPage constructor"
	 *  
	 * @return f Fragment of type LogInPage will be returned.
	 */
	public static LogInPage newInstance()
	{
		LogInPage f = new LogInPage();
		Bundle bdl = new Bundle(1);
	    f.setArguments(bdl);
	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		// setting layout to inflate
		View v = inflater.inflate(R.layout.login_mask, container, false);		
		
		// point the EditTexts to an id in layout.xml
		etName  = (EditText) v.findViewById(R.id.login_name);							
		etPw = (EditText) v.findViewById(R.id.login_pw);

		// create button and point to id in layout.xml
		final Button button = (Button) v.findViewById(R.id.login_button);		
		
		// anonymous class for button OnClickListener
	    button.setOnClickListener(new View.OnClickListener() 
	    {
	        public void onClick(View v) 
	        {
	           // instantiate  dialog and set title
	           Builder dialog = new Builder(getActivity());
	           dialog.setTitle("Login");
	    	     	   
	           // getting String of EditText User
	           String name = etName.getText().toString();
	           String pw = etPw.getText().toString();
	           	   
	    	   // evaluate entered texts and fire dialog 
	           // with specific message
	    	   if(pw.length() > 1 && name.length() > 1) 					// case: username and password entered
	    	   {
	    		   dialog.setMessage(UserAndPwDialog + name);
	    		   dialog.show();
	    		   return;
	    	   }
	    	   if(pw.length() > 1 && name.length() < 1)						// case: only password entered
	    	   {
	    		   dialog.setMessage(onlyPwDialog);
	    		   dialog.show();
	    		   return;
	    	   }
	    	   if(pw.length() < 1 && name.length() > 1)						// case: only username entered
	    	   {
	    		   dialog.setMessage(onlyUserDialog);
	    		   dialog.show();
	    		   return;
	    	   }
	    	   if(pw.length() < 1 && name.length() < 1)						// case: nothing entered
	    	   {
	    		   dialog.setMessage(nothingDialog);
	    		   dialog.show();
	    		   return;
	    	   }
	        }
	    });
        return v;
    }	
	
	// GETTER
	/**
	 * getter: gives the test class the possibility to reference to the dialog message of class under test.
	 * 
	 * @return String: dialog message if Username and Password have been entered
	 */
	public static String getUserAndPwDialog()
	{
		return UserAndPwDialog;
	}
	
	/**
	 * getter: gives the test class the possibility to reference to the dialog message of class under test.
	 * 
	 * @return String: dialog message if only the Username has been entered
	 */
	public static String getOnlyUserDialog()
	{
		return onlyUserDialog;
	}
	
	/**
	 * getter: gives the test class the possibility to reference to the dialog message of class under test.
	 * 
	 * @return String: dialog message if only the Password has been entered
	 */
	public static String getOnlyPwDialog()
	{
		return onlyPwDialog;
	}
	
	/**
	 * getter: gives the test class the possibility to reference to the dialog message of class under test.
	 * 
	 * @return String: dialog message if nothing has been entered
	 */
	public static String getNothingDialog()
	{
		return nothingDialog;
	}
	
}
