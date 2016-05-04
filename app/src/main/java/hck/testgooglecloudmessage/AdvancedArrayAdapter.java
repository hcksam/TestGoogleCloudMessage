package hck.testgooglecloudmessage;

import android.content.Context;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by hck on 20/9/2015.
 */
public class AdvancedArrayAdapter extends ArrayAdapter{
    private LinkedList<Integer> disablePositions = new LinkedList<Integer>();
    private LinkedList<Integer> onlyTextDisablePositions = new LinkedList<Integer>();
    private String[] listItems;
    private LinkedList<OptionListItem> optionListItems;
    private LinkedList<OptionListItem> filteredData;

    public AdvancedArrayAdapter(Context context, String[] listItems) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1, listItems);
        this.listItems = listItems;
        optionListItems = new LinkedList<>();
        for (String item:listItems){
            OptionListItem optionListItem = new OptionListItem(item, null);
            optionListItems.add(optionListItem);
        }
        filteredData = optionListItems;
    }

    public AdvancedArrayAdapter(Context context, LinkedList<OptionListItem> optionListItems) {
        super(context, android.R.layout.simple_list_item_2, android.R.id.text1, optionListItems);
        this.optionListItems = optionListItems;
        filteredData = optionListItems;
    }

    @Override
    public boolean isEnabled(int position){
        for (Integer disablePosition:disablePositions){
            if(position == disablePosition){
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
        text1.setTextSize(32);
        text1.setAutoLinkMask(Linkify.WEB_URLS);
        text1.setText(filteredData.get(position).getOption());
        text1.setEnabled(true);

        TextView text2 = null;

        if (listItems == null) {
            text2 = (TextView) view.findViewById(android.R.id.text2);
            text2.setText(filteredData.get(position).getIntroduction());
            text2.setEnabled(true);
        }

        for (Integer disablePosition : disablePositions) {
            if (position == disablePosition) {
                text1.setEnabled(false);
                if (text2 != null) {
                    text2.setEnabled(false);
                }
            }
        }

        for (Integer disablePosition : onlyTextDisablePositions) {
            if (position == disablePosition) {
                text1.setEnabled(false);
                if (text2 != null) {
                    text2.setEnabled(false);
                }
            }
        }

        return view;
    }

    public void filter(String keyword, boolean searchOption){
        if (keyword != null) {
            String filterText = keyword.toLowerCase();
            LinkedList<OptionListItem> filteredData = new LinkedList<OptionListItem>();

            for (OptionListItem optionListItem : optionListItems) {
                String filterTarget = (searchOption || optionListItem.getIntroduction() == null) ? optionListItem.getOption().toLowerCase() : optionListItem.getIntroduction().toLowerCase();
                int index = filterTarget.indexOf(filterText);

                if (index >= 0) {
                    filteredData.add(optionListItem);
                }
            }

            this.filteredData = filteredData;
        }else{
            this.filteredData = optionListItems;
        }
        notifyDataSetChanged();
    }

    public LinkedList<Integer> getDisablePositions() {
        return disablePositions;
    }

    public LinkedList<Integer> getOnlyTextDisablePositions() {
        return onlyTextDisablePositions;
    }
}
