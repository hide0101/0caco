package com.example.subtest.database;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

//ÉfÅ[É^å^çÏê¨
public class AddressEntry implements Parcelable {
    
    private Integer mId;
    private Integer mTab;
    private String mName;
    private String mFacebook;
    private String mTwitter;
    private String mLine;
    private String mSkype;

//address_id                            
//    address_name                            
//    address_facebook                                
//    address_twitter                         
//    address_line                            
//    address_skype   
    // address_tab

    public static final String TABLE_NAME = "address";
    public static final String COLUMN_ADDRESS_ID = "address_id";
    public static final String COLUMN_ADDRESS_NAME = "address_name";
    public static final String COLUMN_ADDRESS_FACEBOOK = "address_facebook";
    public static final String COLUMN_ADDRESS_TWITTER = "addres_twitter";
    public static final String COLUMN_ADDRESS_LINE = "address_line";
    public static final String COLUMN_ADDRESS_SKYPE = "address_skype";
    public static final String COLUM_ADDRESS_TAB = "address_tab";
    
    public static final Parcelable.Creator<AddressEntry> CREATOR = new Creator<AddressEntry>() {
        
        public AddressEntry[] newArray(int size) {
            return new AddressEntry[size];
        };
        
        public AddressEntry createFromParcel(Parcel source) {
            return new AddressEntry(source);
        
        };
    };
    
    public AddressEntry() {}
    
    public AddressEntry(Parcel in) {
        mId = (Integer) in.readSerializable();
        mName = in.readString();
        mFacebook = in.readString();
        mTwitter = in.readString();
        mLine = in.readString();
        mSkype = in.readString();
        mTab = (Integer) in.readSerializable();
        
    }
    
    public AddressEntry(Cursor in) {
        mId = in.getInt(in.getColumnIndexOrThrow(COLUMN_ADDRESS_ID));
        mName = in.getString(in.getColumnIndexOrThrow(COLUMN_ADDRESS_NAME));
        mFacebook = in.getString(in.getColumnIndexOrThrow(COLUMN_ADDRESS_FACEBOOK));
        mTwitter = in.getString(in.getColumnIndexOrThrow(COLUMN_ADDRESS_TWITTER));
        mLine = in.getString(in.getColumnIndexOrThrow(COLUMN_ADDRESS_LINE));
        mSkype = in.getString(in.getColumnIndexOrThrow(COLUMN_ADDRESS_SKYPE));
        mTab = in.getInt(in.getColumnIndexOrThrow(COLUM_ADDRESS_TAB));
    }
    @Override
    public int describeContents() {
        return 0;
        
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(mId);
        dest.writeString(mName);
        dest.writeString(mFacebook);
        dest.writeString(mTwitter);
        dest.writeString(mLine);
        dest.writeString(mSkype);
        dest.writeSerializable(mTab);
        
        
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getFacebook() {
        return mFacebook;
    }

    public void setFacebook(String mFacebook) {
        this.mFacebook = mFacebook;
    }

    public String getTwitter() {
        return mTwitter;
    }

    public void setTwitter(String mTwitter) {
        this.mTwitter = mTwitter;
    }

    public String getLine() {
        return mLine;
    }

    public void setLine(String mLine) {
        this.mLine = mLine;
    }

    public String getSkype() {
        return mSkype;
    }

    public void setSkype(String mSkype) {
        this.mSkype = mSkype;
    }

    public Integer getTab() {
        return mTab;
    }
    public void setTab(Integer mTab) {
        this.mTab = mTab;
    }
    
    
    @Override
    public String toString() {
        return getName();
    }
      
}
