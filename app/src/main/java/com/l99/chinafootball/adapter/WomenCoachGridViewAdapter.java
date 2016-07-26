package com.l99.chinafootball.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l99.chinafootball.R;
import com.l99.chinafootball.bean.NationalCoachBean;
import com.l99.chinafootball.ui.RatioimageView;
import com.l99.chinafootball.utils.ImageUtils;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lifeix-101 on 2016/7/1.
 */
public class WomenCoachGridViewAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter {

    private List<NationalCoachBean.CategoryBean.CoachesBean> coachesBeans=new ArrayList<>();
    private Context context;
    private NationalCoachBean.CategoryBean GuoJiaDuiJiaoLian;
    private NationalCoachBean.CategoryBean GuoQingDuiJiaoLian;
    private NationalCoachBean.CategoryBean GuoShaoDuiJiaoLian;
    private NationalCoachBean.CategoryBean WuRenZhiJiaoLian;
    private NationalCoachBean.CategoryBean ShaTanJiaoLian;
    private int GuoJiaDuiJiaoLiannsize;
    private int GuoQingDuiJiaoLiansize;
    private int GuoShaoDuiJiaoLiansize;
    private int WuRenZhiJiaoLiansize;
    private int ShaTanJiaoLiansize;

    public static final int SHOW_GUOJIADUI = 1;
    public static final int SHOW_GUOQINGDUI = 2;
    public static final int SHOW_GUOSHAODUI = 3;
    public static final int SHOW_WURENZHI = 4;
    public static final int SHOW_SHATANZUQIU = 5;


    public WomenCoachGridViewAdapter(Context context, ArrayList<NationalCoachBean.CategoryBean> categoryBeans) {
        this.context = context;
        initData(categoryBeans);
    }

    private void initData(ArrayList<NationalCoachBean.CategoryBean> categoryBeans) {
        GuoJiaDuiJiaoLian = categoryBeans.get(0);
        Log.e("ly","GuoJiaDuiJiaoLian--"+GuoJiaDuiJiaoLian.getCoaches().size());
        GuoQingDuiJiaoLian = categoryBeans.get(1);
        Log.e("ly","GuoQingDuiJiaoLian--"+GuoQingDuiJiaoLian.getCoaches().size());
        GuoShaoDuiJiaoLian = categoryBeans.get(2);
        Log.e("ly","GuoShaoDuiJiaoLian--"+GuoShaoDuiJiaoLian.getCoaches().size());
        WuRenZhiJiaoLian = categoryBeans.get(3);
        Log.e("ly","WuRenZhiJiaoLian--"+WuRenZhiJiaoLian.getCoaches().size());
        ShaTanJiaoLian = categoryBeans.get(4);
        Log.e("ly","ShaTanJiaoLian--"+ShaTanJiaoLian.getCoaches().size());

        if (GuoJiaDuiJiaoLian != null) {
            coachesBeans.addAll(GuoJiaDuiJiaoLian.getCoaches());
            GuoJiaDuiJiaoLiannsize = GuoJiaDuiJiaoLian.getCoaches().size();
        }

        if (GuoQingDuiJiaoLian != null) {
            coachesBeans.addAll(GuoQingDuiJiaoLian.getCoaches());
            GuoQingDuiJiaoLiansize = GuoQingDuiJiaoLian.getCoaches().size();
        }
        if (GuoShaoDuiJiaoLian != null) {
            coachesBeans.addAll(GuoShaoDuiJiaoLian.getCoaches());
            GuoShaoDuiJiaoLiansize = GuoShaoDuiJiaoLian.getCoaches().size();
        }
        if (WuRenZhiJiaoLian != null) {
            coachesBeans.addAll(WuRenZhiJiaoLian.getCoaches());
            WuRenZhiJiaoLiansize = WuRenZhiJiaoLian.getCoaches().size();
        }

        if (ShaTanJiaoLian != null) {
            coachesBeans.addAll(ShaTanJiaoLian.getCoaches());
            ShaTanJiaoLiansize = ShaTanJiaoLian.getCoaches().size();
        }


        Log.e("ly","总个数--"+coachesBeans.size());

    }

    @Override
    public int getCount() {
        return coachesBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_gridview, null);
            viewHolder.image = (RatioimageView) convertView.findViewById(R.id.pic);
            viewHolder.image.setRatio(157f / 237f);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NationalCoachBean.CategoryBean.CoachesBean coachesBean = coachesBeans.get(position);
        String coachesBean_name = coachesBean.getName(); //高洪波
        String coachesBean_position = coachesBean.getPosition(); //主教练
        viewHolder.name.setText("[ " + coachesBean_position + " ] " + " " + coachesBean_name);
        if("".equals(coachesBean.getAvatar()) || "null".equals(coachesBean.getAvatar()) || coachesBean.getAvatar() == null) {
           viewHolder.image.setImageResource(R.drawable.placehold_player);
        }else {
            ImageUtils.playerAvatarCompress(coachesBean.getAvatar(), 0, viewHolder.image);
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        int type = getItemViewType(position);
        if (type == SHOW_GUOJIADUI) {
            return 0;
        } else if(type == SHOW_GUOQINGDUI) {
            return 1;
        }else if (type == SHOW_GUOSHAODUI) {
            return 2;
        } else if (type == SHOW_WURENZHI) {
            return 3;
        } else if (type == SHOW_SHATANZUQIU) {
            return 4;
        } else {
            return 5;
        }


    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
            holder = new HeaderViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.coach_header, null);
        TextView title = (TextView) convertView.findViewById(R.id.tv_coach_header);

        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case SHOW_GUOJIADUI:
                title.setText("国家队");
                break;
            case SHOW_GUOQINGDUI:
                title.setText("国青队");
                break;
            case SHOW_GUOSHAODUI:
                title.setText("国少队");
                break;
            case SHOW_WURENZHI:
                title.setText("五人制");
                break;
            case SHOW_SHATANZUQIU:
                title.setText("沙滩足球");
                break;
        }
        return convertView;
    }

    class ViewHolder {
        RatioimageView image;
        TextView name;

    }

    class HeaderViewHolder {
        TextView title;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 5) {
            return SHOW_GUOJIADUI;
        } else if (position >= 5 && position < 9 ) {
            return SHOW_GUOQINGDUI;
        } else if (9 >= position && position < 12 ) {
            Log.e("ly","position："+position);
            return SHOW_GUOSHAODUI;
        } else if(position >= 12 && position < 13) {
            return SHOW_WURENZHI;
        }else {
           return SHOW_SHATANZUQIU;
        }
    }

    /**
     * 返回 布局种类 的总个数
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 6;
    }

}
