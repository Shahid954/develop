package com.zenwork.smoke;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zenwork.library.AppLibrary;
import com.zenwork.library.TestBase;
import com.zenwork.pageObject.DashboardPage;
import com.zenwork.pageObject.HomePage;
import com.zenwork.pageObject.LoginPage;
import com.zenwork.pageObject.ManagePayerPage;
import com.zenwork.pageObject.People;
import com.zenwork.pageObject.SideNavigation;

public class BulkDeletePayer extends TestBase {

	public class ValidateBulkDeletePayer extends TestBase{
	    
	    @BeforeClass
	    public void setUp() {
	        appLibrary = new AppLibrary("ValidateBulkDeletePayer");
	    }
	    
	    @Test
	    public void deletePayer() throws Exception {
	        
	    appLibrary.getDriverInstance();
	    appLibrary.launchApp();
	    
	    HomePage ap = new HomePage(appLibrary);
	    DashboardPage dp = new DashboardPage(appLibrary);
	    SideNavigation sn = new SideNavigation(appLibrary);
	    LoginPage lp = ap.navigateToLoginPage();	   
	    lp.login(getUserID(), getPassword());
	    dp.plansPopupCancel();
	    People p = sn.selectPeople();
	    ManagePayerPage mpp = p.selectManagePayer();
	    mpp.uploadFileForPayer();
	    mpp.bulkDeletePayerButton("delete");
	    
	    
	    }

	}
}
