package com.example.what2do.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.what2do.R;
import com.example.what2do.model.CardStackGenreAdapter;
import com.example.what2do.model.FakeBackend;
import com.example.what2do.model.ItemModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

import static com.yuyakaido.android.cardstackview.Direction.Bottom;
import static com.yuyakaido.android.cardstackview.Direction.Left;
import static com.yuyakaido.android.cardstackview.Direction.Right;
import static com.yuyakaido.android.cardstackview.Direction.Top;

public class SwipeGenreActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SwipeGenreActivity";
    private CardStackLayoutManager manager;
    private CardStackGenreAdapter adapter;
    private CardStackView cardStackView;
    private int superlikes = 1;
    private int bans = 1;
    private List<Integer> swipes = new ArrayList<Integer>() {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_swipe);

        cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                //Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Right) {
                    //Toast.makeText(SwipeGenreActivity.this, "Direction Right", Toast.LENGTH_SHORT).show();
                    swipes.add(1);
                }
                if (direction == Direction.Top) {
                    //Toast.makeText(SwipeGenreActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                    superlikes--;
                    swipes.add(2);
                    TextView superlikesText = (TextView) findViewById(R.id.textView2);
                    superlikesText.setText(superlikes + " left");
                }
                if (direction == Left) {
                    //Toast.makeText(SwipeGenreActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                    swipes.add(3);
                }
                if (direction == Direction.Bottom) {
                    //Toast.makeText(SwipeGenreActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                    bans--;
                    TextView bansText = (TextView) findViewById(R.id.textView3);
                    bansText.setText(bans + " left");
                    swipes.add(4);
                }

                else if (swipes.size() == adapter.getItems().size()) {
                    setResult(RESULT_OK);
                    finish();
                }
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.incrementProgressBy(1);


                List dir = new ArrayList<Direction>();
                dir.add(Left);
                dir.add(Right);
                if (superlikes > 0) {
                    dir.add(Top);
                }
                if (bans > 0) {
                    dir.add(Bottom);
                }
                manager.setDirections(dir);
            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardCanceled: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        cardStackView.setLayoutManager(manager);
        adapter = new CardStackGenreAdapter(FakeBackend.getGenres());
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

        TextView superlikesText = (TextView) findViewById(R.id.textView2);
        superlikesText.setText(superlikes + " left");
        TextView bansText = (TextView) findViewById(R.id.textView3);
        bansText.setText(bans + " left");

        ImageView imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView5.setOnClickListener(this);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(adapter.getItems().size());
        progressBar.setProgress(0);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.imageView5 && swipes.size() > 0) {
            cardStackView.rewind();

            if (swipes.get(swipes.size() - 1) == 2) {
                superlikes++;
                TextView superlikesText = (TextView) findViewById(R.id.textView2);
                superlikesText.setText(superlikes + " left");
            }
            else if (swipes.get(swipes.size() - 1) == 4) {
                bans++;
                TextView bansText = (TextView) findViewById(R.id.textView3);
                bansText.setText(bans + " left");
            }
            swipes.remove(swipes.size() - 1);


            List dir = new ArrayList<Direction>();
            dir.add(Left);
            dir.add(Right);
            if (superlikes > 0) {
                dir.add(Top);
            }
            if (bans > 0) {
                dir.add(Bottom);
            }
            manager.setDirections(dir);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.incrementProgressBy(-1);

        }
    }

}
