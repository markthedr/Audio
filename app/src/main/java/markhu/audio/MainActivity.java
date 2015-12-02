package markhu.audio;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_STREAMS = 3;
    private SoundPool mSoundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimalGridAdapter animalGridAdapter = new AnimalGridAdapter(this,getLayoutInflater());

        GridView animalsGrid = (GridView) findViewById(R.id.gvAnimals);
        animalGridAdapter.setAdapter(animalGridAdapter);
        animalsGrid.setOnClickListener(animalGridAdapter);
    }

    private void initSoundPool(AnimalGridAdapter animalGridAdapter){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            mSoundPool = new SoundPool.Builder().setMaxStreams(MAX_STREAMS)
                    .setAudioAttributes(attributes).build();
        } else {
            mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        animalGridAdapter.loadSounds(mSoundPool,this);
    }
}
