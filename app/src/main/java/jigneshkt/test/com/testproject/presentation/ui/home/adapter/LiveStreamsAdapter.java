package jigneshkt.test.com.testproject.presentation.ui.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.domain.model.LiveStreamFail;
import jigneshkt.test.com.testproject.presentation.ui.base.BaseViewHolder;

public class LiveStreamsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<LiveStreamFail> mPostItems;

    public LiveStreamsAdapter(List<LiveStreamFail> postItems) {
        this.mPostItems = postItems;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LiveStreamsAdapter.ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_stream, parent, false), parent.getContext());

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mPostItems == null ? 0 : mPostItems.size();
    }

    public void add(LiveStreamFail response) {
        mPostItems.add(response);
    }

    public void addAll(List<LiveStreamFail> postItems) {
        for (LiveStreamFail response : postItems) {
            add(response);
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends BaseViewHolder {

        private  Context context;
        private View liveStreamItem;
        ViewHolder(View itemView, Context context) {
            super(itemView);
            this.liveStreamItem = itemView;
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            LiveStreamFail liveStreamFail = mPostItems.get(position);

            Glide.with(context).load(liveStreamFail.getThumbnailUrl()).into(((ImageView)liveStreamItem.findViewById(R.id.thumb_image)));
            ((TextView)liveStreamItem.findViewById(R.id.title)).setText(liveStreamFail.getTitle());
            ((TextView)liveStreamItem.findViewById(R.id.points)).setText(liveStreamFail.getPoints());

            if(liveStreamFail.getStreamer()!=null) {
                ((TextView) liveStreamItem.findViewById(R.id.streamer_game_info)).setText(liveStreamFail.getStreamer());
            }else if(liveStreamFail.getGame()!=null) {
                ((TextView) liveStreamItem.findViewById(R.id.streamer_game_info)).setText(liveStreamFail.getGame());
            }
        }
    }

}


