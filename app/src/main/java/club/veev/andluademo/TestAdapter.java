package club.veev.andluademo;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import club.veev.andlua.view.ILuaView;
import club.veev.andlua.view.LuaView;
import club.veev.andluademo.entity.LuaBean;
import club.veev.andluademo.entity.Test1;
import club.veev.andluademo.entity.TestBean;

/**
 * Created by Veev on 2018/7/4.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    TestAdapter
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LUA = 1;
    private static final int TYPE_TEST_1 = 2;

    private List<TestBean> mList;

    public void setData(List<TestBean> data) {
        mList = data;
        notifyDataSetChanged();
    }

    public void add(TestBean bean) {
        mList.add(bean);
        notifyItemInserted(mList.size());
//        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        if (viewType == TYPE_TEST_1) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_1, parent, false);
            return new Test1ViewHolder(itemView);
        } else if (viewType == TYPE_LUA) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lua, parent, false);
            return new LuaViewHolder(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_TEST_1) {
            ((Test1ViewHolder) holder).setData(mList.get(position));
        }

        if (getItemViewType(position) == TYPE_LUA) {
            ((LuaViewHolder) holder).setData(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String type = mList.get(position).getType();

        if (TextUtils.isEmpty(type)) {
            return 0;
        }

        switch (type) {
            case TestBean.TYPE_LUA:
                return TYPE_LUA;
            case TestBean.TYPE_TEST_1:
                return TYPE_TEST_1;
            default:
                return 0;
        }
    }

    private class Test1ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextTitle, mTextSub, mTextContent;

        public Test1ViewHolder(View itemView) {
            super(itemView);

            mTextContent = itemView.findViewById(R.id.test_1_text_content);
            mTextTitle = itemView.findViewById(R.id.test_1_text_title);
            mTextSub = itemView.findViewById(R.id.test_1_text_sub_title);
        }

        private void setData(TestBean bean) {
            try {
                Test1 test1 = (Test1) bean.getData();

                mTextTitle.setText(test1.getTitle());
                mTextSub.setText(test1.getSubTitle());
                mTextContent.setText(test1.getContent());
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    private class LuaViewHolder extends RecyclerView.ViewHolder {

        public LuaViewHolder(View itemView) {
            super(itemView);
        }

        private void setData(TestBean bean) {
            try {
                LuaBean luaBean = (LuaBean) bean.getData();

                ILuaView luaView = LuaView.load(itemView.getContext(), luaBean.getScript());

                ((FrameLayout) itemView).removeAllViews();
                ((FrameLayout) itemView).addView(luaView.getView());
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }
}
