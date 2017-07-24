package com.yeapao.andorid.lessondetails;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scottfu.sflibrary.util.LogUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.homepage.lesson.LessonMessageAdapter;
import com.yeapao.andorid.model.LessonDetailData;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * Created by fujindong on 2017/7/20.
 */

public class LessonDetailContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static Context mContext;
    private LayoutInflater inflater;
    private LessonDetailContentAdapter mAdapter = this;
    private boolean refreshFlag = false;
    private int recycleId = 0;
    private int pagerId = 0;


    private static LessonDetailData lessonDetailData = new LessonDetailData();


    public LessonDetailContentAdapter(Context context ,LessonDetailData lessonDetailData) {

        mContext = context;
        inflater = LayoutInflater.from(context);
        this.lessonDetailData = lessonDetailData;
    }

    public  void refreshSelectNum(int recycleViewItemPosition , int ViewPagerPosition) {
        refreshFlag = true;
        recycleId = recycleViewItemPosition;
        pagerId = ViewPagerPosition;
        LogUtil.e(String.valueOf(recycleViewItemPosition)+"   ---",String.valueOf(ViewPagerPosition));
        lessonDetailData.getData().getActionEssentialsList().get(recycleViewItemPosition).setCurrentId(ViewPagerPosition);
//        notifyItemChanged(recycleViewItemPosition);
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LessonDetailViewHolder(inflater.inflate(R.layout.item_lesson_content, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (!refreshFlag) {

            ((LessonDetailViewHolder) holder).tvDoContent.setText(
                    lessonDetailData.getData().getActionEssentialsList().get(position).getContent());

            ((LessonDetailViewHolder) holder).initContainer(position);
            int currentItem = ((LessonDetailViewHolder) holder).overlapPager.getCurrentItem();

            ((LessonDetailViewHolder) holder).tvTextNum.setText(String.valueOf(currentItem) + "/" +
                    String.valueOf(lessonDetailData.getData().getActionEssentialsList().get(position).getImage().size()));

            ((LessonDetailViewHolder) holder).tvTextNum.setVisibility(View.GONE);

            ((LessonDetailViewHolder) holder).overlapPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int ppp) {
                    LogUtil.e("onpageSelected", String.valueOf(ppp));
                    refreshSelectNum(position, ppp);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } else {

//            if (position == recycleId) {
//                ((LessonDetailViewHolder) holder).tvTextNum.setText(String.valueOf(pagerId) + "/" +
//                        String.valueOf(lessonDetailData.getData().getActionEssentialsList().get(position).getImage().size()));
//            }

        }

    }

    @Override
    public int getItemCount() {
        return lessonDetailData.getData().getActionEssentialsList().size();
    }

    class LessonDetailViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.overlap_pager)
        ViewPager overlapPager;
        @BindView(R.id.pager_container)
        PagerContainer pagerContainer;
        @BindView(R.id.tv_text_num)
        TextView tvTextNum;
        @BindView(R.id.tv_do_content)
        TextView tvDoContent;

        LessonDetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void initContainer(final int position) {
            LogUtil.e("container", String.valueOf(position));
//            PagerContainer container = (PagerContainer) findViewById(R.id.pager_container);
            ViewPager pager = pagerContainer.getViewPager();
            pager.setAdapter(new MyPagerAdapter(position));
            pager.setClipChildren(false);
            //
            pager.setOffscreenPageLimit(15);

            pagerContainer.setPageItemClickListener(new PageItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    LogUtil.e("onItemClick",String.valueOf(i));
//                    LessonDetailContentAdapter.this.notifyItemChanged(position);
                }
            });


            if(true){

                new CoverFlow.Builder()
                        .with(pager)
                        .scale(0.3f)
                        .pagerMargin(mContext.getResources().getDimensionPixelSize(R.dimen.pager_margin))
                        .spaceSize(0f)
                        .build();

            }else{
                pager.setPageMargin(30);
            }
        }






    }




     class MyPagerAdapter extends PagerAdapter {


        private int mPosition;

        public MyPagerAdapter(int position) {
            mPosition = position;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.item_cover,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
            Glide.with(mContext)
                    .load(ConstantYeaPao.HOST + lessonDetailData.getData().getActionEssentialsList().get(mPosition).getImage().get(position))
                    .asBitmap()
                    .placeholder(R.drawable.home_banner_take_place)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.home_banner_take_place)
                    .centerCrop()
                    .into(imageView);
//            imageView.setImageDrawable(getResources().getDrawable(DemoData.covers[position]));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            TextView selectedNum = (TextView) view.findViewById(R.id.tv_selected_num);
            selectedNum.setText(String.valueOf(position+1)+"/"+String.valueOf(getCount()));
//            refreshSelectNum(mPosition,position);

            container.addView(view);




            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return lessonDetailData.getData().getActionEssentialsList().get(mPosition).getImage().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }



    }


}
