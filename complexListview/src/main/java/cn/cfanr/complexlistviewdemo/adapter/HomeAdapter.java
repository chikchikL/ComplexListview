package cn.cfanr.complexlistviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.model.HomeItem;
import cn.cfanr.complexlistviewdemo.view.viewholder.AdHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.MealShowHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.MenuHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.SignMallHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.SpecialHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.TagHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.TalentShowHolder;


/**
 * Created by cfanr on 2015/12/4.
 */
public class HomeAdapter extends BaseAdapter{
    private Context context;
    private List<HomeItem> homeItemList;
    private final static int SIGN_MALL=0;
    private final static int TAG=1;
    private final static int SPECIAL=2;
    private final static int AD=3;
    private final static int MENU=4;
    private final static int MEAL_SHOW=5;
    private final static int TALENT_SHOW=6;

    public HomeAdapter(Context context, List<HomeItem> homeItemList){
        this.context=context;
        this.homeItemList=homeItemList;
    }

    @Override
    public int getCount(){
        return homeItemList.size();          //头部4个，广告位3个
    }

    @Override
    public Object getItem(int position){
        return homeItemList== null ? null : homeItemList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    //这里的convertView假如不为空，那么是怎样从recycler中判断我当前需要的布局类型并且取出来的
    //滑出屏幕的item本身也是初次创建时的convertView,在每次显示新的
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        HomeItem homeItem=homeItemList.get(position);
        LayoutInflater inflater=LayoutInflater.from(context);

        //七种布局维护七种holder
        SignMallHolder signMallHolder;
        TagHolder tagHolder;
        SpecialHolder specialHolder;
        MenuHolder menuHolder;
        AdHolder adHolder;
        MealShowHolder mealShowHolder;
        TalentShowHolder talentShowHolder;

        int type=homeItem.getItemType().getValue();

        //判断当前需要的item布局类型，若convertView为空重新inflate，并且给convertView设置holder，否则直接取Tag即是viewHolder
        switch(type){
            case SIGN_MALL:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_sign_mall,null);
                    System.out.println("SIGN_MALL==创建==地址"+convertView);
                    signMallHolder=new SignMallHolder(convertView);
                    convertView.setTag(signMallHolder);
                }else{
                    signMallHolder=(SignMallHolder)convertView.getTag();
                    System.out.println("SIGN_MALL==复用==地址"+convertView);
                }
                break;
            case TAG:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_tag,null);
                    System.out.println("TAG==创建==地址"+convertView);
                    tagHolder=new TagHolder(convertView);
                    convertView.setTag(tagHolder);
                }else{
                    tagHolder=(TagHolder)convertView.getTag();
                    System.out.println("TAG==复用==地址"+convertView);
                }
                //复用需要刷新ui，将相应引用刷新ui的操作放在其viewHolder中进行非常明智
                tagHolder.refreshUI(homeItem);
                break;
            case SPECIAL:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_special,null);
                    System.out.println("SPECIAL==创建==地址"+convertView);
                    specialHolder=new SpecialHolder(convertView);
                    convertView.setTag(specialHolder);
                }else{
                    specialHolder=(SpecialHolder)convertView.getTag();
                    System.out.println("SPECIAL==复用==地址"+convertView);
                }
                specialHolder.refreshUI(homeItem);
                break;
            case AD:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_ad,null);
                    System.out.println("AD==创建==地址"+convertView);
                    adHolder=new AdHolder(context,convertView);
                    convertView.setTag(adHolder);
                }else{
                    adHolder=(AdHolder)convertView.getTag();
                    System.out.println("AD==复用==地址"+convertView);
                }
                adHolder.setViewPager(homeItem);
                break;
            case MENU:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_menu,null);
                    System.out.println("MENU==创建==地址"+convertView);
                    menuHolder=new MenuHolder(convertView);
                    convertView.setTag(menuHolder);
                }else{
                    menuHolder=(MenuHolder)convertView.getTag();
                    System.out.println("MENU==复用==地址"+convertView);
                }
                menuHolder.refreshUI(homeItem);
                break;
            case MEAL_SHOW:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_meal_show,null);
                    System.out.println("MEAL_SHOW==创建==地址"+convertView);
                    mealShowHolder=new MealShowHolder(context,convertView);
                    convertView.setTag(mealShowHolder);
                }else{
                    mealShowHolder=(MealShowHolder)convertView.getTag();
                    System.out.println("MEAL_SHOW==复用==地址"+convertView);

                }
                mealShowHolder.setViewPager(homeItem);
                break;
            case TALENT_SHOW:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_talent,null);
                    System.out.println("TALENT_SHOW==创建==地址"+convertView);
                    talentShowHolder=new TalentShowHolder(context,convertView);
                    convertView.setTag(talentShowHolder);
                }else{
                    talentShowHolder=(TalentShowHolder)convertView.getTag();
                    System.out.println("TALENT_SHOW==复用==地址"+convertView);
                }
                talentShowHolder.initView(homeItem);
                break;
        }
        return convertView;
    }


   //在getView发生前，系统通过getItemViewType判断当前位置需要的布局类型
    //返回值范围是0~typeCount-1
    @Override
    public int getItemViewType(int position){
        System.out.println("调用getItemViewType");
        if (homeItemList!= null && position < homeItemList.size()) {
            return homeItemList.get(position).getItemType().getValue();
        }
        return super.getItemViewType(position);
    }
    //系统在判断是否有convertView时，会自动去调用getItemViewType (int position) ，查看当前position的homeItem的类型
    //但是如何知道convertView类型呢？
    //当ListView要显示某一项时，getItemViewType方法被调用，根据返回值在mRecycler搜索得到缓存的视图。怎么找的？

    @Override
    public int getViewTypeCount(){
        return 7;
    }
}
