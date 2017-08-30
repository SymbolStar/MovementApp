package com.yeapao.brake.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapao.brake.R;
import com.yeapao.brake.bean.ScreenModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by fujindong on 2017/8/30.
 */

public class LessonMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "LessonMessageAdapter";

    private Context mContext;
    private LayoutInflater inflater;

    private List<ScreenModel.ClassesBean> messageList = new ArrayList<>();

    public LessonMessageAdapter(Context context, List<ScreenModel.ClassesBean> classesBeanList) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        messageList = classesBeanList;

//        for (int i = 0; i <6; i++) {
//            ScreenModel.ClassesBean classesBean = new ScreenModel.ClassesBean();
//            classesBean.setEmp_name("教练");
//            classesBean.setLesson_name("瑜伽课");
//            classesBean.setStart_time("10:00");
//            classesBean.setPlace_name("瑜伽房");
//            messageList.add(classesBean);
//        }
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LessonHolder(inflater.inflate(R.layout.lesson_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((LessonHolder)holder).time.setText(messageList.get(position).getStart_time());
        ((LessonHolder) holder).lesson.setText(messageList.get(position).getLesson_name());
        ((LessonHolder) holder).coach.setText(messageList.get(position).getEmp_name());
        ((LessonHolder) holder).address.setText(messageList.get(position).getPlace_name());

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class LessonHolder extends RecyclerView.ViewHolder {

        private TextView time;
        private TextView lesson;
        private TextView coach;
        private TextView address;


        public LessonHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            time = (TextView) view.findViewById(R.id.tv_time);
            lesson = (TextView) view.findViewById(R.id.tv_lesson);
            coach = (TextView) view.findViewById(R.id.tv_coach);
            address = (TextView) view.findViewById(R.id.tv_address);
        }


    }



}
