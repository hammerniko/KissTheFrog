package de.hgs.kissthefrog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class WimmelView extends View {


    //Variablen
    private Paint paint;        //Virtueller Pinsel zum zeichnen
    private Random rnd;         //Zufällige Pos. für jedes WimmelObjekt
    private long randomSeed=1;  //Startwert für den Zufallsgenerator
    private int imageCount;     //Anzahl der zu ladenden Bilder

    //Zu verwendende Bild in Array laden
    private static final int[] images = {R.mipmap.distract1, R.mipmap.distract2, R.mipmap.distract3, R.mipmap.distract4, R.mipmap.distract5,
    R.mipmap.distract6,R.mipmap.distract7,R.mipmap.distract8};


    public WimmelView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rnd = new Random(randomSeed);
        for (int image :images){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);

            for (int i=0; i<imageCount/images.length; i++){
                float left = (float) (rnd.nextFloat()*getWidth()-bitmap.getWidth());
                float top  = (float) (rnd.nextFloat()*getHeight()-bitmap.getHeight());

                canvas.drawBitmap(bitmap,left,top,paint);
            }
            bitmap.recycle();
        }



    }

    public void setImageCount(int imageCount){
        this.imageCount = imageCount;
        randomSeed = System.currentTimeMillis();

        /**
         * Kennzeichnet gezeichneten Bereich als veraltet und zeichnet ihn bei
         * Gelegenheit neu
         */
        invalidate();
    }
}
