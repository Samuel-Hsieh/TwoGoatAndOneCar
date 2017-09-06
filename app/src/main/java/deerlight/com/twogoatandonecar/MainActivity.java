package deerlight.com.twogoatandonecar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    //    Button door1, door2, door3;
    LinearLayout game;
    ImageView door1, door2, door3;
    TextView plotText;
    FloatingActionButton fab;
    FrameLayout door1_bg, door2_bg, door3_bg;
    List<Integer> tag_array = new ArrayList<>();
    String[] doorTag = new String[3];
    Random random = new Random();
    int Count = 0;
    int PlotCount = 0;

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
                        ArrayList<ImageView> arrayList = new ArrayList<>();
                        arrayList.add(0, door1);
                        arrayList.add(1, door2);
                        arrayList.add(2, door3);
                        for (int i = 0; i < arrayList.size(); i++) {
                            arrayList.get(i).setImageResource(R.drawable.ic_door);
                        }
                        Snackbar.make(view, "刷新", Snackbar.LENGTH_LONG).show();
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
                            if (!(doorTag[1].equals("羊"))) {
                                door3.setImageResource(getImage(doorTag[2]));
                                plotText.setText(String.format(getString(R.string.Plot4), "3"));
                            } else {
                                door2.setImageResource(getImage(doorTag[1]));
                                plotText.setText(String.format(getString(R.string.Plot4), "2"));
                            }
                            door1_bg.setBackgroundColor(Color.parseColor("#FF8888"));
                        }
                        if (Count == 1) {
                            door1.setImageResource(getImage(doorTag[0]));
                            if (doorTag[0].equals("車")) {
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
                            if (!(doorTag[0].equals("羊"))) {
                                door3.setImageResource(getImage(doorTag[2]));
                                plotText.setText(String.format(getString(R.string.Plot4), "3"));
                            } else {
                                door1.setImageResource(getImage(doorTag[0]));
                                plotText.setText(String.format(getString(R.string.Plot4), "1"));
                            }
                            door2_bg.setBackgroundColor(Color.parseColor("#FF8888"));
                        }
                        if (Count == 1) {
                            door2.setImageResource(getImage(doorTag[1]));
                            if (doorTag[1].equals("車")) {
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
                            if (!(doorTag[0].equals("羊"))) {
                                door2.setImageResource(getImage(doorTag[1]));
                                plotText.setText(String.format(getString(R.string.Plot4), "2"));
                            } else {
                                door1.setImageResource(getImage(doorTag[0]));
                                plotText.setText(String.format(getString(R.string.Plot4), "1"));
                            }
                            door3_bg.setBackgroundColor(Color.parseColor("#FF8888"));
                        }
                        if (Count == 1) {
                            door3.setImageResource(getImage(doorTag[2]));
                            if (doorTag[2].equals("車")) {
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
        door1_bg.setBackgroundColor(Color.parseColor("#888888"));
        door2_bg.setBackgroundColor(Color.parseColor("#888888"));
        door3_bg.setBackgroundColor(Color.parseColor("#888888"));
        plotText.setText(getString(R.string.Plot1));
    }

    private void Random() {
        tag_array.clear();
        int tag;
        while (tag_array.size() < 3) {
            tag = random.nextInt(3);
            if (tag_array.contains(tag)) {
                continue;
            } else {
                tag_array.add(tag);
            }
        }
        for (int i = 0; i < doorTag.length; i++) {
            if (tag_array.get(i).equals(0) || tag_array.get(i).equals(1)) {
                doorTag[i] = "羊";
            } else {
                doorTag[i] = "車";
            }
        }
    }

    private int getImage(String doorTag) {
        switch (doorTag) {
            case "羊":
                return R.drawable.ic_goat;
            case "車":
                return R.drawable.ic_car;
            default:
                return R.drawable.ic_door;
        }
    }
}
