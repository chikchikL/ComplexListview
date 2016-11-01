package cn.cfanr.complexlistviewdemo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.adapter.HomeAdapter;
import cn.cfanr.complexlistviewdemo.model.Ad;
import cn.cfanr.complexlistviewdemo.model.AdBanner;
import cn.cfanr.complexlistviewdemo.model.HomeItem;
import cn.cfanr.complexlistviewdemo.model.ItemType;
import cn.cfanr.complexlistviewdemo.model.MealShow;
import cn.cfanr.complexlistviewdemo.model.MenuPo;
import cn.cfanr.complexlistviewdemo.model.Special;
import cn.cfanr.complexlistviewdemo.model.TalentShow;
import cn.cfanr.complexlistviewdemo.view.viewholder.AdBannerHeader;


/**
 * author: xifan
 *
 */
public class HomeActivity extends Activity{
    private ListView mListView;
    private RelativeLayout rlSearchBar;
    private HomeAdapter mAdapter;//HomeAdapter是处理List不同item的适配器，相对于普通适配器多了一个getItemViewType()方法的处理
    private List<HomeItem> homeItemList=new ArrayList<>();
    //HomeItem是整个页面的数据模型，包含了所有item的不同数据模型，接收到网络数据时需要对数据加工再设置到HomeItem，
    //然后根据ItemType作为不同item类型的判断，再根据不同item获取对应的字段；各个item的数据处理是在单独一个ViewHolder上处理



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView(){
        //$找到相应id的控件的引用
        mListView=$(R.id.lv_home_list);
        rlSearchBar=$(R.id.rl_home_search_bar);

        HomeItem signMall=new HomeItem();
        signMall.setItemType(ItemType.SIGN_MALL);
        homeItemList.add(signMall);

        HomeItem tag1=new HomeItem();
        tag1.setItemType(ItemType.TAG);
        tag1.setTagName("专辑推荐");
        homeItemList.add(tag1);

        //在专辑推荐标签下有五个special类型的Item
        initSpecialData();

        HomeItem ad1=new HomeItem();
        ad1.setItemType(ItemType.AD);
        Ad ad11=new Ad();
        int[] imgIds1={R.mipmap.ad3,R.mipmap.ad4,R.mipmap.ad1,R.mipmap.ad2};
        ad11.setImgIds(imgIds1);
        ad1.setAd(ad11);//ad1仅仅指显示广告布局的HomeItem，ad11是新广告类，该类内部维护了一个数据存储img的id
        homeItemList.add(ad1);

        HomeItem tag2=new HomeItem();
        tag2.setItemType(ItemType.TAG);
        tag2.setTagName("精选菜谱");
        homeItemList.add(tag2);

        initMenuPoData();

        HomeItem ad2=new HomeItem();
        ad2.setItemType(ItemType.AD);
        Ad ad22=new Ad();
        int[] imgIds2={R.mipmap.ad4,R.mipmap.ad1,R.mipmap.ad3,R.mipmap.ad2};
        ad22.setImgIds(imgIds2);
        ad2.setAd(ad22);
        homeItemList.add(ad2);

        initMealShowData();

        HomeItem tag3=new HomeItem();
        tag3.setItemType(ItemType.TAG);
        tag3.setTagName("美食达人");
        homeItemList.add(tag3);

        initTalentShowData();

        HomeItem ad3=new HomeItem();
        ad3.setItemType(ItemType.AD);
        Ad ad33=new Ad();
        int[] imgIds3={R.mipmap.ad4,R.mipmap.ad1,R.mipmap.ad3,R.mipmap.ad2};
        ad33.setImgIds(imgIds3);
        ad3.setAd(ad33);
        homeItemList.add(ad3);

        addAdBannerHeader();
        addSearchView();

        mAdapter=new HomeAdapter(this,homeItemList);
        mListView.setAdapter(mAdapter);

        //这是控制隐藏在屏幕顶端的搜索布局的显示与隐藏
        mListView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i){

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    rlSearchBar.setVisibility(View.VISIBLE);
                } else {
                    rlSearchBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initSpecialData(){
        for(int i=0;i<5;i++){
            HomeItem homeItem=new HomeItem();
            Special special=new Special();
            special.setTitle("this is a title!");
            special.setContent("i'm a cute content");
            special.setCommentNum("34");
            special.setLikeNum("999");
            homeItem.setItemType(ItemType.SPECIAL);         //注意不要漏了
            homeItem.setSpecial(special);
            homeItemList.add(homeItem);
        }
    }

    private void initMenuPoData(){
        for(int i=0;i<5;i++){
            HomeItem homeItem=new HomeItem();
            MenuPo menuPo1=new MenuPo();
            menuPo1.setDishName("this is a dish name1");
            menuPo1.setDishIntro("i'm a dish intro1");
            menuPo1.setLikeNum("988");
            menuPo1.setCommentNum("45");

            MenuPo menuPo2=new MenuPo();
            menuPo2.setDishName("this is a dish name2");
            menuPo2.setDishIntro("i'm a dish intro2");
            menuPo2.setLikeNum("988");
            menuPo2.setCommentNum("45");

            MenuPo[] menuPos=new MenuPo[2];//={menuPo1,menuPo2};
            menuPos[0]=menuPo1;
            if(i!=4){
                menuPos[1]=menuPo2;
            }

            homeItem.setItemType(ItemType.MENU);
            homeItem.setMenuPos(menuPos);
            homeItemList.add(homeItem);
        }
    }

    private void initMealShowData(){
        HomeItem mealShowItem=new HomeItem();
        mealShowItem.setItemType(ItemType.MEAL_SHOW);
        int[] imgIds={R.mipmap.ad1,R.mipmap.ad2,R.mipmap.ad3,R.mipmap.ad4};
        String[] titles={"this is title 1111111111111111","this is title 2222222222222222222","this is title 333333333333333333","this is title 44444444444444444"};
        List<MealShow> mealShowList=new ArrayList<MealShow>();
        for(int i=0;i<4;i++){
            MealShow mealShow=new MealShow();
            mealShow.setCommentNum(11+i+"");
            mealShow.setLikeNum(22+i+"");
            mealShow.setUserName("纳兹咩"+i);
            mealShow.setImgId(imgIds[i]);
            mealShow.setTitle(titles[i]);
            mealShowList.add(mealShow);
        }
        mealShowItem.setMealShowList(mealShowList);
        homeItemList.add(mealShowItem);
    }

    private void initTalentShowData(){
        HomeItem talentShowItem=new HomeItem();
        talentShowItem.setItemType(ItemType.TALENT_SHOW);
        List<TalentShow> talentShowList=new ArrayList<>();
        for(int i=0;i<7;i++){
            TalentShow talentShow=new TalentShow();
            talentShow.setNickName("娘口三三"+i);
            talentShowList.add(talentShow);
        }
        talentShowItem.setTalentShowList(talentShowList);
        homeItemList.add(talentShowItem);
    }

    private void addAdBannerHeader(){
        final AdBanner adbanner=new AdBanner();
        int[] imgIds={R.mipmap.ad1,R.mipmap.ad2,R.mipmap.ad3,R.mipmap.ad4};
        String[] titles={"this is a cute ad","you are so beautify","handsome boy","lovey girl"};
        adbanner.setImgIds(imgIds);
        adbanner.setTitle(titles);
        AdBannerHeader adBannerHeader=new AdBannerHeader(this,adbanner);
        mListView.addHeaderView(adBannerHeader.view);
    }

    private void addSearchView(){
        View searchView = View.inflate(this, R.layout.view_home_search, null);
        mListView.addHeaderView(searchView);
    }

    private <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }

}
