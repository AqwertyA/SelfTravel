package org.selftravel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

import org.selftravel.R;


public class CircleImageView extends ImageView {

    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private int mFirstBorderWidth = DEFAULT_BORDER_WIDTH;
    private int mSecondBorderWidth = DEFAULT_BORDER_WIDTH;
    private static final int DEFAULT_FIRST_BORDER_COLOR = Color.BLACK;
    private int mFirstBorderColor = DEFAULT_FIRST_BORDER_COLOR;
    private static final int DEFAULT_SECOND_BORDER_COLOR = Color.WHITE;
    private int mSecondBorderColor = DEFAULT_SECOND_BORDER_COLOR;
    private final RectF mDrawableRect = new RectF();
    private final RectF mFirstBorderRect = new RectF();
    private final RectF mSecondBorderRect = new RectF();
    private final Matrix mShaderMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private final Paint mFirstBorderPaint = new Paint();
    private final Paint mSecondBorderPaint = new Paint();
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mDrawableRadius;
    private float mFirstBorderRadius;
    private float mSecondBorderRadius;

    private boolean mReady;
    private boolean mSetupPending;

    public CircleImageView(Context context) {
        super(context);

        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);

        mFirstBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_first_border_width, DEFAULT_BORDER_WIDTH);
        mFirstBorderColor = a.getColor(R.styleable.CircleImageView_first_border_color, DEFAULT_FIRST_BORDER_COLOR);
        mSecondBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_second_border_width, DEFAULT_BORDER_WIDTH);
        mSecondBorderColor = a.getColor(R.styleable.CircleImageView_second_border_color, DEFAULT_SECOND_BORDER_COLOR);

        a.recycle();

        init();
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        mReady = true;

        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }
    }

    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius, mBitmapPaint);
        if (mSecondBorderWidth != 0) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mSecondBorderRadius, mSecondBorderPaint);
        }
        if (mFirstBorderWidth != 0) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFirstBorderRadius, mFirstBorderPaint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    public int getFirstBorderColor() {
        return mFirstBorderColor;
    }

    public void setFirstBorderColor(int borderColor) {
        if (borderColor == mFirstBorderColor) {
            return;
        }

        mFirstBorderColor = borderColor;
        mFirstBorderPaint.setColor(mFirstBorderColor);
        invalidate();
    }

    public int getFirstBorderWidth() {
        return mFirstBorderWidth;
    }

    public void setFirstBorderWidth(int borderWidth) {
        if (borderWidth == mFirstBorderWidth) {
            return;
        }

        mFirstBorderWidth = borderWidth;
        setup();
    }

    public int getSecondBorderColor() {
        return mSecondBorderColor;
    }

    public void setSecondBorderColor(int mSecondBorderColor) {
        if (this.mSecondBorderColor == mSecondBorderColor) {
            return;
        }
        this.mSecondBorderColor = mSecondBorderColor;
        mSecondBorderPaint.setColor(mSecondBorderColor);
        invalidate();
    }

    public int getSecondBorderWidth() {
        return mSecondBorderWidth;
    }

    public void setSecondBorderWidth(int mSecondBorderWidth) {
        if (this.mSecondBorderWidth == mSecondBorderWidth) {
            return;
        }
        this.mSecondBorderWidth = mSecondBorderWidth;
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }

        if (mBitmap == null) {
            return;
        }

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mFirstBorderPaint.setStyle(Paint.Style.STROKE);
        mFirstBorderPaint.setAntiAlias(true);
        mFirstBorderPaint.setColor(mFirstBorderColor);
        mFirstBorderPaint.setStrokeWidth(mFirstBorderWidth+1);

        mSecondBorderPaint.setStyle(Paint.Style.STROKE);
        mSecondBorderPaint.setAntiAlias(true);
        mSecondBorderPaint.setColor(mSecondBorderColor);
        mSecondBorderPaint.setStrokeWidth(mSecondBorderWidth+1);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();


        mFirstBorderRect.set(0, 0, getWidth(), getHeight());
        mFirstBorderRadius =
                Math.min((mFirstBorderRect.height() - mFirstBorderWidth) / 2f,
                        (mFirstBorderRect.width() - mFirstBorderWidth) / 2f);

        int borderWidth = mFirstBorderWidth + mSecondBorderWidth;
        mSecondBorderRect.set(mFirstBorderWidth, mFirstBorderWidth, getWidth() - mFirstBorderWidth, getHeight() - mFirstBorderWidth);
        mSecondBorderRadius = Math.min((mSecondBorderRect.height() - mSecondBorderWidth) / 2f, (mSecondBorderRect.width() - mSecondBorderWidth) / 2f);


        mDrawableRect.set(borderWidth, borderWidth, mFirstBorderRect.width() - borderWidth, mFirstBorderRect.height()
                - borderWidth);
        mDrawableRadius = Math.min(mDrawableRect.height() / 2f, mDrawableRect.width() / 2f);

        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mFirstBorderWidth + mSecondBorderWidth, (int) (dy + 0.5f) + mFirstBorderWidth + mSecondBorderWidth);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

}
