package hck.testgooglecloudmessage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.LinkedList;

public class MessagesActivity extends AppCompatActivity {
    Context context;
    ListView messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        context = this;

        messagesList = (ListView) findViewById(R.id.messagesList);

        setMessagesList();
    }

    private void setMessagesList(){
        LinkedList<OptionListItem> messagesData = Utility.readFileToItems(getFilesDir(), FixData.messagesFileName);
        if (messagesData != null && messagesData.size() >0) {
            AdvancedArrayAdapter adapter = new AdvancedArrayAdapter(context, messagesData);
            messagesList.setAdapter(adapter);
        }
//        LinkedList<String> test = Utility.readFile(getFilesDir(), FixData.messagesFileName);
//        for (String line:test){
//            Log.w("hck line", line);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMessagesList();
    }
}
