package hck.testgooglecloudmessage;

import java.util.HashMap;

/**
 * Created by hck on 2/9/2015.
 */
public class OptionListItem {
    public final static String optionName = "option";
    public final static String introductionName = "introduction";
    public final static String[] arrayKey = {optionName, introductionName};

    private String option;
    private String introduction;
    private HashMap<String, String> dataMap = new HashMap<String, String>();

    @Override
    public String toString() {
        return "OptionListItem{" +
                "option='" + option + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public OptionListItem(){
    }

    public OptionListItem(String option, String introduction){
        this.option = option;
        this.introduction = introduction;
        setMap();
    }

    public OptionListItem(Object dataMapObject){
        dataMap = (HashMap<String, String>) dataMapObject;
        option = dataMap.get(optionName);
        introduction = dataMap.get(introductionName);
    }

    public OptionListItem(HashMap<String, String> dataMap){
        option = dataMap.get(optionName);
        introduction = dataMap.get(introductionName);
        this.dataMap = dataMap;
    }

    public void setMap(){
        dataMap.put(optionName,option);
        dataMap.put(introductionName, introduction);
    }

    public HashMap<String, String> getMap(){
        return dataMap;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
