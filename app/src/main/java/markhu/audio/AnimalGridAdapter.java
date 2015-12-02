package markhu.audio;

import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

public class AnimalGridAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private static final int[] SOUND_MAP = {R.raw.bear_growl, R.raw.bird, R.raw.cat, R.raw.dog_bark};

    private static String[] ITEM_LABELS;
    private LayoutInflater mInflater;
    private SoundPool mSoundPool;
    private HashMap<Integer,Integer> mSoundIdMap;

    public AnimalGridAdapter(Context context, LayoutInflater layoutInflater){
        ITEM_LABELS = context.getResources().getStringArray(R.array.animal_sound_array);
        mInflater = layoutInflater;
        mSoundIdMap = new HashMap<Integer, Integer>();
    }



    @Override
    public int getCount() {
        return ITEM_LABELS.length;
    }

    @Override
    public Object getItem(int position) {
        return new Integer(SOUND_MAP[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView animalName = (TextView) convertView.findViewById(android.R.id.text1);
        animalName.setText(ITEM_LABELS[position]);
        return convertView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mSoundPool.play(mSoundIdMap.get(new Integer(position)), 1, 1, 1, 0, 1);

    }

    public void loadSounds(SoundPool soundPool, Context context){
        mSoundPool = soundPool;
        for(int position= 0; position< SOUND_MAP.length; position++){
            int soundID = soundPool.load(context, SOUND_MAP[position], 1);
            mSoundIdMap.put(position, soundID);
        }
    }
}
