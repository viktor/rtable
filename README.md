# rtable

Component implements a recyclerview, shows table headers and also has pagination.

### Screenshots

<img src="https://raw.githubusercontent.com/viktor/rtable/master/app/screenshots/Screenshot_2019-09-28-02-03-53.png" width="180px" height="250px" />

<img src="https://raw.githubusercontent.com/viktor/rtable/master/app/screenshots/Screenshot_2019-09-28-02-04-15.png" width="180px" height="250px" />

### How to use

Add in repository section (project gradle file)

        maven { url "https://jitpack.io" }

Component only works with androidx, add the following libs:

    implementation "androidx.appcompat:appcompat:1.0.0-alpha3"
    implementation "androidx.recyclerview:recyclerview:1.0.0-alpha3"
    implementation 'com.github.viktor:rtable:1.0.0'


Add the xml element in your activity layout:

       <com.viktor.rtable.RecyclerViewTable
                android:id="@+id/grid_recycler"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                />

Create java bean to show in table:

      public class PersonDetail {
            @GridColumn(width = 50, position = 0)
            private String id;
            @GridColumn(width = 200, position = 1)
            private String name;
            
      }

Configure the component in your activity class:

        recyclerTable = findViewById(R.id.grid_recycler);
        recyclerTable.configure(
                new ColumnHeader[]{
                        new ColumnHeader(50, "id"),
                        new ColumnHeader(200, "name")
                }
                , (List) setupCollection(), PersonDetail.class, new OnRowClicked() {
                    @Override
                    public void onClick(View view, int rowIndex) {
                        Log.d("INFO", "rowIndex: "+rowIndex);
                    }
                });

OnRowClicked is optional. Also u need to convert the collection of java beans to (List) 
just cast the list. Specify the position of every bean field. 

See the example project here:

https://github.com/viktor/rtable-example

### Styling 

If u need to style your table override the following style rules:

    <style name="RTableHeaderTheme" parent="Theme.AppCompat.Light">
        <item name="android:background">@color/rtable_primaryColor</item>
        <item name="android:textColor">@color/rtable_header_textColor</item>
        <item name="android:textSize">@dimen/rtable_header_textSize</item>
        <item name="android:textStyle">bold</item>
    </style>

    <style name="RTableContentTheme" parent="Theme.AppCompat.Light">
        <item name="android:background"></item>
        <item name="android:textColor">@color/rtable_content_textColor</item>
        <item name="android:textSize">@dimen/rtable_row_content_textSize</item>
    </style>

    <style name="RTableFooterTheme" parent="Theme.AppCompat.Light">
        <item name="android:background">@color/rtable_primaryColor</item>
    </style>

These are the colors:

    <color name="rtable_primaryColor">#FF0000</color>
    <color name="rtable_header_textColor">#ffffff</color>
    <color name="rtable_footer_textColor">#ffffff</color>
    <color name="rtable_content_textColor">#000000</color>


And these are the dimensions:

    <dimen name="rtable_header_height">30dp</dimen>
    <dimen name="rtable_row_height">50dp</dimen>
    <dimen name="rtable_header_padding">5dp</dimen>
    <dimen name="rtable_row_padding">5dp</dimen>
    <dimen name="rtable_footer_height">30dp</dimen>
    <dimen name="rtable_header_textSize"></dimen>
    <dimen name="rtable_row_content_textSize"></dimen>

    <integer name="rtable_rowsPerPage">10</integer>


# LICENSE

MIT License

Copyright (c) 2019 VictorQ

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



