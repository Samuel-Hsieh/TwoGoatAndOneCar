package deerlight.com.twogoatandonecar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private LinearLayout game;
    private ImageView door1, door2, door3;
    private TextView plotText;
    private FloatingActionButton fab;
    private FrameLayout door1_bg, door2_bg, door3_bg;
    private String[] doorTag = new String[3];
    private int Count = 0;
    private int PlotCount = 0;
    private static final String GOAT = "山羊";
    private static final String CAR = "車";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        Random();
        door1.setOnClickListener(OnClickGroup());
        door2.setOnClickListener(OnClickGroup());
        door3.setOnClickListener(OnClickGroup());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (PlotCount) {
                    case 0:
                        plotText.setText(getString(R.string.Plot2));
                        game.setVisibility(View.VISIBLE);
                        PlotCount++;
                        break;
                    case 1:
                        plotText.setText(getString(R.string.Plot3));
                        fab.setVisibility(View.GONE);
                        fab.setImageResource(R.drawable.ic_refresh);
                        door1.setEnabled(true);
                        door2.setEnabled(true);
                        door3.setEnabled(true);
                        PlotCount++;
                        break;
                    default:
                        initData();
                        Random();
                        door1.setImageResource(R.drawable.ic_door);
                        door2.setImageResource(R.drawable.ic_door);
                        door3.setImageResource(R.drawable.ic_door);
                        break;
                }

            }
        });
    }

    private View.OnClickListener OnClickGroup() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.door1: {
                        if (Count == 0) {
                            if (doorTag[1].equals(GOAT)) {
                                door2.setImageResource(getImage(doorTag[1]));
                                plotText.setText(String.format(getString(R.string.Plot4), "2"));
                            } else {
                                door3.setImageResource(getImage(doorTag[2]));
                                plotText.setText(String.format(getString(R.string.Plot4), "3"));
                            }
                            door1_bg.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Selected));
                        } else {
                            door1.setImageResource(getImage(doorTag[0]));
                            if (doorTag[0].equals(CAR)) {
                                plotText.setText(getString(R.string.Plot_Winner));
                            } else {
                                plotText.setText(getString(R.string.Plot_Loser));
                            }
                            fab.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                    case R.id.door2: {
                        if (Count == 0) {
                            if (doorTag[2].equals(GOAT)) {
                                door3.setImageResource(getImage(doorTag[2]));
                                plotText.setText(String.format(getString(R.string.Plot4), "3"));
                            } else {
                                door1.setImageResource(getImage(doorTag[0]));
                                plotText.setText(String.format(getString(R.string.Plot4), "1"));
                            }
                            door2_bg.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Selected));
                        } else {
                            door2.setImageResource(getImage(doorTag[1]));
                            if (doorTag[1].equals(CAR)) {
                                plotText.setText(getString(R.string.Plot_Winner));
                            } else {
                                plotText.setText(getString(R.string.Plot_Loser));
                            }
                            fab.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                    case R.id.door3: {
                        if (Count == 0) {
                            if (doorTag[0].equals(GOAT)) {
                                door1.setImageResource(getImage(doorTag[0]));
                                plotText.setText(String.format(getString(R.string.Plot4), "1"));
                            } else {
                                door2.setImageResource(getImage(doorTag[1]));
                                plotText.setText(String.format(getString(R.string.Plot4), "2"));
                            }
                            door3_bg.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Selected));
                        } else {
                            door3.setImageResource(getImage(doorTag[2]));
                            if (doorTag[2].equals(CAR)) {
                                plotText.setText(getString(R.string.Plot_Winner));
                            } else {
                                plotText.setText(getString(R.string.Plot_Loser));
                            }
                            fab.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                }
                Count++;
            }
        };
        return Listener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        door1 = (ImageView) findViewById(R.id.door1);
        door2 = (ImageView) findViewById(R.id.door2);
        door3 = (ImageView) findViewById(R.id.door3);
        plotText = (TextView) findViewById(R.id.plotText);
        game = (LinearLayout) findViewById(R.id.game_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        door1_bg = (FrameLayout) findViewById(R.id.door1_bg);
        door2_bg = (FrameLayout) findViewById(R.id.door2_bg);
        door3_bg = (FrameLayout) findViewById(R.id.door3_bg);
    }

    private void initData() {
        Count = 0;
        PlotCount = 0;
        game.setVisibility(View.GONE);
        door1.setEnabled(false);
        door2.setEnabled(false);
        door3.setEnabled(false);
        fab.setImageResource(R.drawable.ic_next);
        door1_bg.setBackgroundColor(ContextCompat.getColor(this, R.color.unSelected));
        door2_bg.setBackgroundColor(ContextCompat.getColor(this, R.color.unSelected));
        door3_bg.setBackgroundColor(ContextCompat.getColor(this, R.color.unSelected));
        door3_bg.setBackgroundColor(ContextCompat.getColor(this, R.color.unSelected));
        plotText.setText(getString(R.string.Plot1));
    }

    private void Random() {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);

        for (int i = 0; i < 3; i++) {
            int tag = random.nextInt(list.size());
            if (list.get(tag).equals(0) || list.get(tag).equals(1)) {
                doorTag[i] = GOAT;
            } else {
                doorTag[i] = CAR;
            }
            list.remove(tag);
        }
    }

    private int getImage(String doorTag) {
        if (doorTag.equals(GOAT)) {
            return R.drawable.ic_goat;
        }
        return R.drawable.ic_car;
    }

}
