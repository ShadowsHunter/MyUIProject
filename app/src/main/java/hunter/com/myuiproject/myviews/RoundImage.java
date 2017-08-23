package hunter.com.myuiproject.myviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import hunter.com.myuiproject.R;

/**
 * 自定义View----圆形图片
 * Created by hcgan on 2017/8/9.
 */

public class RoundImage extends View {
    private Paint paint;

    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    private Bitmap mSrc;//图片

    private int mRadius;//圆角大小

    private int mWidth;//控件宽度

    private int mHeight;//控件高度

    public RoundImage(Context context) {
        this(context,null);
    }

    public RoundImage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImage,defStyle,0);

        int n = a.getIndexCount();
        for (int i = 0 ; i < n ; i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.RoundImage_src:
                    mSrc = BitmapFactory.decodeResource(getResources(),a.getResourceId(attr,0));
                    break;
                case R.styleable.RoundImage_type:
                    type = a.getInt(attr,0);// 默认为Circle
                    break;
                case R.styleable.RoundImage_borderRadius:
                    mRadius = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                            getResources().getDisplayMetrics()));// 默认为10DP
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (type){
            case TYPE_CIRCLE:
                int min = Math.min(mWidth,mHeight);
                /**
                 * 长度如果不一致，按小的值进行压缩
                 */
                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);

                canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);

                break;
            case TYPE_ROUND:
                canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
                break;
        }

    }
    /**
     * 根据原图添加圆角
     *
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 获取圆形图片方法
     * @param bitmap
     * @param min
     * @return Bitmap
     * @author caizhiming
     */
    private Bitmap createCircleImage(Bitmap bitmap,int min){
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /*
         * SRC_IN这种模式，两个绘制的效果叠加后取交集展现后图，怎么说呢，咱们第一个绘制的是个圆形，第二个绘制的是个Bitmap，
         * 于是交集为圆形，展现的是BItmap，就实现了圆形图片效果。
         * 圆角，其实就是先绘制圆角矩形，是不是很简单，以后别人再说实现圆角，你就把这一行代码给他就行了。
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,0,0,paint);

        return target;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //设置宽度
        int specMode=MeasureSpec.getMode(widthMeasureSpec);
        int specSize=MeasureSpec.getSize(widthMeasureSpec);

        if(specMode==MeasureSpec.EXACTLY){// match_parent , accurate
            mWidth = specSize;
        }else {
            int desireByImg = getPaddingLeft() + getPaddingRight() + mSrc.getWidth();
            if(specMode == MeasureSpec.AT_MOST){  // wrap_content
                mWidth = Math.min(desireByImg,specSize);
            }else{
                mWidth = desireByImg;
            }
        }

        //设置高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        } else
        {
            int desire = getPaddingTop() + getPaddingBottom()
                    + mSrc.getHeight();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mHeight = Math.min(desire, specSize);
            } else{
                mHeight = desire;
            }
        }

        setMeasuredDimension(mWidth, mHeight);

    }
}
