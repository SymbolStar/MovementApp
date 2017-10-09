package com.yeapao.andorid.homepage.circle.circledetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.SoftInputUtils;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.LoginActivity;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseFragment;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.CommunityDetailModel;
import com.yeapao.andorid.util.GlobalDataYepao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fujindong on 2017/7/18.
 */

public class CircleDetailFragmentView extends BaseFragment implements CircleDetailContract.View {
    private static final String TAG = "CircleDetailFragment";

    @BindView(R.id.rv_circle_detail_list)
    RecyclerView rvCircleDetailList;
    @BindView(R.id.srl_circle_detail)
    SwipeRefreshLayout srlCircleDetail;
    @BindView(R.id.iv_circle_finger)
    ImageView ivCircleFinger;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.tv_circle_publish)
    TextView tvCirclePublish;
    Unbinder unbinder;

    private boolean fingerStatus = false;
    private CircleDetailContract.Presenter mPresenter;
    private LinearLayoutManager llm;

    private int commentStatus = 0;//0 写评论 1 回复评论 2 回复 评论
    private int commentPos = 0;
    private int childCommentPos = 0;


    private CircleDetailMessageAdapter mMessageAdapter;

    private CommunityDetailModel mCommunityDetailModel;

    public CircleDetailFragmentView() {

    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        srlCircleDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });
        srlCircleDetail.setColorSchemeResources(R.color.colorPrimary);
        initViews(view);
        mPresenter.start();
        return view;
    }

    @Override
    public void setPresenter(CircleDetailContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        tvCirclePublish.setVisibility(View.GONE);
        ivCircleFinger.setVisibility(View.VISIBLE);
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvCircleDetailList.setLayoutManager(llm);

        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    ivCircleFinger.setVisibility(View.GONE);
                    tvCirclePublish.setVisibility(View.VISIBLE);
                } else {
                    tvCirclePublish.setVisibility(View.GONE);
                    ivCircleFinger.setVisibility(View.VISIBLE);
                }

//                if (s.length() > 140) {
//                    ToastManager.showToast(getContext(), "内容最多只能输入140个字");
//                    s.delete(140, s.length());
//                    etComment.setText(s);
//                    etComment.setSelection(140);
//                }
            }
        });


    }

    @OnClick(R.id.tv_circle_publish)
    void setTvCirclePublish(View view) {
        if (!GlobalDataYepao.isLogin()) {
            LoginActivity.start(getContext());
        } else {
            String content = etComment.getText().toString();
            if (content == null || content.equals("")) {
                return;
            }

            if (commentStatus == 0) {
                mPresenter.getComment(content);
            } else if (commentStatus == 1) {//回复评论
                mPresenter.getFromToComment(commentPos,content);
            } else {//回复评论子评论
                mPresenter.getFromToChildComment(commentPos,childCommentPos,content);
            }

//设置默认状态
            etComment.setText("");
            etComment.setHint("写评论");
            SoftInputUtils.hideSoftinput(getActivity());
        }
    }


    @Override
    public void showResult(CommunityDetailModel communityDetailModel) {
        mCommunityDetailModel = communityDetailModel;
        if (communityDetailModel.getData().getIsFabulous().equals("1")) {
            refreshPraise(true);
        } else {
            refreshPraise(false);
        }
        LogUtil.e(TAG+"---thumbsUp",String.valueOf(communityDetailModel.getData().getThumbsUp()));
            mMessageAdapter = new CircleDetailMessageAdapter(getContext(), communityDetailModel);
            rvCircleDetailList.setAdapter(mMessageAdapter);
        mMessageAdapter.setDeleteCommunityClickListener(new CircleDetailMessageAdapter.DeleteCommunityClickListener() {
            @Override
            public void deleteCommunity() {
                mPresenter.deleteCommunity();
            }
        });
        mMessageAdapter.setCommentClickListener(new CircleDetailMessageAdapter.setCircleCommentClickListener() {
            @Override
            public void onCommentClickListener(final int position) {
                if (!GlobalDataYepao.isLogin()) {
                    return;
                }
                if (String.valueOf(mCommunityDetailModel.getData().getComments().get(position).getCustomerId()).equals(GlobalDataYepao.getUser(getContext()).getId())) {
                    DialogUtils.showMessageTwoButtonDialog(getContext(), "提示", "是否删除评论", new DialogCallback() {
                        @Override
                        public void onItemClick(int position) {

                        }

                        @Override
                        public void onLeftClick() {

                        }

                        @Override
                        public void onRightClick() {
                            mPresenter.deleteComment(String.valueOf(mCommunityDetailModel.getData().getComments().get(position).getId()));
                        }
                    });
                } else {
                    commentPos = position;
                    commentStatus = 1;
                    etComment.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    etComment.setHint("回复: "+mCommunityDetailModel.getData().getComments().get(position).getName());
                }
            }

            @Override
            public void onCommentDeleteIconClickListener(final int position) {
                DialogUtils.showMessageTwoButtonDialog(getContext(), "提示", "是否删除评论", new DialogCallback() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onLeftClick() {

                    }

                    @Override
                    public void onRightClick() {
                        mPresenter.deleteComment(String.valueOf(mCommunityDetailModel.getData().getComments().get(position).getId()));
                    }
                });
            }
        });

        mMessageAdapter.setChildCommentClickListener(new CircleDetailMessageAdapter.ChildCommentClickListener() {
            @Override
            public void onChildCommentListener(final int pos, final int childPos) {
                if (!GlobalDataYepao.isLogin()) {
                    return;
                }
                if (String.valueOf(mCommunityDetailModel.getData().getComments().get(pos).getCommunityCommentsOuts().get(childPos).getCustomerId()).equals(GlobalDataYepao.getUser(getContext()).getId())) {
                    DialogUtils.showMessageTwoButtonDialog(getContext(), "提示", "是否删除评论", new DialogCallback() {
                        @Override
                        public void onItemClick(int position) {

                        }

                        @Override
                        public void onLeftClick() {

                        }

                        @Override
                        public void onRightClick() {
                            mPresenter.deleteComment(String.valueOf(mCommunityDetailModel.getData().getComments().get(pos).getCommunityCommentsOuts().get(childPos).getId()));
                        }
                    });
                } else {
                    commentPos = pos;
                    childCommentPos = childPos;
                    commentStatus = 2;
                    etComment.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    etComment.setHint("回复: " + mCommunityDetailModel.getData().getComments().get(pos).getCommunityCommentsOuts()
                            .get(childPos).getName());
                }
            }

            @Override
            public void onChildDeleteIconListener(final int pos, final int childPos) {
                DialogUtils.showMessageTwoButtonDialog(getContext(), "提示", "是否删除评论", new DialogCallback() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onLeftClick() {

                    }

                    @Override
                    public void onRightClick() {
                        mPresenter.deleteComment(String.valueOf(mCommunityDetailModel.getData().getComments().get(pos).getCommunityCommentsOuts().get(childPos).getId()));
                    }
                });

            }
        });
    }





    @Override
    public void startLoading() {
        srlCircleDetail.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        srlCircleDetail.setRefreshing(false);
    }

    @Override
    public void refreshPraise(boolean status) {
        fingerStatus = status;
        if (status) {
            ivCircleFinger.setImageDrawable(getContext().getResources().getDrawable(R.drawable.circle_finger_s));
        } else {
            ivCircleFinger.setImageDrawable(getContext().getResources().getDrawable(R.drawable.circle_finger_n));
        }
    }


    /**
     * 刷新整个页面
     */
    @Override
    public void refreshComment() {

    }

    @Override
    public void CircleDetailFinish() {
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_circle_finger, R.id.et_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_circle_finger:
                if (!GlobalDataYepao.isLogin()) {
                    LoginActivity.start(getContext());
                } else {
                    if (!fingerStatus) {
                        mPresenter.getPraise();
                    } else {
                        mPresenter.deletePraise();
                    }
                }
                break;
            case R.id.et_comment:
                break;
        }
    }
}
