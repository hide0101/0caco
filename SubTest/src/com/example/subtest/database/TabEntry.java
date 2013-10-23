package com.example.subtest.database;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class TabEntry implements Parcelable {
    
    private Integer mTabId;
    private String mIdName;
    
    public static final String TABLE_NAME = "AddressTab";
    public static final String COLUMN_ADDRESS_TABID = "address_tabid";
    public static final String COLUMN_ADDRESS_IDNAME = "address_idname";
    
    public static final Parcelable.Creator<TabEntry> CREATOR =  new Creator<TabEntry>() {
    
        public TabEntry[] newArray(int size) {
            return new TabEntry[size];
            
        };

        @Override
        public TabEntry createFromParcel(Parcel source) {
           
            return new TabEntry(source);
        };
    
    };
    
    public TabEntry() {}
    
    
    public TabEntry(Parcel in) {
        
        mTabId = (Integer) in.readSerializable();
        mIdName = in.readString();
        
    }

    
    public TabEntry(Cursor in) {
        mTabId = in.getInt(in.getColumnIndexOrThrow(COLUMN_ADDRESS_TABID));
        mIdName = in.getString(in.getColumnIndexOrThrow(COLUMN_ADDRESS_IDNAME));
        
    }
    @Override
    public int describeContents() {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(mTabId);
        dest.writeString(mIdName);
        
    }
    
    public Integer getTabId() {
        return mTabId;
    }
    
    public void setTabId(Integer mTabId) {
        
        this.mTabId = mTabId;
        
    }
    
    public String getIdName(){
        
        return mIdName;
        
    }

    public void setIdName(String mIdName){
        
        this.mIdName = mIdName;
    }
}
