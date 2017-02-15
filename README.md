# MultiTypeAdapter

[![](https://jitpack.io/v/baurine/multi-type-adapter.svg)](https://jitpack.io/#baurine/multi-type-adapter)

Implement a super simple, powerful, and easy to use MultiTypeAdapter for RecyclerView by android databinding, it is only about 100 lines code and just one java file, use this adapter, you can never to implement kinds of ViewHolder anymore.

I write a very detail tutorial to explain how to implement this MultiTypeAdapter, and how to use it to implement a complete sample that support refresh, load more, retry, header item, emtpy item, error item, footer item, any kinds of data item step by step.

## Tutorial

1. [Escape the nightmare of adapter and viewholder by android databinding (1)](./note/multi-type-adapter-tutorial-1.md)
1. [Escape the nightmare of adapter and viewholder by android databinding (2)](./note/multi-type-adapter-tutorial-2.md)

## Sample

1. Demo HeaderItem, EmptyItem, ErrorItem, refresh, load more, load error and retry:

   ![](./note/sample_art/multi_type_adapter_1.gif)

1. Demo load more but has no more data:

   ![](./note/sample_art/multi_type_adapter_2.gif)

1. Polish the UI, add event handler for item:

   ![](./note/sample_art/multi_type_adapter_3.gif)

The sample includes following features, I think it should fullfill 90% needs:

1. Refresh
1. Load more
1. Support header item
1. Support empty item and enable refresh again
1. Support error item and enable refresh again
1. Support footer item, includes 3 states: loading, load error and enable retry, no more data
1. Support any kind of data item, here just demo ImageItem and TextItem

[Download APK](https://github.com/baurine/multi-type-adapter/releases/tag/v1.0.5_release) | [GitHub](https://github.com/baurine/multi-type-adapter)

There are two folders in this project, MultiTypeAdapterSample and MultiTypeAdapterTutorial, their codes are nearly same, the latter is created for the above tutorial articles, I tag the every key step so you can easily compare the code with article.

## Getting Started

Add JitPack as library source in your project `build.gradle`:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

Then, add dependency in your app module `build.gradle`:

    dependencies {
        compile 'com.github.baurine:multi-type-adapter:${latest-version}'
    }

`latest-version`: see top jitpack badge.

And enable databinding in your app module `build.gradle`:

    android {
        ...
        dataBinding {
            enabled = true
        }
    }

## Usage

Please read the above super detail tutorial to learn how exactly to use it. Following are some simple instruction extract from tutorial.

After you use this MultiTypeAdapter, you just need to implement kinds of items, and add to or remove from adapter at the right time. The item represents a whole body that display in recyclerview, includes layout, data and event, so it is a wrapper, wrap the layout, data model and event handler together, but the data model and event handler are not necessary, just layout is required, it depends on what's the kind of item. All items must inherit from `IItem` interface:

    public interface IItem {
        // get the xml layout this type item used in
        int getLayout();

        // get the variable name in the xml
        int getVariableId();
    }

`getLayout()` method should return the xml layout, likes `R.layout.item_header`, and `getVariableId()` return the variable name this item used in xml, likes `BR.item`. Becasuse we usually use a same variable name in all items, and the event handler, usually it can be a `View.OnClickListener`, so we define a `BaseItem`:

    public abstract class BaseItem implements MultiTypeAdapter.IItem {
        @Override
        public int getVariableId() {
            return BR.item;
        }

        ////////////////////////////////////////////
        // handle event
        private View.OnClickListener onClickListener;

        public View.OnClickListener getOnClickListener() {
            return onClickListener;
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }

Here is an example, a most simple item - HeaderItem, just has layout, no data and no event:

    public class HeaderItem extends BaseItem {
        @Override
        public int getLayout() {
            return R.layout.item_header;
        }
    }

And HeaderItem used in `item_header.xml`:

    <layout
        xmlns:android="http://schemas.android.com/apk/res/android">
        <data>
            <variable
                name="item"
                type="com.baurine.multitypeadaptersample.item.HeaderItem"/>
        </data>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            android:background="@drawable/x_border"
            android:gravity="center_horizontal"
            android:orientation="vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:padding="24dp"
                android:text="This is a HeaderItem"
                android:textSize="20sp"
                android:textStyle="bold"/>

        </LinearLayout>
    </layout>

A most complex item - ImageItem, display a random image, and you can like it, hide it, and comment it, the image data from ImageModel:

ImageModel:

    public class ImageModel extends BaseModel {
        @Override
        public MultiTypeAdapter.IItem createItem(MultiTypeAdapter adapter) {
            return new ImageItem(this, adapter);
        }

        ////////////////////////////////////////
        public final String url;
        public boolean liked;

        public ImageModel() {
            super();
            url = "https://unsplash.it/200/200?random&" + new Random().nextInt(40);
            liked = (new Random()).nextBoolean();
        }
    }

ImageItem:

    public class ImageItem extends BaseItem {
        @Override
        public int getLayout() {
            return R.layout.item_image;
        }

        ///////////////////////////////////////
        public ImageItem(ImageModel imageModel, final MultiTypeAdapter adapter) {
            this.imageModel = imageModel;

            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.tv_like:
                            toggleLiked();
                            adapter.notifyItemChanged(adapter.findPos(ImageItem.this));
                            break;
                        case R.id.tv_hide:
                            adapter.notifyItemRemoved(adapter.removeItem(ImageItem.this));
                            break;
                        case R.id.tv_comment:
                            CommonUtil.showToast(view.getContext(),
                                    "TODO: comment image, id: " +
                                            String.valueOf(getId()));
                            break;
                    }
                }
            });
        }

        ///////////////////////////////////////
        // data model part
        private final ImageModel imageModel;

        public String getUrl() {
            return imageModel.url;
        }

        public boolean isLiked() {
            return imageModel.liked;
        }

        public int getId() {
            return imageModel.id;
        }

        public void toggleLiked() {
            imageModel.liked = !imageModel.liked;
        }
    }

ImageItem used in `item_image.xml`:

    <layout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">
        <data>
            <variable
                name="item"
                type="com.baurine.multitypeadaptersample.item.ImageItem"/>
        </data>

        <android.support.v7.widget.CardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            app:cardCornerRadius="2dp">

            <FrameLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="200dp"
                    android:scaleType="centerCrop"
                    app:error="@{@drawable/ic_launcher}"
                    app:imageUrl="@{item.url}"
                    app:placeholder="@{@drawable/ic_launcher}"/>

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_gravity="bottom"
                    android:background="#c0ffffff">

                    <include
                        layout="@layout/include_actions"
                        app:liked="@{item.liked}"
                        app:onClickListener="@{item.onClickListener}"/>

                </LinearLayout>
            </FrameLayout>
        </android.support.v7.widget.CardView>
    </layout>

    <!-- include_actions.xml-->
    <layout
        xmlns:android="http://schemas.android.com/apk/res/android">
        <data>
            <variable
                name="liked"
                type="Boolean"/>
            <variable
                name="onClickListener"
                type="android.view.View.OnClickListener"/>
        </data>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <TextView
                android:id="@+id/tv_like"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:gravity="center"
                android:onClick="@{onClickListener::onClick}"
                android:paddingVertical="@{8}"
                android:text="@{liked ? `取消赞` : `点赞`}"
                android:textColor="@{liked ? @android:color/holo_red_light : @android:color/darker_gray}"/>

            <TextView
                android:id="@+id/tv_hide"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:gravity="center"
                android:onClick="@{onClickListener::onClick}"
                android:paddingVertical="@{8}"
                android:text="隐藏"/>

            <TextView
                android:id="@+id/tv_comment"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:gravity="center"
                android:onClick="@{onClickListener::onClick}"
                android:paddingVertical="@{8}"
                android:text="评论"/>
        </LinearLayout>
    </layout>

Then call `adapter.addItem()`, `adapter.removeItem()`, `adapter.notifyItemChanged(adapter.findPos(item))` to operate the adapter at the right time. Following is an example to handle the network response result:

    private void retrieveItems(boolean loadMore) {
        // result = 0, network error
        // result = 1, empty
        // result = 2, last page data
        // result = 3 and other, normal result
        int resultType = (new Random()).nextInt(100) % 5;
        if (resultType == 0) {
            adapter.addItem(loadMore ? footerItem.setState(FooterItem.ERROR) : errorItem);
        } else if (resultType == 1) {
            adapter.addItem(loadMore ? footerItem.setState(FooterItem.NO_MORE) : emptyItem);
        } else if (resultType == 2) {
            addDataItems(PER_PAGE_COUNT / 2);
            // here depends whether you want to display no more data state
            // if you don't want to display this state when has no more data
            // then just don't add it back
            adapter.addItem(footerItem.setState(FooterItem.NO_MORE));
        } else {
            addDataItems(PER_PAGE_COUNT);
            // pre-display loading state to improve user experience
            adapter.addItem(footerItem.setState(FooterItem.LOADING));
        }
    }

License
-------

    Copyright 2017 baurine.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
