package com.yeapao.andorid.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.scottfu.sflibrary.util.GlideUtil;
import com.scottfu.sflibrary.util.ScreenUtil;
import com.scottfu.sflibrary.zxing.GenerateQRCode;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.model.UserData;

import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

/**
 * Created by fujindong on 2017/7/20.
 */

public class DialogUtils {



    public static final String DEFAULT_DIALOG_MESSAGE = "加载中...";
    public static ProgressDialog pDialog = null;

    public static void showProgressDialog(Context context){
        showProgressDialog(context, DEFAULT_DIALOG_MESSAGE);
    }

    public static void showProgressDialog(Context context, boolean cancelable) {
        showProgressDialog(context,DEFAULT_DIALOG_MESSAGE,cancelable,false);
    }
    public static void showProgressDialog(Context context, boolean cancelable,boolean isCang) {
        showProgressDialog(context,DEFAULT_DIALOG_MESSAGE,cancelable,isCang);
    }

    public static void showProgressDialog(Context context, String message){
        showProgressDialog(context, message, false,false);
    }


    public static void showProgressDialog(Context context, String message, boolean cancelable, boolean isCang){
        if(pDialog != null){
            pDialog.dismiss();
            pDialog = null;
        }
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(cancelable);
        pDialog.show();
        View v;
        if (isCang) {
            v = LayoutInflater.from(context).inflate(R.layout.dialog_loading_cang, null);
        } else {
             v = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        }



        pDialog.setContentView(v);
        WindowManager.LayoutParams params = pDialog.getWindow().getAttributes();
        params.width = ScreenUtil.dpToPxInt(context, 100);
        params.height = ScreenUtil.dpToPxInt(context, 100);
        pDialog.getWindow().setAttributes(params);
    }

    public static ProgressDialog getProgressDialog(Context context, String message){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        return dialog;
    }


    public static void cancelProgressDialog(){
        if(pDialog == null) return;
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

//卡片滑动效果
    public static void showCardSwipe(final Context context) {

         List<Integer> listV = new ArrayList<>();
        listV.add(R.drawable.food1);
        listV.add(R.drawable.food2);
        listV.add(R.drawable.food3);
        listV.add(R.drawable.food4);
        listV.add(R.drawable.food5);

        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        final RecyclerView recyclerView;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_card_swipe, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_card_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MyAdapter(context,listV));
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), listV);
        cardCallback.setOnSwipedListener(new OnSwipeListener<Integer>() {

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
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer o, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
                Toast.makeText(context, direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(context, "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        initData();
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 3000L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
        Window window = dialog.getWindow();
        window.setContentView(view);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 1);
        dialog.getWindow().setAttributes(params);



    }
    private static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Integer> list = new ArrayList<>();
        private Context mContext;


        public MyAdapter(Context context, List<Integer> list) {
            this.list = list;
            mContext = context;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_card_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
            avatarImageView.setImageResource(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;
            ImageView likeImageView;
            ImageView dislikeImageView;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
            }

        }
    }




    public static void showQRCode(final Context context, String code, UserData userData) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        ImageView userHead;
        ImageView userGarde;
        ImageView userBadge;
        TextView userName;
        TextView userTell;
        ImageView QRCode;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_show_qrcode, null);
        userHead = (ImageView) view.findViewById(R.id.iv_qrcode_head);
        userName = (TextView) view.findViewById(R.id.tv_qrcode_name);
        userTell = (TextView) view.findViewById(R.id.tv_user_tell);
        QRCode = (ImageView) view.findViewById(R.id.iv_qrcode);

        GenerateQRCode generateQRCode = new GenerateQRCode();
        generateQRCode.createEnglishQRCodeWithLogo(context,code,QRCode,R.drawable.y_you);

        GlideUtil glideUtil = new GlideUtil();
        glideUtil.glideLoadingImage(context, ConstantYeaPao.HOST+userData.getHead(),R.drawable.y_you,userHead);
        userName.setText(userData.getName());
        userTell.setText(userData.getPhone());

        Window window = dialog.getWindow();
        window.setContentView(view);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);


    }


    public static void showDialog(final Context context, String title, String hint, String item1, String item2, final DialogCallback listener){
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();

        final TextView onLine;
        final TextView down;

        Button cancel;
        Button sure;

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_lesson_view, null);
        if(title != null){
            ((TextView)v.findViewById(R.id.tv_order_title)).setText(title);
        }
        if (hint != null) {
            ((TextView) v.findViewById(R.id.tv_dialog_hint)).setText(hint);
        }

            down = (TextView) v.findViewById(R.id.tv_item_1);
            onLine = (TextView) v.findViewById(R.id.tv_item_2);
            onLine.setVisibility(View.GONE);
            down.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape));

        down.setText(item1);
        onLine.setText(item2);
            onLine.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    listener.onItemClick(1);
                    onLine.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape));
                    down.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape_n));
                }
            });

        down.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    listener.onItemClick(2);
                    down.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape));
                    onLine.setBackground(context.getResources().getDrawable(R.drawable.dialog_shape_n));
                }
            });


        cancel = (Button) v.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLeftClick();
                dialog.dismiss();
            }
        });
        sure = (Button) v.findViewById(R.id.btn_sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setContentView(v);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);
    }


    public static void showRefundDialog(Context context, final DialogCallback listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Button cancel;
        Button sure;

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_refund, null);

        cancel = (Button) v.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLeftClick();
                dialog.dismiss();
            }
        });
        sure = (Button) v.findViewById(R.id.btn_sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setContentView(v);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);
    }

    public static void showMessageTwoButtonDialog(Context context,String title,String content, final DialogCallback listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Button cancel;
        Button sure;
        TextView titleTextView;
        TextView hintTextView;

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_refund, null);

        titleTextView = (TextView) v.findViewById(R.id.tv_order_title);
        hintTextView = (TextView) v.findViewById(R.id.tv_dialog_hint);

        titleTextView.setText(title);
        hintTextView.setText(content);

        cancel = (Button) v.findViewById(R.id.btn_cancel);
        cancel.setText("取消");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLeftClick();
                dialog.dismiss();
            }
        });
        sure = (Button) v.findViewById(R.id.btn_sure);
        sure.setText("确定");
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setContentView(v);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);
    }

    public static void showMessageDialog(Context context,String title,String content,String buttonContent, final DialogCallback listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Button sure;

        TextView contentTextView;
        TextView titleTextView;

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_single_bottom_title, null);

        titleTextView = (TextView) v.findViewById(R.id.tv_order_title);
        titleTextView.setText(title);
        contentTextView = (TextView) v.findViewById(R.id.tv_dialog_hint);
        contentTextView.setText(content);

        sure = (Button) v.findViewById(R.id.btn_sure);
        sure.setText(buttonContent);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setContentView(v);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);
    }


    public static void showCangInputDialog(Context context, final CangInputCallback listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        final EditText cangId;
        TextView startFit;
        ImageView cancelImageView;


        View v = LayoutInflater.from(context).inflate(R.layout.dialog_input_cang_code, null);

        cangId = (EditText) v.findViewById(R.id.et_cang_id);
        startFit = (TextView) v.findViewById(R.id.tv_start_fit);
        cancelImageView = (ImageView) v.findViewById(R.id.iv_cancel_input);

//        cangId.requestFocus();
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        startFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getContent(cangId.getText().toString());
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setContentView(v);
        WindowManager m = ((Activity) context).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);
    }



}
