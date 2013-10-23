package com.example.subtest.database;

import org.w3c.dom.Text;

public enum AddressTabItem {
    
    CATEGORY_UNIV(1,"ëÂäw"),
    CATEGORY_PARENT(2,"êeê ");

    private final int mNumber;
    private final String mValue;
    
    private AddressTabItem(int number, String value){
         mNumber = number;
         mValue = value;
    }
    
    public AddressTabItem findItemById(int id){
        for(AddressTabItem item : values()){
            if(item.mNumber == id){
         
        return item;
        
    }
}

        return null;
    }
    
    public int getNumber(){
        return  mNumber;
    }
    public String getValue(){
        return mValue;
    }
    }