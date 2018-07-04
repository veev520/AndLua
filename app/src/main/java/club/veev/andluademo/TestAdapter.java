package club.veev.andluademo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veev on 2018/7/4.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    TestAdapter
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TestBean> mList;

    public void add(TestBean bean) {
        mList.add(bean);
        notifyItemInserted(mList.size() - 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private class LuaViewHolder extends RecyclerView.ViewHolder {

        public LuaViewHolder(View itemView) {
            super(itemView);
        }
    }
}
