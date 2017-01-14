package com.baurine.multitypeadaptersample.model;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.item.TextItem;

import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public class TextModel extends BaseModel {
    @Override
    public MultiTypeAdapter.IItem createItem(MultiTypeAdapter adapter) {
        return new TextItem(this, adapter);
    }

    ///////////////////////////////////////
    // copy from: http://www.guancha.cn/life/2016_11_28_382164.shtml
    private static final String[] TOXIC_SOUP = new String[]{
            "你必须非常努力，才能相信自己真的是无能无力",
            "能用钱解决的问题，我一件都解决不了",
            "不是因为看到希望了才去坚持，而是坚持了才知道没有希望",
            "那些曾经把我打倒的人，谢谢你们，躺着真舒服",
            "人就要趁年轻的时候多出去走走，看看不同的风景，不同的文化，等转遍了世界你就会发现原来....躺在被窝里是真他妈舒服！",
            "人生就是一个起落落落落落落...的过程",
            "生活不止眼前的苟且，还有明天和后天苟且",
            "一觉醒来，是不是感觉离梦想又远了呢",
            "一路上有你，苦一点也愿意，苦很多就算了",
            "旅行不必在乎目的地，在乎的是沿途的风景，因为就你那点钱也就只够买个往返的火车硬座票",
    };

    ////////////////////////////////////////
    public final String content;
    public boolean liked;

    public TextModel() {
        super();
        content = TOXIC_SOUP[(new Random()).nextInt(1000) % TOXIC_SOUP.length];
        liked = (new Random()).nextBoolean();
    }
}
