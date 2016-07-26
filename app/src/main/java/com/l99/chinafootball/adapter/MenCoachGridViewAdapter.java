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
public class MenCoachGridViewAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter {

    private List<NationalCoachBean.CategoryBean.CoachesBean> coachesBeans=new ArrayList<>();
    private Context context;
    private NationalCoachBean.CategoryBean GuoJiaDuiJiaoLian;
    private NationalCoachBean.CategoryBean GuoAoDuiJiaoLian;
    private NationalCoachBean.CategoryBean GuoQingDuiJiaoLian;
    private NationalCoachBean.CategoryBean WuRenZhiJiaoLian;
    private NationalCoachBean.CategoryBean ShaTanJiaoLian;
    private int GuoJiaDuiJiaoLiannsize;
    private int GuoAoDuiJiaoLiansize;
    private int GuoQingDuiJiaoLiansize;
    private int WuRenZhiJiaoLiansize;
    private int ShaTanJiaoLiansize;

    public static final int SHOW_GUOJIADUI = 0;
    public static final int SHOW_GUOAODUI = 1;
    public static final int SHOW_GUOQINGDUI =2;
    public static final int SHOW_WURENZHI = 3;
    public static final int SHOW_SHATANZUQIU = 4;

    public MenCoachGridViewAdapter(Context context, ArrayList<NationalCoachBean.CategoryBean> categoryBeans) {
        this.context = context;
        initData(categoryBeans);
    }

    private void initData(ArrayList<NationalCoachBean.CategoryBean> categoryBeans) {
        GuoJiaDuiJiaoLian = categoryBeans.get(0);
        Log.e("ly","国家队size："+GuoJiaDuiJiaoLian.getCoaches().size());
        GuoAoDuiJiaoLian = categoryBeans.get(1);
        Log.e("ly","国奥队size："+GuoAoDuiJiaoLian.getCoaches().size());
        GuoQingDuiJiaoLian = categoryBeans.get(2);
        Log.e("ly","国青队size："+GuoQingDuiJiaoLian.getCoaches().size());
        WuRenZhiJiaoLian = categoryBeans.get(3);
        Log.e("ly","五人制size："+WuRenZhiJiaoLian.getCoaches().size());
        ShaTanJiaoLian = categoryBeans.get(4);
        Log.e("ly","沙滩足球size："+ShaTanJiaoLian.getCoaches().size());

        if (GuoJiaDuiJiaoLian != null) {
            coachesBeans.addAll(GuoJiaDuiJiaoLian.getCoaches());
            GuoJiaDuiJiaoLiannsize = GuoJiaDuiJiaoLian.getCoaches().size();
        }
        if (GuoAoDuiJiaoLian != null) {
            coachesBeans.addAll(GuoAoDuiJiaoLian.getCoaches());
            GuoAoDuiJiaoLiansize = GuoAoDuiJiaoLian.getCoaches().size();

        }
        if (GuoQingDuiJiaoLian != null) {
            coachesBeans.addAll(GuoQingDuiJiaoLian.getCoaches());
            GuoQingDuiJiaoLiansize = GuoQingDuiJiaoLian.getCoaches().size();
        }
        if (WuRenZhiJiaoLian != null) {
            coachesBeans.addAll(WuRenZhiJiaoLian.getCoaches());
            WuRenZhiJiaoLiansize = WuRenZhiJiaoLian.getCoaches().size();

        }
        if (ShaTanJiaoLian != null) {
            coachesBeans.addAll(ShaTanJiaoLian.getCoaches());
            ShaTanJiaoLiansize = ShaTanJiaoLian.getCoaches().size();
        }


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
        int itemViewType = getItemViewType(position);
        NationalCoachBean.CategoryBean.CoachesBean coachesBean = coachesBeans.get(position);
        String coachesBean_name = coachesBean.getName(); //高洪波
        String coachesBean_position = coachesBean.getPosition(); //主教练
        viewHolder.name.setText("[ " + coachesBean_position + " ] " + " " + coachesBean_name);
        if("".equals(coachesBean.getAvatar()) || "null".equals(coachesBean.getAvatar()) || coachesBean.getAvatar() == null) {
            viewHolder.image.setImageResource(R.drawable.placehold_player);
        }else {

            ImageUtils.playerAvatarCompress(coachesBean.getAvatar(), 0, viewHolder.image);
        }
        Log.e("TAGggg", itemViewType+"---------"+coachesBean_name+"-------------"+position+tittle[itemViewType]);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return getItemViewType(position);
    }
private  String[] tittle={"国家队","国奥队","国青队","五人制","沙滩足球"};
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(parent.getContext(), R.layout.coach_header, null);
             TextView title = (TextView) convertView.findViewById(R.id.tv_coach_header);
        int itemViewType = getItemViewType(position);

        Log.e("TAGG1", position+"<"+ GuoJiaDuiJiaoLiannsize );
        Log.e("TAGG2", GuoJiaDuiJiaoLiannsize +"=<"+position+"<"+ (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize ) );
        Log.e("TAGG3", (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize) +"=<"+position+"<"+ (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize + GuoQingDuiJiaoLiansize) );
        Log.e("TAGG4", (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize + GuoQingDuiJiaoLiansize) +"=<"+position+"<"+ (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize + GuoQingDuiJiaoLiansize + WuRenZhiJiaoLiansize) );

        switch (itemViewType) {
            case SHOW_GUOJIADUI:
                title.setText(tittle[itemViewType]);
                break;
            case SHOW_GUOAODUI:
                title.setText(tittle[itemViewType]);
                break;
            case SHOW_GUOQINGDUI:
                title.setText(tittle[itemViewType]);
                break;
            case SHOW_WURENZHI:
                title.setText(tittle[itemViewType]);
                break;
            case SHOW_SHATANZUQIU:
                title.setText(tittle[itemViewType]);
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

        
       /* if (position < GuoJiaDuiJiaoLiannsize) {

            return SHOW_GUOJIADUI;

        } else if (position >= GuoJiaDuiJiaoLiannsize && position < (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize)) {
                return SHOW_GUOAODUI;

        } else if ( (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize) >= position && position < (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize + GuoQingDuiJiaoLiansize) ) {

       return SHOW_GUOQINGDUI;

        } else if ( (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize + GuoQingDuiJiaoLiansize) >= position && position < (GuoJiaDuiJiaoLiannsize + GuoAoDuiJiaoLiansize + GuoQingDuiJiaoLiansize + WuRenZhiJiaoLiansize) ) {

               return SHOW_WURENZHI;
        } else {
           
           
            return SHOW_SHATANZUQIU;
        }*/
        if(position<4) {
            Log.e("CZG", 0+"-------"+position);
return 0;

        }
        if((4>=position)&&(position<9) ) {
            Log.e("CZG", 1+"-------"+position);
return 1;
        }
        if((9>=position)&&(position<15)) {
            Log.e("CZG", 2+"-------"+position);
return 2;

        }
        if((15>=position)&&(position<18)) {
            Log.e("CZG", 3 + "-------" + position);
            return 3;
        }
            Log.e("CZG", 4+"-------"+position);
            return 4;


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
