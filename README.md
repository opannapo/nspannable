# NSpannable
> Spannable String
> Easy to use
 

# How to use

### 1. Gradle 
>Gradle (Top-level).

```gradle
...
buildscript {
    repositories {
        jcenter() 
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.2'
    }
}
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io'}
    }
}
...
```


>Gradle (On your Module:App)

```gradle
...
dependencies {
    compile 'com.github.opannapo:nspannable:1.0'
} 
...
```

### 2. Java - SimpleSpanCreator
```Java 
Spannable s = new SimpleSpanCreator()
        .ofText(TEXT)
        .find("@opannapo", "@novita")
        .spandColor(color, color2)
        .setBold(true)
        .setItalic(false)
        .setSpanClickListener(tText, new SpanClickListener() {
            @Override
            public void clicked(String text) {
                Log.d("CLICK", "link clicked " + text);
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        })
        .create(); 
textView.setText(s);
``` 

### 3. Java - ObjSpanCreator
```Java 
SpanObj obj1 = new SpanObj().find("Metallica").color(Color.RED).bold(true).italic(false);
SpanObj obj2 = new SpanObj().find("Jakarta").color(Color.BLACK).bold(true).italic(true);
SpanObj obj3 = new SpanObj().find("Augusts 25").color(Color.BLUE).bold(false).italic(true);
Spannable s2 = new ObjSpanCreator()
        .ofText(TEXT2)
        .find(obj1, obj2, obj3)
        .setSpanClickListener(tText2, new SpanClickListener() {
            @Override
            public void clicked(String text) {
                Log.d("CLICK", "link clicked " + text);
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        })
        .create(); 
textView.setText(s2);
``` 

### 4. Java - SpanStartWith
```Java 
Spannable s3 = new SpanStartWith()
        .ofText(TEXT3)
        .startWith("#")
        .spandColor(Color.parseColor("#14af9b"))
        .setBold(false)
        .setItalic(true)
        .setSpanClickListener(tText3, new SpanClickListener() {
            @Override
            public void clicked(String text) {
                Log.d("CLICK", "link clicked " + text);
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        })
        .create();
textView.setText(s3);
``` 


### Output
Youtube : https://www.youtube.com/watch?v=FkN3MpOiKzg

![Alt text](https://github.com/opannapo/OutputPreviewAssets/blob/master/Nspannable/Android_Spannable_Library.mp4 "Output")
