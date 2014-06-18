package com.cortado.viewpager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.cortado.viewpager.R;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

/**
 * @author andre.boddenberg@gmx.de
 * 
 * 
 * 
 * @param MyPageAdapter pageAdapter needed for the ViewPager
 */
public class MainActivity extends FragmentActivity {
	private MyPageAdapter pageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        
        List<Fragment> fragments = null;
       
		try 
		{									
			fragments = getFragments();
		} 	
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
        
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
    private List<Fragment> getFragments() throws IOException
    {
    	List<Fragment> fList = new ArrayList<Fragment>();	
    
    	fList.add(ImagePage.newInstance(loadImage("HubbleGalaxyXXL.jpg")));
    	fList.add(LogInPage.newInstance());

    	return fList;
    }

    /**
  	 *
     */
    private class MyPageAdapter extends FragmentPagerAdapter {
    	private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }
     
        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
    
    /**
     * 
     * Method to parse an Image from the assets folder via assetManager. It returns a Bitmap object.
     * 
     * @param file file to be parsed. give path if file is in subfolder of assets.
     * @return bitmap parsed image from assetfolder.
     * @throws IOException 
     */
    private Bitmap loadImage(String file) throws IOException
    {
    	AssetManager assetManager = getAssets();
        InputStream istr = null;
    
        istr = assetManager.open(file);
     
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        
        return bitmap;
    }
}
   

