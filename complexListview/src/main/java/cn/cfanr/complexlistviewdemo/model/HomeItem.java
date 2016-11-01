package cn.cfanr.complexlistviewdemo.model;

import java.util.List;

/**
 * Created by cfanr on 2015/12/4.
 */
public class HomeItem{
    private ItemType itemType;//item种类，一共六种
    private String tagName;//标签布局的标签名
    private Special special;//专辑布局需要的数据类
    private Ad ad;//广告布局需要的数据类，维护一个imgId的数组用来展示广告图片
    private MenuPo[] menuPos;//菜谱布局需要的数据集
    private List<MealShow> mealShowList;//美食布局
    private List<TalentShow> talentShowList;//美食达人布局需要的数据集，或者准确点说是数据模型

    public ItemType getItemType(){
        return itemType;
    }

    public void setItemType(ItemType itemType){
        this.itemType=itemType;
    }

    public Special getSpecial(){
        return special;
    }

    public void setSpecial(Special special){
        this.special=special;
    }

    public Ad getAd(){
        return ad;
    }

    public void setAd(Ad ad){
        this.ad=ad;
    }

    public List<MealShow> getMealShowList(){
        return mealShowList;
    }

    public void setMealShowList(List<MealShow> mealShowList){
        this.mealShowList=mealShowList;
    }

    public List<TalentShow> getTalentShowList(){
        return talentShowList;
    }

    public void setTalentShowList(List<TalentShow> talentShowList){
        this.talentShowList=talentShowList;
    }

    public MenuPo[] getMenuPos(){
        return menuPos;
    }

    public void setMenuPos(MenuPo[] menuPos){
        this.menuPos=menuPos;
    }

    public String getTagName(){
        return tagName;
    }

    public void setTagName(String tagName){
        this.tagName=tagName;
    }
}
