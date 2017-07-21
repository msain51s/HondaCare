package com.intellinet.hondatwowheeler.utility;

/**
 * Created by Chandan Dwivedi on 13.07.2017.
 */



import android.content.Context;


/**
 * Created by Administrator on 11/28/2016.
 */
public class Application extends android.app.Application {
    private static Application ourInstance = new Application();
    public static Context mContext;
    public static int selectedCategoryId;
    public static String selectedAssignToPersonId;
    public static int selectedUserTypeId;
    public static int selectedPropertyTypeId=-1;

    public static Application getInstance() {
        return ourInstance;
    }

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();

    }



}
