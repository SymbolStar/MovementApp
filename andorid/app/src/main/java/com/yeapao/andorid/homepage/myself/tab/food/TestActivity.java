package com.yeapao.andorid.homepage.myself.tab.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.Network;
import com.yeapao.andorid.dialog.ChooseFoodDialogFragment;
import com.yeapao.andorid.model.CookListDetailModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fujindong on 2017/8/1.
 */

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity-cookbook";

    private List<Integer> list = new ArrayList<>();

    private List<CookListDetailModel.DataBean> cookList = new ArrayList<>();
    private List<CookListDetailModel.DataBean> cookListbk = new ArrayList<>();

    protected Subscription subscription;

    private String meal, type;

    private RecyclerView recyclerView;

    public static void start(Context context, String meal, String type) {
        Intent intent = new Intent();
        intent.putExtra("meal", meal);
        intent.putExtra("type", type);
        intent.setClass(context, TestActivity.class);
        context.startActivity(intent);

    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        meal = intent.getStringExtra("meal");
        type = intent.getStringExtra("type");


//        showDialogFragment();

        setContentView(R.layout.dialog_card_swipe);

        getNetWork(meal, type);

//        initView();
    }

    private void showDialogFragment() {

        FragmentManager fm = getSupportFragmentManager();
        ChooseFoodDialogFragment dialogFragment = new ChooseFoodDialogFragment();
        dialogFragment.show(fm, "dialog");
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.rv_card_list);
        recyclerView.setAdapter(new MyAdapter());
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), cookList);
        cardCallback.setOnSwipedListener(new OnSwipeListener<CookListDetailModel.DataBean>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, CookListDetailModel.DataBean dataBean, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
                Toast.makeText(TestActivity.this, direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
                if (direction == CardConfig.SWIPED_RIGHT) {
                    GlobalDataYepao.foodFlag = true;
                    dataBean.setMeal(meal);
                    dataBean.setPosition(type);
                    GlobalDataYepao.setFoodDetail(dataBean);
                    finish();
                }

            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(TestActivity.this, "data clear", Toast.LENGTH_SHORT).show();
//                initData();
                getNetWork(meal, type);

                recyclerView.getAdapter().notifyDataSetChanged();
//                recyclerView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        initData();
//                        recyclerView.getAdapter().notifyDataSetChanged();
//                    }
//                }, 3000L);
            }

        });
        ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    public Context getContext() {
        return this;
    }


    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_card_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
//            avatarImageView.setImageResource(list.get(position));

            ((MyViewHolder) holder).foodDetail.setText(cookList.get(position).getName()+" "+cookList.get(position).getMeasure());
            GlideUtil glideUtil = new GlideUtil();
            glideUtil.glideLoadingImage(getContext(), cookList.get(position).getImage(), R.drawable.food1, ((MyViewHolder) holder).avatarImageView);
        }

        @Override
        public int getItemCount() {
            return cookList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;
            ImageView likeImageView;
            ImageView dislikeImageView;
            TextView foodDetail;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
                foodDetail = (TextView) itemView.findViewById(R.id.tv_food_detail);

            }

        }
    }


    private void getNetWork(String meal, String type) {
        subscription = Network.getYeapaoApi()
                .getCookDetail(meal, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelObserver);
    }

    Observer<CookListDetailModel> modelObserver = new Observer<CookListDetailModel>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtil.e(TAG, e.toString());

        }

        @Override
        public void onNext(CookListDetailModel model) {
            LogUtil.e(TAG, model.getErrmsg());
            if (model.getErrmsg().equals("ok")) {
                cookList = model.getData();
                cookListbk = model.getData();
                initView();
            }
        }
    };

}
