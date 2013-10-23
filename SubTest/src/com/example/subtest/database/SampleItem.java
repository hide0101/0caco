package com.example.subtest.database;

public enum SampleItem {
    
    CATEGORY_FRIEND(1,"�F�B"),
    CATEGORY_PARENT(2,"�e��"),
    CATEGORY_OTHER(3,"���̑�");
    
    private final int mNumber;
    private final String mValue;
    
    private SampleItem(int number,String value){
        mNumber = number;
        mValue = value;
    }
    
    public SampleItem findItemById(int id){
        for(SampleItem item : values()){
            if(item.mNumber == id){
                return item;
            }
        }
        
        return null;
    }
    
    public int getNumber(){
        return mNumber;
    }
    
    public String getValue(){
        return mValue;
        
    }

}
